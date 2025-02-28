
package de.metas.edi.esb.jaxb.stepcom.invoic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DPRDQ1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DPRDQ1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LINENUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="QUANTITYQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PRICEQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PRICE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PRICESPEC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PRICETYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PRICEBASIS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PRICEMEASUREUNIT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CURRENCY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DPRDQ1", propOrder = {
    "documentid",
    "linenumber",
    "quantityqual",
    "pricequal",
    "price",
    "pricespec",
    "pricetype",
    "pricebasis",
    "pricemeasureunit",
    "currency"
})
public class DPRDQ1 {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "LINENUMBER", required = true)
    protected String linenumber;
    @XmlElement(name = "QUANTITYQUAL", required = true)
    protected String quantityqual;
    @XmlElement(name = "PRICEQUAL", required = true)
    protected String pricequal;
    @XmlElement(name = "PRICE", required = true)
    protected String price;
    @XmlElement(name = "PRICESPEC")
    protected String pricespec;
    @XmlElement(name = "PRICETYPE")
    protected String pricetype;
    @XmlElement(name = "PRICEBASIS")
    protected String pricebasis;
    @XmlElement(name = "PRICEMEASUREUNIT")
    protected String pricemeasureunit;
    @XmlElement(name = "CURRENCY")
    protected String currency;

    /**
     * Gets the value of the documentid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCUMENTID() {
        return documentid;
    }

    /**
     * Sets the value of the documentid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCUMENTID(String value) {
        this.documentid = value;
    }

    /**
     * Gets the value of the linenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINENUMBER() {
        return linenumber;
    }

    /**
     * Sets the value of the linenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINENUMBER(String value) {
        this.linenumber = value;
    }

    /**
     * Gets the value of the quantityqual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUANTITYQUAL() {
        return quantityqual;
    }

    /**
     * Sets the value of the quantityqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUANTITYQUAL(String value) {
        this.quantityqual = value;
    }

    /**
     * Gets the value of the pricequal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRICEQUAL() {
        return pricequal;
    }

    /**
     * Sets the value of the pricequal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRICEQUAL(String value) {
        this.pricequal = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRICE() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRICE(String value) {
        this.price = value;
    }

    /**
     * Gets the value of the pricespec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRICESPEC() {
        return pricespec;
    }

    /**
     * Sets the value of the pricespec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRICESPEC(String value) {
        this.pricespec = value;
    }

    /**
     * Gets the value of the pricetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRICETYPE() {
        return pricetype;
    }

    /**
     * Sets the value of the pricetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRICETYPE(String value) {
        this.pricetype = value;
    }

    /**
     * Gets the value of the pricebasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRICEBASIS() {
        return pricebasis;
    }

    /**
     * Sets the value of the pricebasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRICEBASIS(String value) {
        this.pricebasis = value;
    }

    /**
     * Gets the value of the pricemeasureunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRICEMEASUREUNIT() {
        return pricemeasureunit;
    }

    /**
     * Sets the value of the pricemeasureunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRICEMEASUREUNIT(String value) {
        this.pricemeasureunit = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENCY() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENCY(String value) {
        this.currency = value;
    }

}
