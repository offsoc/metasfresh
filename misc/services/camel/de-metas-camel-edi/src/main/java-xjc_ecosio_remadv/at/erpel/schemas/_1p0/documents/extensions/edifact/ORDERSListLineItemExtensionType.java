
package at.erpel.schemas._1p0.documents.extensions.edifact;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ORDERSListLineItemExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ORDERSListLineItemExtensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SalesValueCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}ConsumerUnitEAN" minOccurs="0"/&gt;
 *         &lt;element name="ConsumerUnitAmount" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *         &lt;element name="ContractReference" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}ReferenceType" minOccurs="0"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}FreeText" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ScheduledQuantity" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://erpel.at/schemas/1p0/documents/extensions/edifact&gt;Decimal4Type"&gt;
 *                 &lt;attribute ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}Date"/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ORDERSListLineItemExtensionType", propOrder = {
    "salesValueCurrency",
    "consumerUnitEAN",
    "consumerUnitAmount",
    "contractReference",
    "freeText",
    "scheduledQuantity"
})
public class ORDERSListLineItemExtensionType {

    @XmlElement(name = "SalesValueCurrency")
    protected String salesValueCurrency;
    @XmlElement(name = "ConsumerUnitEAN")
    protected String consumerUnitEAN;
    @XmlElement(name = "ConsumerUnitAmount")
    protected UnitType consumerUnitAmount;
    @XmlElement(name = "ContractReference")
    protected ReferenceType contractReference;
    @XmlElement(name = "FreeText")
    protected List<FreeTextType> freeText;
    @XmlElement(name = "ScheduledQuantity")
    protected List<ORDERSListLineItemExtensionType.ScheduledQuantity> scheduledQuantity;

    /**
     * Gets the value of the salesValueCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesValueCurrency() {
        return salesValueCurrency;
    }

    /**
     * Sets the value of the salesValueCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesValueCurrency(String value) {
        this.salesValueCurrency = value;
    }

    /**
     * The GTIN (EAN) of the consumer unit.
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
     * Gets the value of the contractReference property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getContractReference() {
        return contractReference;
    }

    /**
     * Sets the value of the contractReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setContractReference(ReferenceType value) {
        this.contractReference = value;
    }

    /**
     * Free text information.Gets the value of the freeText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the freeText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFreeText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FreeTextType }
     * 
     * 
     */
    public List<FreeTextType> getFreeText() {
        if (freeText == null) {
            freeText = new ArrayList<FreeTextType>();
        }
        return this.freeText;
    }

    /**
     * Gets the value of the scheduledQuantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduledQuantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduledQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ORDERSListLineItemExtensionType.ScheduledQuantity }
     * 
     * 
     */
    public List<ORDERSListLineItemExtensionType.ScheduledQuantity> getScheduledQuantity() {
        if (scheduledQuantity == null) {
            scheduledQuantity = new ArrayList<ORDERSListLineItemExtensionType.ScheduledQuantity>();
        }
        return this.scheduledQuantity;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://erpel.at/schemas/1p0/documents/extensions/edifact&gt;Decimal4Type"&gt;
     *       &lt;attribute ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}Date"/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ScheduledQuantity {

        @XmlValue
        protected BigDecimal value;
        @XmlAttribute(name = "Date", namespace = "http://erpel.at/schemas/1p0/documents/extensions/edifact")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar date;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setValue(BigDecimal value) {
            this.value = value;
        }

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDate(XMLGregorianCalendar value) {
            this.date = value;
        }

    }

}
