
package de.metas.edi.esb.jaxb.stepcom.invoic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DTAXI1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DTAXI1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LINENUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TAXQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TAXCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TAXRATE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TAXCATEGORY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TAXAMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TAXABLEAMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DTAXI1", propOrder = {
    "documentid",
    "linenumber",
    "taxqual",
    "taxcode",
    "taxrate",
    "taxcategory",
    "taxamount",
    "taxableamount"
})
public class DTAXI1 {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "LINENUMBER", required = true)
    protected String linenumber;
    @XmlElement(name = "TAXQUAL", required = true)
    protected String taxqual;
    @XmlElement(name = "TAXCODE")
    protected String taxcode;
    @XmlElement(name = "TAXRATE", required = true)
    protected String taxrate;
    @XmlElement(name = "TAXCATEGORY")
    protected String taxcategory;
    @XmlElement(name = "TAXAMOUNT")
    protected String taxamount;
    @XmlElement(name = "TAXABLEAMOUNT")
    protected String taxableamount;

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
     * Gets the value of the taxqual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXQUAL() {
        return taxqual;
    }

    /**
     * Sets the value of the taxqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXQUAL(String value) {
        this.taxqual = value;
    }

    /**
     * Gets the value of the taxcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXCODE() {
        return taxcode;
    }

    /**
     * Sets the value of the taxcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXCODE(String value) {
        this.taxcode = value;
    }

    /**
     * Gets the value of the taxrate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXRATE() {
        return taxrate;
    }

    /**
     * Sets the value of the taxrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXRATE(String value) {
        this.taxrate = value;
    }

    /**
     * Gets the value of the taxcategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXCATEGORY() {
        return taxcategory;
    }

    /**
     * Sets the value of the taxcategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXCATEGORY(String value) {
        this.taxcategory = value;
    }

    /**
     * Gets the value of the taxamount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXAMOUNT() {
        return taxamount;
    }

    /**
     * Sets the value of the taxamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXAMOUNT(String value) {
        this.taxamount = value;
    }

    /**
     * Gets the value of the taxableamount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXABLEAMOUNT() {
        return taxableamount;
    }

    /**
     * Sets the value of the taxableamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXABLEAMOUNT(String value) {
        this.taxableamount = value;
    }

}
