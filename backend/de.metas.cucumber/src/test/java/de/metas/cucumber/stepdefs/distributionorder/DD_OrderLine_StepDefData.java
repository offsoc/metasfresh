/*
 * #%L
 * de.metas.cucumber
 * %%
 * Copyright (C) 2022 metas GmbH
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

package de.metas.cucumber.stepdefs.distributionorder;

import de.metas.cucumber.stepdefs.StepDefData;
import de.metas.cucumber.stepdefs.StepDefDataGetIdAware;
import de.metas.distribution.ddorder.DDOrderId;
import de.metas.distribution.ddorder.DDOrderLineId;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.I_DD_OrderLine;

/**
 * Having a dedicated class to help the IOC-framework injecting the right instances, if a step-def needs more than one.
 */
public class DD_OrderLine_StepDefData extends StepDefData<I_DD_OrderLine>
		implements StepDefDataGetIdAware<DDOrderLineId, I_DD_OrderLine>
{
	public DD_OrderLine_StepDefData()
	{
		super(I_DD_OrderLine.class);
	}

	@Override
	public DDOrderLineId extractIdFromRecord(final I_DD_OrderLine record)
	{
		return DDOrderLineId.ofRepoId(record.getDD_OrderLine_ID());
	}
}
