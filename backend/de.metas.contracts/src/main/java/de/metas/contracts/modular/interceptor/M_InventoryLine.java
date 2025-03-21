/*
 * #%L
 * de.metas.contracts
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

package de.metas.contracts.modular.interceptor;

import de.metas.contracts.FlatrateTermId;
import de.metas.contracts.IFlatrateBL;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_InventoryLine;
import org.compiere.model.ModelValidator;
import org.springframework.stereotype.Component;

import static de.metas.contracts.model.X_C_Flatrate_Term.TYPE_CONDITIONS_ModularContract;

@Component
@Interceptor(I_M_InventoryLine.class)
public class M_InventoryLine
{
	private final IFlatrateBL flatrateBL = Services.get(IFlatrateBL.class);

	@ModelChange(timings = ModelValidator.TYPE_BEFORE_CHANGE, ifColumnsChanged = I_M_InventoryLine.COLUMNNAME_Modular_Flatrate_Term_ID)
	public void validateModularContract(@NonNull final I_M_InventoryLine inventoryLineRecord)
	{
		final FlatrateTermId modularContractId = FlatrateTermId.ofRepoIdOrNull(inventoryLineRecord.getModular_Flatrate_Term_ID());
		if (modularContractId == null)
		{
			return;
		}

		if (!flatrateBL.isModularContract(modularContractId))
		{
			throw new AdempiereException("Contract must " + TYPE_CONDITIONS_ModularContract);
		}
	}
}
