/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Sebastian Leonte
 */
public class LectorXMLSAX extends DefaultHandler {
 

 public static void main(String[] args) throws SAXException, IOException { 

 XMLReader lectorXML=XMLReaderFactory.createXMLReader(); 

 GestionContenido gestor= new GestionContenido(); 

 lectorXML.setContentHandler(gestor); 

 InputSource ficheroXML =new InputSource("escritores.xml"); 

 lectorXML.parse(ficheroXML); 

 }
}
