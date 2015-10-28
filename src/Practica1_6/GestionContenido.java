/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sebastian Leonte
 */

public class GestionContenido extends DefaultHandler { 

public GestionContenido (){ 

 super(); 

} 

 @Override 

 public void startDocument(){ 

 System.out.println("Comienzo del documento"); 

} 

 @Override 

public void endDocument(){ 

 System.out.println("Fin del documento"); 

 } 

public void startElement(String ruta,String nombre,String nombreC,Attributes atts){ 

 System.out.println("Inicio del elemento " + nombre); 

 } 

@Override 

public void endElement(String ruta, String nombre, String nombreC){ 

 System.out.println("Fin Elemento " + nombre); 

 } 

 @Override
 public void characters(char[] ch,int inicio, int longitud)throws SAXException{ 

 String car = new String(ch,inicio,longitud); 

 car=car.replaceAll("[\t\n]", ""); 

 System.out.println(car); 

 } 
}
