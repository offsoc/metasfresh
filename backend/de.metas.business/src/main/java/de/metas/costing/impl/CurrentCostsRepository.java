package de.metas.costing.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.acct.api.AcctSchema;
import de.metas.acct.api.AcctSchemaId;
import de.metas.acct.api.IAcctSchemaDAO;
import de.metas.costing.AggregatedCostPrice;
import de.metas.costing.CostElement;
import de.metas.costing.CostElementId;
import de.metas.costing.CostPrice;
import de.metas.costing.CostSegment;
import de.metas.costing.CostSegmentAndElement;
import de.metas.costing.CostTypeId;
import de.metas.costing.CostingLevel;
import de.metas.costing.CostingMethod;
import de.metas.costing.CurrentCost;
import de.metas.costing.CurrentCostId;
import de.metas.costing.CurrentCostQuery;
import de.metas.costing.ICostElementRepository;
import de.metas.costing.ICurrentCostsRepository;
import de.metas.costing.IProductCostingBL;
import de.metas.logging.LogManager;
import de.metas.money.CurrencyId;
import de.metas.organization.OrgId;
import de.metas.product.IProductBL;
import de.metas.product.ProductId;
import de.metas.uom.IUOMDAO;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.dao.ICompositeQueryUpdater;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ClientId;
import org.compiere.model.I_AD_Org;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Cost;
import org.compiere.model.I_M_Product;
import org.compiere.model.MOrg;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.adempiere.model.InterfaceWrapperHelper.saveRecord;

