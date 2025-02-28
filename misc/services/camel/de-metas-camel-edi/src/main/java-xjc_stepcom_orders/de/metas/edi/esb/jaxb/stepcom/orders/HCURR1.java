
package de.metas.edi.esb.jaxb.stepcom.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HCURR1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HCURR1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CURRENCYQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CURRENCYCODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CURRENCYEXRATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HCURR1", propOrder = {
    "documentid",
    "currencyqual",
    "currencycode",
    "currencyexrate"
})
public class HCURR1 {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "CURRENCYQUAL", required = true)
    protected String currencyqual;
    @XmlElement(name = "CURRENCYCODE", required = true)
    protected String currencycode;
    @XmlElement(name = "CURRENCYEXRATE")
    protected String currencyexrate;

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
     * Gets the value of the currencyqual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENCYQUAL() {
        return currencyqual;
    }

    /**
     * Sets the value of the currencyqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENCYQUAL(String value) {
        this.currencyqual = value;
    }

    /**
     * Gets the value of the currencycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENCYCODE() {
        return currencycode;
    }

    /**
     * Sets the value of the currencycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENCYCODE(String value) {
        this.currencycode = value;
    }

    /**
     * Gets the value of the currencyexrate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENCYEXRATE() {
        return currencyexrate;
    }

    /**
     * Sets the value of the currencyexrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENCYEXRATE(String value) {
        this.currencyexrate = value;
    }

}
