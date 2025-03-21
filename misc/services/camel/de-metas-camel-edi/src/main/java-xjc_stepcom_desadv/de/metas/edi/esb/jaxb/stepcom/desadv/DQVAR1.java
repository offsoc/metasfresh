
package de.metas.edi.esb.jaxb.stepcom.desadv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DQVAR1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DQVAR1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LINENUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="QUANTITY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="QUANTITYMEASUREUNIT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DISCREPANCYCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DISCREPANCYREASON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DISCREPANCYDESC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DISCREPANCYDATE1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DISCREPANCYDATE2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DQVAR1", propOrder = {
    "documentid",
    "linenumber",
    "quantity",
    "quantitymeasureunit",
    "discrepancycode",
    "discrepancyreason",
    "discrepancydesc",
    "discrepancydate1",
    "discrepancydate2"
})
public class DQVAR1 {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "LINENUMBER", required = true)
    protected String linenumber;
    @XmlElement(name = "QUANTITY", required = true)
    protected String quantity;
    @XmlElement(name = "QUANTITYMEASUREUNIT")
    protected String quantitymeasureunit;
    @XmlElement(name = "DISCREPANCYCODE")
    protected String discrepancycode;
    @XmlElement(name = "DISCREPANCYREASON")
    protected String discrepancyreason;
    @XmlElement(name = "DISCREPANCYDESC")
    protected String discrepancydesc;
    @XmlElement(name = "DISCREPANCYDATE1")
    protected String discrepancydate1;
    @XmlElement(name = "DISCREPANCYDATE2")
    protected String discrepancydate2;

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
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUANTITY() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUANTITY(String value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the quantitymeasureunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUANTITYMEASUREUNIT() {
        return quantitymeasureunit;
    }

    /**
     * Sets the value of the quantitymeasureunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUANTITYMEASUREUNIT(String value) {
        this.quantitymeasureunit = value;
    }

    /**
     * Gets the value of the discrepancycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISCREPANCYCODE() {
        return discrepancycode;
    }

    /**
     * Sets the value of the discrepancycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISCREPANCYCODE(String value) {
        this.discrepancycode = value;
    }

    /**
     * Gets the value of the discrepancyreason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISCREPANCYREASON() {
        return discrepancyreason;
    }

    /**
     * Sets the value of the discrepancyreason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISCREPANCYREASON(String value) {
        this.discrepancyreason = value;
    }

    /**
     * Gets the value of the discrepancydesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISCREPANCYDESC() {
        return discrepancydesc;
    }

    /**
     * Sets the value of the discrepancydesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISCREPANCYDESC(String value) {
        this.discrepancydesc = value;
    }

    /**
     * Gets the value of the discrepancydate1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISCREPANCYDATE1() {
        return discrepancydate1;
    }

    /**
     * Sets the value of the discrepancydate1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISCREPANCYDATE1(String value) {
        this.discrepancydate1 = value;
    }

    /**
     * Gets the value of the discrepancydate2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISCREPANCYDATE2() {
        return discrepancydate2;
    }

    /**
     * Sets the value of the discrepancydate2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISCREPANCYDATE2(String value) {
        this.discrepancydate2 = value;
    }

}