/*
 * #%L
 * de.metas.business
 * %%
 * Copyright (C) 2018 metas GmbH
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

@Component
public class CurrentCostsRepository implements ICurrentCostsRepository
{
	private static final Logger logger = LogManager.getLogger(CurrentCostsRepository.class);

	private final IQueryBL queryBL = Services.get(IQueryBL.class);
	private final IProductBL productBL = Services.get(IProductBL.class);
	private final IAcctSchemaDAO acctSchemasRepo = Services.get(IAcctSchemaDAO.class);
	private final IUOMDAO uomsRepo = Services.get(IUOMDAO.class);
	private final IProductCostingBL productCostingBL = Services.get(IProductCostingBL.class);
	private final ICostElementRepository costElementRepo;

	public CurrentCostsRepository(
			@NonNull final ICostElementRepository costElementRepo)
	{
		this.costElementRepo = costElementRepo;
	}

	@Override
	public List<CurrentCost> getByIds(@NonNull final Set<CurrentCostId> ids)
	{
		if (ids.isEmpty())
		{
			return ImmutableList.of();
		}

		final ImmutableList<I_M_Cost> records = queryBL
				.createQueryBuilder(I_M_Cost.class)
				.addInArrayFilter(I_M_Cost.COLUMNNAME_M_Cost_ID, ids)
				.list();

		return newLoader().toCurrentCosts(records);
	}

	private CurrentCostsLoader newLoader()
	{
		return CurrentCostsLoader.builder()
				.acctSchemasRepo(acctSchemasRepo)
				.uomsRepo(uomsRepo)
				.productCostingBL(productCostingBL)
				.costElementRepo(costElementRepo)
				.build();
	}

	@Override
	public CurrentCost getOrNull(@NonNull final CostSegmentAndElement costSegmentAndElement)
	{
		final I_M_Cost costRecord = getCostRecordOrNull(costSegmentAndElement);
		return costRecord != null
				? newLoader().toCurrentCost(costRecord)
				: null;
	}

	@Nullable
	private I_M_Cost getCostRecordOrNull(@NonNull final CostSegmentAndElement costSegmentAndElement)
	{
		return toSqlQuery(CurrentCostQuery.builderFrom(costSegmentAndElement).build())
				.firstOnly();
	}

	@Override
	public Stream<CurrentCost> stream(@NonNull final CurrentCostQuery query)
	{
		return list(query).stream();
	}

	@Override
	public ImmutableList<CurrentCost> list(@NonNull final CurrentCostQuery query)
	{
		final ImmutableList<I_M_Cost> records = toSqlQuery(query).list();
		return !records.isEmpty()
				? newLoader().toCurrentCosts(records)
				: ImmutableList.of();
	}

	private IQueryBuilder<I_M_Cost> toSqlQuery(@NonNull final CurrentCostQuery query)
	{
		final IQueryBuilder<I_M_Cost> queryBuilder = queryBL.createQueryBuilder(I_M_Cost.class);

		if (query.getClientId() != null)
		{
			queryBuilder.addEqualsFilter(I_M_Cost.COLUMNNAME_AD_Client_ID, query.getClientId());
		}
		if (query.getOrgId() != null)
		{
			queryBuilder.addEqualsFilter(I_M_Cost.COLUMNNAME_AD_Org_ID, query.getOrgId());
		}
		if (!query.getProductIds().isEmpty())
		{
			queryBuilder.addInArrayFilter(I_M_Cost.COLUMNNAME_M_Product_ID, query.getProductIds());
		}
		if (query.getAttributeSetInstanceId() != null)
		{
			queryBuilder.addEqualsFilter(I_M_Cost.COLUMNNAME_M_AttributeSetInstance_ID, query.getAttributeSetInstanceId());
		}
		if (query.getCostTypeId() != null)
		{
			queryBuilder.addEqualsFilter(I_M_Cost.COLUMNNAME_M_CostType_ID, query.getCostTypeId());
		}
		if (query.getAcctSchemaId() != null)
		{
			queryBuilder.addEqualsFilter(I_M_Cost.COLUMNNAME_C_AcctSchema_ID, query.getAcctSchemaId());
		}
		if (!query.getCostElementIds().isEmpty())
		{
			queryBuilder.addInArrayFilter(I_M_Cost.COLUMNNAME_M_CostElement_ID, query.getCostElementIds());
		}

		return queryBuilder;
	}

	@Override
	public CurrentCost getOrCreate(@NonNull final CostSegmentAndElement costSegmentAndElement)
	{
		final CurrentCost currentCost = getOrNull(costSegmentAndElement);
		if (currentCost != null)
		{
			return currentCost;
		}
		else
		{
			return create(costSegmentAndElement);
		}
	}

	@Override
	public Optional<AggregatedCostPrice> getAggregatedCostPriceByCostSegmentAndCostingMethod(
			@NonNull final CostSegment costSegment,
			@NonNull final CostingMethod costingMethod)
	{
		final Set<CostElementId> costElementIds = costElementRepo.getIdsByCostingMethod(costingMethod);
		if (costElementIds.isEmpty())
		{
			// throw new AdempiereException("No cost elements found for costing method: " + costingMethod);
			return Optional.empty();
		}

		final CurrentCostQuery query = CurrentCostQuery.builderFrom(costSegment).costElementIds(costElementIds).build();
		final ImmutableMap<CostElement, CostPrice> costPrices = stream(query)
				.collect(ImmutableMap.toImmutableMap(CurrentCost::getCostElement, CurrentCost::getCostPrice));
		if (costPrices.isEmpty())
		{
			// throw new AdempiereException("No costs found for " + costSegment + " and " + costingMethod);
			return Optional.empty();
		}

		return Optional.of(
				AggregatedCostPrice.builder()
						.prices(costPrices)
						.build());
	}

	@Override
	public ImmutableList<CurrentCost> getByCostSegmentAndCostingMethod(
			@NonNull final CostSegment costSegment,
			final CostingMethod costingMethod)
	{
		final Set<CostElementId> costElementIds = costElementRepo.getIdsByCostingMethod(costingMethod);
		if (costElementIds.isEmpty())
		{
			// throw new AdempiereException("No cost elements found for costing method: " + costingMethod);
			return ImmutableList.of();
		}

		final CurrentCostQuery query = CurrentCostQuery.builderFrom(costSegment).costElementIds(costElementIds).build();
		return list(query);
	}

	@Override
	public ImmutableList<CurrentCost> getByCostSegmentAndCostElements(
			@NonNull final CostSegment costSegment,
			@NonNull final Set<CostElementId> costElementIds)
	{
		final CurrentCostQuery query = CurrentCostQuery.builderFrom(costSegment)
				.costElementIds(Check.assumeNotEmpty(costElementIds, "costElementIds is not empty"))
				.build();

		return list(query);
	}

	@Override
	public CurrentCost create(@NonNull final CostSegmentAndElement costSegmentAndElement)
	{
		final CostElement costElement = costElementRepo.getById(costSegmentAndElement.getCostElementId());
		final AcctSchema acctSchema = acctSchemasRepo.getById(costSegmentAndElement.getAcctSchemaId());
		final I_C_UOM uom = productBL.getStockUOM(costSegmentAndElement.getProductId());

		final CurrentCost currentCost = CurrentCost.builder()
				.costSegment(costSegmentAndElement.toCostSegment())
				.costElement(costElement)
				.currencyId(acctSchema.getCurrencyId())
				.precision(acctSchema.getCosting().getCostingPrecision())
				.uom(uom)
				.build();

		save(currentCost);

		return currentCost;
	}

	@Override
	public void createIfMissing(@NonNull final CostSegmentAndElement costSegmentAndElement)
	{
		getOrCreate(costSegmentAndElement);
	}

	@Override
	public void save(@NonNull final CurrentCost currentCost)
	{
		final I_M_Cost costRecord;
		if (currentCost.getId() != null)
		{
			costRecord = InterfaceWrapperHelper.load(currentCost.getId(), I_M_Cost.class);
		}
		else
		{
			costRecord = InterfaceWrapperHelper.newInstance(I_M_Cost.class);
		}

		updateCostRecord(costRecord, currentCost);
		// costRecord.setProcessed(true); // FIXME Processed is a virtual column ?!?! wtf?!
		InterfaceWrapperHelper.save(costRecord);

		currentCost.setId(CurrentCostId.ofRepoId(costRecord.getM_Cost_ID()));
	}

	private static void updateCostRecord(
			final I_M_Cost cost,
			final CurrentCost from)
	{
		final CostSegment costSegment = from.getCostSegment();
		cost.setAD_Org_ID(costSegment.getOrgId().getRepoId());
		cost.setC_AcctSchema_ID(costSegment.getAcctSchemaId().getRepoId());
		cost.setM_CostType_ID(costSegment.getCostTypeId().getRepoId());
		cost.setM_Product_ID(costSegment.getProductId().getRepoId());
		cost.setM_AttributeSetInstance_ID(costSegment.getAttributeSetInstanceId().getRepoId());
		cost.setM_CostElement_ID(from.getCostElementId().getRepoId());

		cost.setC_Currency_ID(from.getCurrencyId().getRepoId());
		cost.setC_UOM_ID(from.getUomId().getRepoId());

		cost.setCurrentCostPrice(from.getCostPrice().getOwnCostPrice().toBigDecimal());
		cost.setCurrentCostPriceLL(from.getCostPrice().getComponentsCostPrice().toBigDecimal());
		cost.setCurrentQty(from.getCurrentQty().toBigDecimal());
		cost.setCumulatedAmt(from.getCumulatedAmt().toBigDecimal());
		cost.setCumulatedQty(from.getCumulatedQty().toBigDecimal());
	}

	@Override
	public void createDefaultProductCosts(final I_M_Product product)
	{
		forEachCostSegmentAndElement(product, this::createIfMissing);
	}

	@Override
	public void deleteForProduct(final I_M_Product product)
	{
		forEachCostSegmentAndElement(product, costSegmentAndElement -> toSqlQuery(CurrentCostQuery.builderFrom(costSegmentAndElement).build())
				.create()
				.delete());
	}

	@Override
	public void updateUOMForProduct(final I_M_Product product)
	{
		final ICompositeQueryUpdater<I_M_Cost> queryUpdater = queryBL
				.createCompositeQueryUpdater(I_M_Cost.class)
				.addSetColumnValue(I_M_Cost.COLUMNNAME_C_UOM_ID, product.getC_UOM_ID());

		forEachCostSegmentAndElement(product, costSegmentAndElement -> toSqlQuery(CurrentCostQuery.builderFrom(costSegmentAndElement).build())
				.create()
				.update(queryUpdater));
	}

	private void forEachCostSegmentAndElement(
			final I_M_Product product,
			final Consumer<CostSegmentAndElement> consumer)
	{
		final ClientId clientId = ClientId.ofRepoId(product.getAD_Client_ID());
		final ProductId productId = ProductId.ofRepoId(product.getM_Product_ID());
		final OrgId productOrgId = OrgId.ofRepoId(product.getAD_Org_ID());

		final List<CostElement> costElements = costElementRepo.getByClientId(clientId);

		for (final AcctSchema as : acctSchemasRepo.getAllByClient(clientId))
		{
			if (as.isDisallowPostingForOrg(productOrgId))
			{
				continue;
			}

			final AcctSchemaId acctSchemaId = as.getId();
			final CostTypeId costTypeId = as.getCosting().getCostTypeId();
			final CostingLevel costingLevel = productCostingBL.getCostingLevel(product, as);

			// Create Std Costing
			if (costingLevel == CostingLevel.Client)
			{
				final CostSegment costSegment = CostSegment.builder()
						.costingLevel(costingLevel)
						.acctSchemaId(acctSchemaId)
						.costTypeId(costTypeId)
						.productId(productId)
						.clientId(clientId)
						.orgId(OrgId.ANY)
						.attributeSetInstanceId(AttributeSetInstanceId.NONE)
						.build();

				costElements.forEach(costElement -> consumer.accept(costSegment.withCostElementId(costElement.getId())));
			}
			else if (costingLevel == CostingLevel.Organization)
			{
				final Properties ctx = Env.getCtx();
				for (final I_AD_Org org : MOrg.getOfClient(ctx, clientId.getRepoId()))
				{
					final CostSegment costSegment = CostSegment.builder()
							.costingLevel(costingLevel)
							.acctSchemaId(acctSchemaId)
							.costTypeId(costTypeId)
							.productId(productId)
							.clientId(clientId)
							.orgId(OrgId.ofRepoId(org.getAD_Org_ID()))
							.attributeSetInstanceId(AttributeSetInstanceId.NONE)
							.build();

					costElements.forEach(costElement -> consumer.accept(costSegment.withCostElementId(costElement.getId())));
				}
			}
			else
			{
				logger.warn("{}'s costing Level {} not supported", product.getName(), costingLevel);
			}
		}    // accounting schema loop
	}

	@Override
	public void updateCostRecord(
			@NonNull final CostSegmentAndElement costSegmentAndElement,
			@NonNull final Consumer<I_M_Cost> updater)
	{
		final I_M_Cost costRecord = getCostRecordOrNull(costSegmentAndElement);
		if (costRecord == null)
		{
			return;
		}

		updater.accept(costRecord);
		saveRecord(costRecord);
	}

	public boolean hasCostsInCurrency(final @NonNull AcctSchemaId acctSchemaId, @NonNull final CurrencyId currencyId)
	{
		return queryBL.createQueryBuilder(I_M_Cost.class)
				.addOnlyActiveRecordsFilter()
				.addEqualsFilter(I_M_Cost.COLUMNNAME_C_AcctSchema_ID, acctSchemaId)
				.addEqualsFilter(I_M_Cost.COLUMNNAME_C_Currency_ID, currencyId)
				.create()
				.anyMatch();
	}
}
