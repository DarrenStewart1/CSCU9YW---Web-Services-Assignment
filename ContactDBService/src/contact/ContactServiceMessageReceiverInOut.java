/**
 * ContactServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package contact;


/**
 *  ContactServiceMessageReceiverInOut message receiver
 */
public class ContactServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            ContactServiceSkeleton skel = (ContactServiceSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("editContact".equals(methodName)) {
                    contact.RequestSingleContact requestSingleContact26 = null;
                    contact.RequestSingleContact wrappedParam = (contact.RequestSingleContact) fromOM(msgContext.getEnvelope()
                                                                                                                .getBody()
                                                                                                                .getFirstElement(),
                            contact.RequestSingleContact.class);

                    requestSingleContact26 = skel.editContact(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            requestSingleContact26, false,
                            new javax.xml.namespace.QName("urn:Contact",
                                "requestSingleContact"));
                } else
                 if ("getByCity".equals(methodName)) {
                    contact.MultipleContacts multipleContacts28 = null;
                    contact.RequestCity wrappedParam = (contact.RequestCity) fromOM(msgContext.getEnvelope()
                                                                                              .getBody()
                                                                                              .getFirstElement(),
                            contact.RequestCity.class);

                    multipleContacts28 = skel.getByCity(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            multipleContacts28, false,
                            new javax.xml.namespace.QName("urn:Contact",
                                "MultipleContacts"));
                } else
                 if ("getByPhoneNumber".equals(methodName)) {
                    contact.RequestSingleContact requestSingleContact30 = null;
                    contact.ContactPhoneNumber wrappedParam = (contact.ContactPhoneNumber) fromOM(msgContext.getEnvelope()
                                                                                                            .getBody()
                                                                                                            .getFirstElement(),
                            contact.ContactPhoneNumber.class);

                    requestSingleContact30 = skel.getByPhoneNumber(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            requestSingleContact30, false,
                            new javax.xml.namespace.QName("urn:Contact",
                                "requestSingleContact"));
                } else
                 if ("deleteContact".equals(methodName)) {
                    contact.ContactResponse contactResponse32 = null;
                    contact.ContactPhoneNumber wrappedParam = (contact.ContactPhoneNumber) fromOM(msgContext.getEnvelope()
                                                                                                            .getBody()
                                                                                                            .getFirstElement(),
                            contact.ContactPhoneNumber.class);

                    contactResponse32 = skel.deleteContact(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            contactResponse32, false,
                            new javax.xml.namespace.QName("urn:Contact",
                                "contactResponse"));
                } else
                 if ("addContact".equals(methodName)) {
                    contact.ContactResponse contactResponse34 = null;
                    contact.RequestSingleContact wrappedParam = (contact.RequestSingleContact) fromOM(msgContext.getEnvelope()
                                                                                                                .getBody()
                                                                                                                .getFirstElement(),
                            contact.RequestSingleContact.class);

                    contactResponse34 = skel.addContact(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            contactResponse34, false,
                            new javax.xml.namespace.QName("urn:Contact",
                                "contactResponse"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (ErrorFault e) {
            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,
                "Error");

            org.apache.axis2.AxisFault f = createAxisFault(e);

            if (e.getFaultMessage() != null) {
                f.setDetail(toOM(e.getFaultMessage(), false));
            }

            throw f;
        }
        catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(
        contact.RequestSingleContact param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(contact.RequestSingleContact.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(contact.Error param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(contact.Error.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(contact.RequestCity param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(contact.RequestCity.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(contact.MultipleContacts param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(contact.MultipleContacts.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        contact.ContactPhoneNumber param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(contact.ContactPhoneNumber.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(contact.ContactResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(contact.ContactResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        contact.RequestSingleContact param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    contact.RequestSingleContact.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private contact.RequestSingleContact wrapeditContact() {
        contact.RequestSingleContact wrappedElement = new contact.RequestSingleContact();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        contact.MultipleContacts param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    contact.MultipleContacts.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private contact.MultipleContacts wrapgetByCity() {
        contact.MultipleContacts wrappedElement = new contact.MultipleContacts();

        return wrappedElement;
    }

    private contact.RequestSingleContact wrapgetByPhoneNumber() {
        contact.RequestSingleContact wrappedElement = new contact.RequestSingleContact();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        contact.ContactResponse param, boolean optimizeContent,
        javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    contact.ContactResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private contact.ContactResponse wrapdeleteContact() {
        contact.ContactResponse wrappedElement = new contact.ContactResponse();

        return wrappedElement;
    }

    private contact.ContactResponse wrapaddContact() {
        contact.ContactResponse wrappedElement = new contact.ContactResponse();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (contact.ContactPhoneNumber.class.equals(type)) {
                return contact.ContactPhoneNumber.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (contact.ContactResponse.class.equals(type)) {
                return contact.ContactResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (contact.Error.class.equals(type)) {
                return contact.Error.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (contact.MultipleContacts.class.equals(type)) {
                return contact.MultipleContacts.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (contact.RequestCity.class.equals(type)) {
                return contact.RequestCity.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (contact.RequestSingleContact.class.equals(type)) {
                return contact.RequestSingleContact.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
