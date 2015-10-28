/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Practica1_6;

import Practica1_4.Persona;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author siuxoes
 */
public class XMLaBinario {
    private final File file = new File("Escritores.obs");
    
    public void crearBinario(){
        ObjectOutputStream objectOutputStream;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("escritores.xml"));
            NodeList escritor = document.getElementsByTagName("escritor");
            for(int i=0;i<escritor.getLength();i++){           
                    String nombre = document.getElementsByTagName("nombre").item(i).getTextContent();
                    String fecha = document.getElementsByTagName("nacimiento").item(i).getTextContent();
                    int edad = Integer.parseInt(document.getElementsByTagName("edad").item(i).getTextContent());  
                    objectOutputStream.writeObject(new Escritor(nombre, fecha, edad));
            }
             objectOutputStream.close();
        }catch(Exception e){
        }
    }
    
    public void leerArchivoBinario(){
        ObjectInputStream objectInputStream = null;
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true){
                Escritor escritor = (Escritor)objectInputStream.readObject();
                System.out.println(escritor.toString());
            }
        } catch (FileNotFoundException e) {

        } catch(EOFException eofe){

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
    }
    
    public static void main(String[] args){
        XMLaBinario xMLaBinario = new  XMLaBinario();
        //xMLaBinario.crearBinario();
        xMLaBinario.leerArchivoBinario();
    }
}
