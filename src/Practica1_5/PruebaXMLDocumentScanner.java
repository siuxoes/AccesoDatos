package Practica1_5;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 *
 * @author siuxoes
 */
public class PruebaXMLDocumentScanner {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;
    
    /**
     * Create new PruebaXMLDocumentScanner with org.w3c.dom.Document.
     */
    public PruebaXMLDocumentScanner(org.w3c.dom.Document document) {
        this.document = document;
    }
    
    /**
     * Scan through org.w3c.dom.Document document.
     */
    public void visitDocument() {
        org.w3c.dom.Element element = document.getDocumentElement();
        if ((element != null) && element.getTagName().equals("agenda")) {
            visitElement_agenda(element);
        }
        if ((element != null) && element.getTagName().equals("anotacion")) {
            visitElement_anotacion(element);
        }
        if ((element != null) && element.getTagName().equals("nombre")) {
            visitElement_nombre(element);
        }
        if ((element != null) && element.getTagName().equals("direccion")) {
            visitElement_direccion(element);
        }
        if ((element != null) && element.getTagName().equals("telefono")) {
            visitElement_telefono(element);
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named agenda.
     */
    void visitElement_agenda(org.w3c.dom.Element element) {
        // <agenda>
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("anotacion")) {
                        visitElement_anotacion(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named anotacion.
     */
    void visitElement_anotacion(org.w3c.dom.Element element) {
        // <anotacion>
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("nombre")) {
                        visitElement_nombre(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("direccion")) {
                        visitElement_direccion(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("telefono")) {
                        visitElement_telefono(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named nombre.
     */
    void visitElement_nombre(org.w3c.dom.Element element) {
        // <nombre>
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
                case org.w3c.dom.Node.TEXT_NODE:
                    System.out.println(((org.w3c.dom.Text)node).getData());
                    break;
            }
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named direccion.
     */
    void visitElement_direccion(org.w3c.dom.Element element) {
        // <direccion>
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
                case org.w3c.dom.Node.TEXT_NODE:
                    System.out.println(((org.w3c.dom.Text)node).getData());
                    break;
            }
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named telefono.
     */
    void visitElement_telefono(org.w3c.dom.Element element) {
        // <telefono>
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
                case org.w3c.dom.Node.TEXT_NODE:
                    System.out.println(((org.w3c.dom.Text)node).getData());
                    break;
            }
        }
    }
    
    public static void main(String[] args)
    {
        try       
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse (new InputSource("PruebaXMLDocument.xml"));
            PruebaXMLDocumentScanner scanner = new PruebaXMLDocumentScanner(document);
            scanner.visitDocument();
        }
        catch(Exception e)
        {
            System.out.println("Error de procesamiento");
            e.printStackTrace();
        }
    }
}
