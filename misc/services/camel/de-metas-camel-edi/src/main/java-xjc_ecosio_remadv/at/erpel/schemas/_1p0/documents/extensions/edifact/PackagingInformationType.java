
package at.erpel.schemas._1p0.documents.extensions.edifact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackagingInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackagingInformationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TypeOfPackaging" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NVE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="GrossVolume" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType"/&gt;
 *         &lt;element name="GrossWeight" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackagingInformationType", propOrder = {
    "typeOfPackaging",
    "nve",
    "grossVolume",
    "grossWeight"
})
public class PackagingInformationType {

    @XmlElement(name = "TypeOfPackaging", required = true)
    protected String typeOfPackaging;
    @XmlElement(name = "NVE", required = true)
    protected String nve;
    @XmlElement(name = "GrossVolume", required = true)
    protected UnitType grossVolume;
    @XmlElement(name = "GrossWeight", required = true)
    protected UnitType grossWeight;

    /**
     * Gets the value of the typeOfPackaging property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfPackaging() {
        return typeOfPackaging;
    }

    /**
     * Sets the value of the typeOfPackaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfPackaging(String value) {
        this.typeOfPackaging = value;
    }

    /**
     * Gets the value of the nve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNVE() {
        return nve;
    }

    /**
     * Sets the value of the nve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNVE(String value) {
        this.nve = value;
    }

    /**
     * Gets the value of the grossVolume property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getGrossVolume() {
        return grossVolume;
    }

    /**
     * Sets the value of the grossVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setGrossVolume(UnitType value) {
        this.grossVolume = value;
    }

    /**
     * Gets the value of the grossWeight property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getGrossWeight() {
        return grossWeight;
    }

    /**
     * Sets the value of the grossWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setGrossWeight(UnitType value) {
        this.grossWeight = value;
    }

}
