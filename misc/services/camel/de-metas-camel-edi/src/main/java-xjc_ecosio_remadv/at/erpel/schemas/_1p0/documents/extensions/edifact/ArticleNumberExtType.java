
package at.erpel.schemas._1p0.documents.extensions.edifact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Additional articlenumbers for special setups.
 * 
 * <p>Java class for ArticleNumberExtType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArticleNumberExtType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="ArticleNumberType"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="PZN"/&gt;
 *             &lt;enumeration value="GTIN"/&gt;
 *             &lt;enumeration value="SuppliersArticleNumber"/&gt;
 *             &lt;enumeration value="CustomersArticleNumber"/&gt;
 *             &lt;enumeration value="ManufacturersArticleNumber"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArticleNumberExtType", propOrder = {
    "content"
})
public class ArticleNumberExtType {

    @XmlValue
    protected String content;
    @XmlAttribute(name = "ArticleNumberType", namespace = "http://erpel.at/schemas/1p0/documents/extensions/edifact")
    protected String articleNumberType;
    @XmlAttribute(name = "Reference", namespace = "http://erpel.at/schemas/1p0/documents/extensions/edifact")
    protected String reference;

    /**
     * Additional articlenumbers for special setups.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the articleNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleNumberType() {
        return articleNumberType;
    }

    /**
     * Sets the value of the articleNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleNumberType(String value) {
        this.articleNumberType = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

}
