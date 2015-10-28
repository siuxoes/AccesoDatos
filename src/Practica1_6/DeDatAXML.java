/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Practica1_6;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.*;
/**
 *
 * @author siuxoes
 */
public class DeDatAXML {
    
    private File file = new File("departamentos.dat");
    
    public void CrearElemento(String dato, String valor, Element raiz, Document document)
    {
        Element element= document.createElement(dato);
        Text text= document.createTextNode(valor);
        raiz.appendChild(element);
        element.appendChild(text);
    }
    
    public void crearArchivo(){
        DataInputStream dataInputStream = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document document=null;
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document=implementation.createDocument(null, "Departamentos", null);
            document.setXmlVersion("1.0");
            if (file.exists()) {
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                while (true) {
                    String numero = String.valueOf(dataInputStream.readInt());
                    String nombre = dataInputStream.readUTF();
                    Element raiz=document.createElement("departamento");
                    document.getDocumentElement().appendChild(raiz);
                    CrearElemento("numero",numero,raiz,document);
                    CrearElemento("nombre",nombre,raiz,document);
                }
            }
        } catch (EOFException eofe) {
            
        } catch (IOException ioe) {
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DeDatAXML.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Source source= new DOMSource(document);
            Result result = new StreamResult(new File("Departamentos.xml"));
            
            Transformer transformer;
            try {
                transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source,result);
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(DeDatAXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(DeDatAXML.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public static void main(String[] args){
        DeDatAXML deDatAXML = new DeDatAXML();
        deDatAXML.crearArchivo();
    }
}
