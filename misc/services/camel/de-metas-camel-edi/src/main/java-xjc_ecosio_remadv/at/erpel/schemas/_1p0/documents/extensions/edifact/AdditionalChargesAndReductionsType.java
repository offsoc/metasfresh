
package at.erpel.schemas._1p0.documents.extensions.edifact;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalChargesAndReductionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalChargesAndReductionsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="AdditionalChargeAndReduction" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="BaseAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="Percentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="CalcuationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="VATRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="AllowanceOrChargeQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
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
@XmlType(name = "AdditionalChargesAndReductionsType", propOrder = {
    "additionalChargeAndReduction"
})
public class AdditionalChargesAndReductionsType {

    @XmlElement(name = "AdditionalChargeAndReduction")
    protected List<AdditionalChargesAndReductionsType.AdditionalChargeAndReduction> additionalChargeAndReduction;

    /**
     * Gets the value of the additionalChargeAndReduction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalChargeAndReduction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalChargeAndReduction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalChargesAndReductionsType.AdditionalChargeAndReduction }
     * 
     * 
     */
    public List<AdditionalChargesAndReductionsType.AdditionalChargeAndReduction> getAdditionalChargeAndReduction() {
        if (additionalChargeAndReduction == null) {
            additionalChargeAndReduction = new ArrayList<AdditionalChargesAndReductionsType.AdditionalChargeAndReduction>();
        }
        return this.additionalChargeAndReduction;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="BaseAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="Percentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="CalcuationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="VATRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="AllowanceOrChargeQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "type",
        "baseAmount",
        "percentage",
        "amount",
        "calcuationCode",
        "vatRate",
        "allowanceOrChargeQualifier"
    })
    public static class AdditionalChargeAndReduction {

        @XmlElement(name = "Type")
        protected String type;
        @XmlElement(name = "BaseAmount")
        protected BigDecimal baseAmount;
        @XmlElement(name = "Percentage")
        protected BigDecimal percentage;
        @XmlElement(name = "Amount")
        protected BigDecimal amount;
        @XmlElement(name = "CalcuationCode")
        protected String calcuationCode;
        @XmlElement(name = "VATRate")
        protected BigDecimal vatRate;
        @XmlElement(name = "AllowanceOrChargeQualifier")
        protected String allowanceOrChargeQualifier;

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the baseAmount property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBaseAmount() {
            return baseAmount;
        }

        /**
         * Sets the value of the baseAmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBaseAmount(BigDecimal value) {
            this.baseAmount = value;
        }

        /**
         * Gets the value of the percentage property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPercentage() {
            return percentage;
        }

        /**
         * Sets the value of the percentage property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPercentage(BigDecimal value) {
            this.percentage = value;
        }

        /**
         * Gets the value of the amount property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAmount() {
            return amount;
        }

        /**
         * Sets the value of the amount property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAmount(BigDecimal value) {
            this.amount = value;
        }

        /**
         * Gets the value of the calcuationCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCalcuationCode() {
            return calcuationCode;
        }

        /**
         * Sets the value of the calcuationCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCalcuationCode(String value) {
            this.calcuationCode = value;
        }

        /**
         * Gets the value of the vatRate property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getVATRate() {
            return vatRate;
        }

        /**
         * Sets the value of the vatRate property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setVATRate(BigDecimal value) {
            this.vatRate = value;
        }

        /**
         * Gets the value of the allowanceOrChargeQualifier property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAllowanceOrChargeQualifier() {
            return allowanceOrChargeQualifier;
        }

        /**
         * Sets the value of the allowanceOrChargeQualifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAllowanceOrChargeQualifier(String value) {
            this.allowanceOrChargeQualifier = value;
        }

    }

}
