/*
 * #%L
 * ic114
 * %%
 * Copyright (C) 2023 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import { shallowEqual, useDispatch, useSelector } from 'react-redux';
import React, { useCallback } from 'react';
import { trl } from '../../../utils/translations';
import ScanHUAndGetQtyComponent from '../../../components/ScanHUAndGetQtyComponent';
import { getActivityById, getLineById, getQtyRejectedReasonsFromActivity } from '../../../reducers/wfProcesses';
import { parseQRCodeString } from '../../../utils/qrCode/hu';
import { postStepPicked } from '../../../api/picking';
import { updateWFProcess } from '../../../actions/WorkflowActions';
import { useBooleanSetting } from '../../../reducers/settings';
import {
  getNextEligibleLineToPick,
  getQtyPickedOrRejectedTotalForLine,
  getQtyToPickForLine,
  getQtyToPickRemainingForLine,
} from '../../../utils/picking';
import { isShowBestBeforeDate, isShowLotNo } from './PickConfig';
import { useSearchParams } from '../../../hooks/useSearchParams';
import { useHeaderUpdate } from './PickLineScreen';
import { pickingLineScanScreenLocation, pickingLineScreenLocation } from '../../../routes/picking';
import { getWFProcessScreenLocation } from '../../../routes/workflow_locations';
import { useCurrentPickingTargetInfo } from '../../../reducers/wfProcesses/picking/useCurrentPickTarget';
import { toNumberOrZero } from '../../../utils/numbers';
import { isBarcodeProductNoMatching } from '../../../utils/qrCode/common';
import { useMobileNavigation } from '../../../hooks/useMobileNavigation';
import { useScreenDefinition } from '../../../hooks/useScreenDefinition';

export const NEXT_PickingJob = 'pickingJob';
export const NEXT_NextPickingLine = 'nextPickingLine';

const PickLineScanScreen = () => {
  const { url, applicationId, wfProcessId, activityId, lineId } = useScreenDefinition({
    back: pickingLineScreenLocation,
  });

  const [urlParams] = useSearchParams();
  const qrCode = urlParams.get('qrCode');
  const next = urlParams.get('next');

  const {
    activity,
    caption,
    productId,
    productNo,
    ean13ProductCode,
    pickingUnit,
    packingItemName,
    qtyToPick,
    qtyPicked,
    qtyToPickRemaining,
    uom,
    qtyRejectedReasons,
    catchWeightUom,
    isShowPromptWhenOverPicking,
  } = useSelector((state) => getPropsFromState({ state, wfProcessId, activityId, lineId }), shallowEqual);

  const { luPickingTarget } = useCurrentPickingTargetInfo({ wfProcessId, activityId });

  useHeaderUpdate({ url, caption, uom, qtyToPick, qtyPicked });

  const resolveScannedBarcode = useCallback(
    (scannedBarcode) =>
      convertScannedBarcodeToResolvedResult({
        scannedBarcode,
        expectedProductId: productId,
      }),
    [productId]
  );

  const onClose = useOnClose({ applicationId, wfProcessId, activity, lineId, next });

  const onResult = usePostQtyPicked({
    wfProcessId,
    activityId,
    lineId,
    expectedProductNo: productNo,
    expectedEAN13ProductCode: ean13ProductCode,
    onClose,
  });

  const getConfirmationPromptForQty = useCallback(
    (qtyInput) => {
      if (qtyToPickRemaining !== undefined && toNumberOrZero(qtyInput) > qtyToPickRemaining) {
        return trl('activities.picking.overPickConfirmationPrompt');
      }
      return undefined;
    },
    [qtyToPickRemaining]
  );

  return (
    <ScanHUAndGetQtyComponent
      key={`${applicationId}_${wfProcessId}_${activityId}_${lineId}_scan`} // very important, to force the component recreation when we do history.replace
      scannedBarcode={qrCode}
      qtyTargetCaption={trl('general.QtyToPick')}
      qtyCaption={trl(pickingUnit === 'TU' ? 'general.QtyTU' : 'general.Qty')}
      packingItemName={pickingUnit === 'TU' ? packingItemName : null}
      qtyMax={qtyToPickRemaining}
      qtyTarget={qtyToPickRemaining}
      uom={uom}
      qtyRejectedReasons={qtyRejectedReasons}
      catchWeight={0}
      catchWeightUom={catchWeightUom}
      isShowBestBeforeDate={isShowBestBeforeDate}
      isShowLotNo={isShowLotNo}
      isShowCloseTargetButton={!!luPickingTarget}
      //
      resolveScannedBarcode={resolveScannedBarcode}
      onResult={onResult}
      onClose={onClose}
      getConfirmationPromptForQty={isShowPromptWhenOverPicking ? getConfirmationPromptForQty : undefined}
    />
  );
};

const getPropsFromState = ({ state, wfProcessId, activityId, lineId }) => {
  const activity = getActivityById(state, wfProcessId, activityId);
  const qtyRejectedReasons = getQtyRejectedReasonsFromActivity(activity);

  const line = getLineById(state, wfProcessId, activityId, lineId);

  return {
    activity,
    caption: line?.caption,
    productId: line.productId,
    productNo: line.productNo,
    ean13ProductCode: line.ean13ProductCode,
    pickingUnit: line?.pickingUnit,
    packingItemName: line?.packingItemName,
    qtyToPick: getQtyToPickForLine({ line }),
    qtyPicked: getQtyPickedOrRejectedTotalForLine({ line }),
    qtyToPickRemaining: getQtyToPickRemainingForLine({ line }),
    uom: line.uom,
    qtyRejectedReasons,
    catchWeightUom: line.catchWeightUOM,
    isShowPromptWhenOverPicking: activity?.dataStored?.isShowPromptWhenOverPicking,
  };
};

// @VisibleForTesting
export const convertScannedBarcodeToResolvedResult = ({ scannedBarcode, expectedProductId }) => {
  const parsedHUQRCode = parseQRCodeString(scannedBarcode);

  if (expectedProductId != null && parsedHUQRCode.productId != null && parsedHUQRCode.productId !== expectedProductId) {
    throw trl('activities.picking.notEligibleHUBarcode');
  }

  return convertQRCodeObjectToResolvedResult(parsedHUQRCode);
};

const convertQRCodeObjectToResolvedResult = (qrCodeObj) => {
  const result = {};

  if (qrCodeObj.weightNet != null) {
    result['catchWeight'] = qrCodeObj.weightNet;
  }

  if (qrCodeObj.isTUToBePickedAsWhole === true) {
    result['isTUToBePickedAsWhole'] = true;
  }

  result['bestBeforeDate'] = qrCodeObj.bestBeforeDate;
  result['lotNo'] = qrCodeObj.lotNo;

  console.log('resolveScannedBarcode', { result, qrCodeObj });
  return result;
};

const useOnClose = ({ applicationId, wfProcessId, activity, lineId, next }) => {
  const history = useMobileNavigation();
  const isGotoPickingJobOnClose = useBooleanSetting('PickLineScanScreen.gotoPickingJobOnClose', true);

  const gotoPickingJob = () => {
    history.replace(getWFProcessScreenLocation({ applicationId, wfProcessId }));
  };

  const gotoNextPickingLine = () => {
    const nextLineId = getNextEligibleLineToPick({ activity, excludeLineId: lineId })?.pickingLineId;
    if (nextLineId) {
      history.replace(
        pickingLineScanScreenLocation({
          applicationId,
          wfProcessId,
          activityId: activity.activityId,
          lineId: nextLineId,
          next: NEXT_NextPickingLine,
        })
      );
    } else {
      gotoPickingJob();
    }
  };

  return () => {
    if (next === NEXT_PickingJob) {
      gotoPickingJob();
    } else if (next === NEXT_NextPickingLine) {
      gotoNextPickingLine();
    } else {
      if (isGotoPickingJobOnClose) {
        gotoPickingJob();
      } else {
        history.goBack(); // go to picking line screen
      }
    }
  };
};

const usePostQtyPicked = ({
  wfProcessId,
  activityId,
  lineId: lineIdParam = null,
  expectedProductNo,
  expectedEAN13ProductCode,
  onClose,
}) => {
  const dispatch = useDispatch();

  return ({
    lineId = null,
    qty = 0,
    qtyRejected,
    reason = null,
    scannedBarcode = null,
    catchWeight = null,
    catchWeightUom = null,
    isTUToBePickedAsWhole = false,
    bestBeforeDate = null,
    lotNo = null,
    productNo,
    ean13ProductCode,
    isCloseTarget = false,
    isDone = true,
    resolvedBarcodeData,
    barcodeType,
    ...others
  }) => {
    const lineIdEffective = resolvedBarcodeData?.lineId ?? lineIdParam;
    console.log('usePostQtyPicked.onResult', {
      lineIdEffective,
      lineId,
      lineIdParam,
      qty,
      reason,
      scannedBarcode,
      catchWeight,
      catchWeightUom,
      isShowBestBeforeDate,
      bestBeforeDate,
      isShowLotNo,
      lotNo,
      productNo,
      ean13ProductCode,
      isDone,
      ...others,
    });

    if (
      !isBarcodeProductNoMatching({
        expectedProductNo,
        expectedEAN13ProductCode,
        barcodeProductNo: productNo,
        barcodeType,
      })
    ) {
      throw {
        messageKey: 'activities.picking.qrcode.differentProduct',
        context: { expectedProductNo, expectedEAN13ProductCode, productNo, barcodeType },
      };
    }

    return postStepPicked({
      wfProcessId,
      activityId,
      lineId: lineIdEffective,
      //stepId,
      huQRCode: scannedBarcode,
      qtyPicked: qty,
      qtyRejectedReasonCode: reason,
      qtyRejected,
      catchWeight,
      pickWholeTU: isTUToBePickedAsWhole,
      checkIfAlreadyPacked: catchWeight == null, // in case we deal with a catch weight product, always split, else we won't be able to pick a CU from CU if last CU
      setBestBeforeDate: isShowBestBeforeDate,
      bestBeforeDate,
      setLotNo: isShowLotNo,
      lotNo,
      isCloseTarget,
    })
      .then((wfProcess) => dispatch(updateWFProcess({ wfProcess })))
      .then(() => isDone && onClose());
    //.catch((axiosError) => toastError({ axiosError })); // no need to catch, will be handled by caller
  };
};

export default PickLineScanScreen;
