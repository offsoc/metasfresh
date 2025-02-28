
package at.erpel.schemas._1p0.documents.ext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsignmentItemInformationExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsignmentItemInformationExtensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}ConsignmentItemInformationExtension"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/ext}ErpelConsignmentItemInformationExtension"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsignmentItemInformationExtensionType", propOrder = {
    "consignmentItemInformationExtension",
    "erpelConsignmentItemInformationExtension"
})
public class ConsignmentItemInformationExtensionType {

    @XmlElement(name = "ConsignmentItemInformationExtension", namespace = "http://erpel.at/schemas/1p0/documents/extensions/edifact")
    protected at.erpel.schemas._1p0.documents.extensions.edifact.ConsignmentItemInformationExtensionType consignmentItemInformationExtension;
    @XmlElement(name = "ErpelConsignmentItemInformationExtension")
    protected CustomType erpelConsignmentItemInformationExtension;

    /**
     * Gets the value of the consignmentItemInformationExtension property.
     * 
     * @return
     *     possible object is
     *     {@link at.erpel.schemas._1p0.documents.extensions.edifact.ConsignmentItemInformationExtensionType }
     *     
     */
    public at.erpel.schemas._1p0.documents.extensions.edifact.ConsignmentItemInformationExtensionType getConsignmentItemInformationExtension() {
        return consignmentItemInformationExtension;
    }

    /**
     * Sets the value of the consignmentItemInformationExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link at.erpel.schemas._1p0.documents.extensions.edifact.ConsignmentItemInformationExtensionType }
     *     
     */
    public void setConsignmentItemInformationExtension(at.erpel.schemas._1p0.documents.extensions.edifact.ConsignmentItemInformationExtensionType value) {
        this.consignmentItemInformationExtension = value;
    }

    /**
     * Gets the value of the erpelConsignmentItemInformationExtension property.
     * 
     * @return
     *     possible object is
     *     {@link CustomType }
     *     
     */
    public CustomType getErpelConsignmentItemInformationExtension() {
        return erpelConsignmentItemInformationExtension;
    }

    /**
     * Sets the value of the erpelConsignmentItemInformationExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomType }
     *     
     */
    public void setErpelConsignmentItemInformationExtension(CustomType value) {
        this.erpelConsignmentItemInformationExtension = value;
    }

}
