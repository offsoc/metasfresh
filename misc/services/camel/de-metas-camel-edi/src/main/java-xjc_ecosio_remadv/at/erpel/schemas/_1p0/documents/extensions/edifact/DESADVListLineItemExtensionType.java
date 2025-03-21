
package at.erpel.schemas._1p0.documents.extensions.edifact;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DESADVListLineItemExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DESADVListLineItemExtensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}OrderedQuantity" minOccurs="0"/&gt;
 *         &lt;element name="TotalDeliveryQuantity" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *         &lt;element name="PartialDelivery" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PriceLookupNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SubstituedForArticleNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}ConsumerUnitEAN" minOccurs="0"/&gt;
 *         &lt;element name="ConsumerUnitAmount" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *         &lt;element name="ConsumerUnit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="TradedUnit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ContractNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QuantityVariances" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ParentLineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AssortmentUnit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ReturnableContainer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="QuantityInHigherLevelAssortmentUnit" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DESADVListLineItemExtensionType", propOrder = {
    "orderedQuantity",
    "totalDeliveryQuantity",
    "partialDelivery",
    "priceLookupNumber",
    "substituedForArticleNumber",
    "consumerUnitEAN",
    "consumerUnitAmount",
    "consumerUnit",
    "tradedUnit",
    "contractNumber",
    "quantityVariances",
    "parentLineNumber",
    "assortmentUnit",
    "returnableContainer",
    "quantityInHigherLevelAssortmentUnit"
})
public class DESADVListLineItemExtensionType {

    @XmlElement(name = "OrderedQuantity")
    protected UnitType orderedQuantity;
    @XmlElement(name = "TotalDeliveryQuantity")
    protected UnitType totalDeliveryQuantity;
    @XmlElement(name = "PartialDelivery")
    protected String partialDelivery;
    @XmlElement(name = "PriceLookupNumber")
    protected String priceLookupNumber;
    @XmlElement(name = "SubstituedForArticleNumber")
    protected String substituedForArticleNumber;
    @XmlElement(name = "ConsumerUnitEAN")
    protected String consumerUnitEAN;
    @XmlElement(name = "ConsumerUnitAmount")
    protected UnitType consumerUnitAmount;
    @XmlElement(name = "ConsumerUnit")
    protected Boolean consumerUnit;
    @XmlElement(name = "TradedUnit")
    protected Boolean tradedUnit;
    @XmlElement(name = "ContractNumber")
    protected String contractNumber;
    @XmlElement(name = "QuantityVariances")
    protected List<UnitType> quantityVariances;
    @XmlElement(name = "ParentLineNumber")
    protected String parentLineNumber;
    @XmlElement(name = "AssortmentUnit")
    protected Boolean assortmentUnit;
    @XmlElement(name = "ReturnableContainer")
    protected Boolean returnableContainer;
    @XmlElement(name = "QuantityInHigherLevelAssortmentUnit")
    protected UnitType quantityInHigherLevelAssortmentUnit;

    /**
     * DEPRICATED - Please use "ListLineItemExtension/OrderedQuantity" instead.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getOrderedQuantity() {
        return orderedQuantity;
    }

    /**
     * Sets the value of the orderedQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setOrderedQuantity(UnitType value) {
        this.orderedQuantity = value;
    }

    /**
     * Gets the value of the totalDeliveryQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getTotalDeliveryQuantity() {
        return totalDeliveryQuantity;
    }

    /**
     * Sets the value of the totalDeliveryQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setTotalDeliveryQuantity(UnitType value) {
        this.totalDeliveryQuantity = value;
    }

    /**
     * Gets the value of the partialDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartialDelivery() {
        return partialDelivery;
    }

    /**
     * Sets the value of the partialDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartialDelivery(String value) {
        this.partialDelivery = value;
    }

    /**
     * Gets the value of the priceLookupNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceLookupNumber() {
        return priceLookupNumber;
    }

    /**
     * Sets the value of the priceLookupNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceLookupNumber(String value) {
        this.priceLookupNumber = value;
    }

    /**
     * Gets the value of the substituedForArticleNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstituedForArticleNumber() {
        return substituedForArticleNumber;
    }

    /**
     * Sets the value of the substituedForArticleNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstituedForArticleNumber(String value) {
        this.substituedForArticleNumber = value;
    }

    /**
     * The GTIN (EAN) of the consumer unit of the given dispatch advice line item.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerUnitEAN() {
        return consumerUnitEAN;
    }

    /**
     * Sets the value of the consumerUnitEAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerUnitEAN(String value) {
        this.consumerUnitEAN = value;
    }

    /**
     * Gets the value of the consumerUnitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getConsumerUnitAmount() {
        return consumerUnitAmount;
    }

    /**
     * Sets the value of the consumerUnitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setConsumerUnitAmount(UnitType value) {
        this.consumerUnitAmount = value;
    }

    /**
     * Gets the value of the consumerUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConsumerUnit() {
        return consumerUnit;
    }

    /**
     * Sets the value of the consumerUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConsumerUnit(Boolean value) {
        this.consumerUnit = value;
    }

    /**
     * Gets the value of the tradedUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTradedUnit() {
        return tradedUnit;
    }

    /**
     * Sets the value of the tradedUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTradedUnit(Boolean value) {
        this.tradedUnit = value;
    }

    /**
     * Gets the value of the contractNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Sets the value of the contractNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * Gets the value of the quantityVariances property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quantityVariances property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuantityVariances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitType }
     * 
     * 
     */
    public List<UnitType> getQuantityVariances() {
        if (quantityVariances == null) {
            quantityVariances = new ArrayList<UnitType>();
        }
        return this.quantityVariances;
    }

    /**
     * Gets the value of the parentLineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentLineNumber() {
        return parentLineNumber;
    }

    /**
     * Sets the value of the parentLineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentLineNumber(String value) {
        this.parentLineNumber = value;
    }

    /**
     * Gets the value of the assortmentUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssortmentUnit() {
        return assortmentUnit;
    }

    /**
     * Sets the value of the assortmentUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssortmentUnit(Boolean value) {
        this.assortmentUnit = value;
    }

    /**
     * Gets the value of the returnableContainer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnableContainer() {
        return returnableContainer;
    }

    /**
     * Sets the value of the returnableContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnableContainer(Boolean value) {
        this.returnableContainer = value;
    }

    /**
     * Gets the value of the quantityInHigherLevelAssortmentUnit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getQuantityInHigherLevelAssortmentUnit() {
        return quantityInHigherLevelAssortmentUnit;
    }

    /**
     * Sets the value of the quantityInHigherLevelAssortmentUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setQuantityInHigherLevelAssortmentUnit(UnitType value) {
        this.quantityInHigherLevelAssortmentUnit = value;
    }

}
