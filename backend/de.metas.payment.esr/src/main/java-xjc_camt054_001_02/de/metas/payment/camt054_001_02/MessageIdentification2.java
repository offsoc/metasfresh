
package de.metas.payment.camt054_001_02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageIdentification2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageIdentification2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MsgNmId" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.02}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="MsgId" type="{urn:iso:std:iso:20022:tech:xsd:camt.054.001.02}Max35Text" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageIdentification2", propOrder = {
    "msgNmId",
    "msgId"
})
public class MessageIdentification2 {

    @XmlElement(name = "MsgNmId")
    protected String msgNmId;
    @XmlElement(name = "MsgId")
    protected String msgId;

    /**
     * Gets the value of the msgNmId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgNmId() {
        return msgNmId;
    }

    /**
     * Sets the value of the msgNmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgNmId(String value) {
        this.msgNmId = value;
    }

    /**
     * Gets the value of the msgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Sets the value of the msgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgId(String value) {
        this.msgId = value;
    }

}
