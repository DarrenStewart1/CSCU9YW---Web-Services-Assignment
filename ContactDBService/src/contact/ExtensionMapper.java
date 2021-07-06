/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:06:07 GMT)
 */
package contact;


/**
 *  ExtensionMapper class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class ExtensionMapper {
    public static java.lang.Object getTypeObject(
        java.lang.String namespaceURI, java.lang.String typeName,
        javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
        if ("urn:Contact".equals(namespaceURI) && "address".equals(typeName)) {
            return contact.Address.Factory.parse(reader);
        }

        if ("urn:Contact".equals(namespaceURI) &&
                "contactDetail".equals(typeName)) {
            return contact.ContactDetail.Factory.parse(reader);
        }

        if ("urn:Contact".equals(namespaceURI) &&
                "contactDetails".equals(typeName)) {
            return contact.ContactDetails.Factory.parse(reader);
        }

        throw new org.apache.axis2.databinding.ADBException("Unsupported type " +
            namespaceURI + " " + typeName);
    }
}
