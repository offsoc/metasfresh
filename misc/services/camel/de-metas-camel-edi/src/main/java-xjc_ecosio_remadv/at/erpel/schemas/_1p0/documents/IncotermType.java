
package at.erpel.schemas._1p0.documents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Incoterm
 * 
 * <p>Java class for IncotermType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IncotermType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DeliveryOrTransportTermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IncotermCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IncotermText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IncotermLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncotermType", propOrder = {
    "deliveryOrTransportTermsCode",
    "incotermCode",
    "incotermText",
    "incotermLocation"
})
public class IncotermType {

    @XmlElement(name = "DeliveryOrTransportTermsCode")
    protected String deliveryOrTransportTermsCode;
    @XmlElement(name = "IncotermCode")
    protected String incotermCode;
    @XmlElement(name = "IncotermText")
    protected String incotermText;
    @XmlElement(name = "IncotermLocation")
    protected String incotermLocation;

    /**
     * Gets the value of the deliveryOrTransportTermsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryOrTransportTermsCode() {
        return deliveryOrTransportTermsCode;
    }

    /**
     * Sets the value of the deliveryOrTransportTermsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryOrTransportTermsCode(String value) {
        this.deliveryOrTransportTermsCode = value;
    }

    /**
     * Gets the value of the incotermCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncotermCode() {
        return incotermCode;
    }

    /**
     * Sets the value of the incotermCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncotermCode(String value) {
        this.incotermCode = value;
    }

    /**
     * Gets the value of the incotermText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncotermText() {
        return incotermText;
    }

    /**
     * Sets the value of the incotermText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncotermText(String value) {
        this.incotermText = value;
    }

    /**
     * Gets the value of the incotermLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncotermLocation() {
        return incotermLocation;
    }

    /**
     * Sets the value of the incotermLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncotermLocation(String value) {
        this.incotermLocation = value;
    }

}
