/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Practica1_6;
import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import java.util.Scanner;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/**
 *
 * @author siuxoes
 */
public class EscritoresScanner {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;
    private Scanner in = new Scanner(System.in);
    /**
     * Create new EscritoresScanner with org.w3c.dom.Document.
     */
    
    /**
     * Scan through org.w3c.dom.Document document.
     */
    public void visitDocument() {
        org.w3c.dom.Element element = document.getDocumentElement();
        if ((element != null) && element.getTagName().equals("escritores")) {
            visitElement_escritores(element);
        }
        if ((element != null) && element.getTagName().equals("escritor")) {
            visitElement_escritor(element);
        }
        if ((element != null) && element.getTagName().equals("nombre")) {
            visitElement_nombre(element);
        }
        if ((element != null) && element.getTagName().equals("nacimiento")) {
            visitElement_nacimiento(element);
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named escritores.
     */
    void visitElement_escritores(org.w3c.dom.Element element) {
        // <escritores>
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
                    if (nodeElement.getTagName().equals("escritor")) {
                        visitElement_escritor(nodeElement);
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
     * Scan through org.w3c.dom.Element named escritor.
     */
    void visitElement_escritor(org.w3c.dom.Element element) {
        // <escritor>
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
                    if (nodeElement.getTagName().equals("nacimiento")) {
                        visitElement_nacimiento(nodeElement);
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
                    // ((org.w3c.dom.Text)node).getData();
                    break;
            }
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named nacimiento.
     */
    void visitElement_nacimiento(org.w3c.dom.Element element) {
        // <nacimiento>
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
                    // ((org.w3c.dom.Text)node).getData();
                    break;
            }
        }
    }
    
    public void añadirElemento(){
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList nodes = document.getElementsByTagName("escritores");
            System.out.println("Indique el nombre del elemento que desea poner a todos: ");
            String elemento = in.nextLine();
            for (int i = 0; i <= nodes.getLength(); i++) {
                Node escritor = document.getElementsByTagName("escritor").item(i);
                Element usuario = document.createElement(elemento);
                System.out.println("Ingrese el valor del atributo creado para el escritor: " + document.getElementsByTagName("nombre").item(i).getTextContent());
                usuario.appendChild(document.createTextNode(in.nextLine()));
                escritor.appendChild(usuario);
            }
            Source source= new DOMSource(document);
            Result result = new StreamResult(new File("escritores.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,result);
        }
        catch(Exception e)
        {
            System.out.println("Error de procesamiento");
        }
    }
    
    public void eliminarElemento(){
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList escritor = document.getElementsByTagName("escritor");
            System.out.println("Indique la id que desea Eliminar: entre 0 y " + (escritor.getLength() - 1));
            int elemento = Integer.parseInt(in.nextLine());
            Node nodo = document.getElementsByTagName("escritor").item(elemento);
            Node parent = nodo.getParentNode();
            parent.removeChild(nodo);
            
            Source source= new DOMSource(document);
            Result result = new StreamResult(new File("escritores.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,result);
        }
        catch(Exception e)
        {
            System.out.println("Error de procesamiento");
        }
    }
    
    public void modificarDato(){
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList escritor = document.getElementsByTagName("escritor");
            System.out.println("Indique la id que desea modificar: entre 0 y " + (escritor.getLength() - 1));
            int elemento = Integer.parseInt(in.nextLine());
            NodeList actual = escritor.item(elemento).getChildNodes();
            for(int i=0;i<actual.getLength();i++){
                if (actual.item(i).hasChildNodes()) {
                    System.out.println(actual.item(i).getNodeName());
                }
            }
            System.out.println("Que dato de los listados desea modificar: ");
            String input = in.nextLine();
            for(int i=0;i<actual.getLength();i++){
                if (input.equalsIgnoreCase(actual.item(i).getNodeName())) {
                    System.out.println("Introduzca el nuevo valor: ");
                    actual.item(i).setTextContent(in.nextLine());
                }
            }
            Source source= new DOMSource(document);
            Result result = new StreamResult(new File("escritores.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,result);
        }
        catch(Exception e)
        {
            System.out.println("Error de procesamiento");
            e.printStackTrace();
        }
    }
    
    public void listarEscritor() {
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList escritor = document.getElementsByTagName("escritor");
            System.out.println("Introduzca el nombre: ");
            String nombre = in.nextLine();
            for(int i=0;i<escritor.getLength();i++){
                if (escritor.item(i).getNodeType()==Node.ELEMENT_NODE) { 
                    Element elemento = (Element) escritor.item(i);
            if(elemento.getElementsByTagName("nombre").item(0).getTextContent().equalsIgnoreCase(nombre)){ //visualiza anotación
                System.out.println(elemento.getNodeName().toString() + ": "  + elemento.getTextContent()); //visualiza contenido completo
            }

        }
            }
          
        }catch(Exception e)
        {
            System.out.println("Error de procesamiento");
            e.printStackTrace();
        }
    }
    
    public void listarTodos() {
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList escritor = document.getElementsByTagName("escritor");
            for(int i=0;i<escritor.getLength();i++){
                NodeList actual = escritor.item(i).getChildNodes();
                for(int j=0;j<actual.getLength();j++){
                    Node este = actual.item(j);
                    if (este.hasChildNodes()) {
                        System.out.println(este.getNodeName()  +  "  : " + este.getTextContent());
                    }
                }
            }      
        }catch(Exception e)
        {
            System.out.println("Error de procesamiento");
            e.printStackTrace();
        }
    }
    
    public void listarRecursivo(){
        try{
             DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList escritor = document.getElementsByTagName("escritor");
            for(int i=0;i<escritor.getLength();i++){
                if(escritor.item(i).hasChildNodes()){
                System.out.println(escritor.item(i).getTextContent());
                }
            }
        }catch(Exception e){
        }
    }
    
    public static void main(String[] args){
        EscritoresScanner escritoresScanner = new EscritoresScanner();
        //escritoresScanner.añadirElemento();
        //escritoresScanner.eliminarElemento();
        escritoresScanner.modificarDato();
        //escritoresScanner.listarEscritor();
        //escritoresScanner.listarRecursivo();
    }
    
}
