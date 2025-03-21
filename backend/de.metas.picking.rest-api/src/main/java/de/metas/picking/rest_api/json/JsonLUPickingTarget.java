/*
 * #%L
 * de.metas.picking.rest-api
 * %%
 * Copyright (C) 2024 metas GmbH
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

package de.metas.picking.rest_api.json;

import de.metas.handlingunits.HuId;
import de.metas.handlingunits.HuPackingInstructionsId;
import de.metas.handlingunits.picking.job.model.LUPickingTarget;
import de.metas.handlingunits.qrcodes.model.HUQRCode;
import de.metas.util.StringUtils;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.annotation.Nullable;

@Value
@Builder
@Jacksonized
public class JsonLUPickingTarget
{
	@NonNull String id;
	@NonNull String caption;
	@Nullable HuPackingInstructionsId luPIId;
	@Nullable HuId luId;
	@Nullable String luQRCode;

	public static JsonLUPickingTarget of(@NonNull final LUPickingTarget target)
	{
		return builder()
				.id(target.getId())
				.caption(target.getCaption())
				.luPIId(target.getLuPIId())
				.luId(target.getLuId())
				.luQRCode(target.getLuQRCode() != null ? target.getLuQRCode().toGlobalQRCodeString() : null)
				.build();
	}

	public LUPickingTarget unbox()
	{
		return LUPickingTarget.builder()
				.caption(caption)
				.luPIId(luPIId)
				.luId(luId)
				.luQRCode(StringUtils.trimBlankToOptional(luQRCode).map(HUQRCode::fromGlobalQRCodeJsonString).orElse(null))
				.build();
	}
}
