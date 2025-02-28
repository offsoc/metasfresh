
package at.erpel.schemas._1p0.documents.extensions.edifact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An adjustment to a monetary amount.
 * 
 * <p>Java class for AdjustmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdjustmentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReasonCode" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}AlphaNumType"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}AdjustmentMonetaryAmount"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}Tax" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdjustmentType", propOrder = {
    "reasonCode",
    "adjustmentMonetaryAmount",
    "tax"
})
public class AdjustmentType {

    @XmlElement(name = "ReasonCode", required = true)
    protected String reasonCode;
    @XmlElement(name = "AdjustmentMonetaryAmount", required = true)
    protected MonetaryAmountType adjustmentMonetaryAmount;
    @XmlElement(name = "Tax")
    protected TaxType tax;

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * Monetary amount of the actual adjustment.
     * 
     * @return
     *     possible object is
     *     {@link MonetaryAmountType }
     *     
     */
    public MonetaryAmountType getAdjustmentMonetaryAmount() {
        return adjustmentMonetaryAmount;
    }

    /**
     * Sets the value of the adjustmentMonetaryAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonetaryAmountType }
     *     
     */
    public void setAdjustmentMonetaryAmount(MonetaryAmountType value) {
        this.adjustmentMonetaryAmount = value;
    }

    /**
     * Taxes applied to the adjustment.
     * 
     * @return
     *     possible object is
     *     {@link TaxType }
     *     
     */
    public TaxType getTax() {
        return tax;
    }

    /**
     * Sets the value of the tax property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxType }
     *     
     */
    public void setTax(TaxType value) {
        this.tax = value;
    }

}
