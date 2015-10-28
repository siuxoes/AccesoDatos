/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_6;

import Practica1_4.Persona;
import com.thoughtworks.xstream.XStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Leonte
 */
public class ArrayAXMLXSTREAM {
    private final File file = new File("Personas.obs");

     public ArrayList pasarAArrayList(){
        ArrayList<Persona> arrayList = new ArrayList<>();
        
        ObjectInputStream objectInputStream = null;
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true){
                Persona persona = (Persona)objectInputStream.readObject();
                arrayList.add(persona);
            }
        } catch (FileNotFoundException e) {

        } catch(EOFException eofe){

        } catch (IOException | ClassNotFoundException e) {

        }
            return arrayList;
    }
     
     
    public void crearXML(){
        XStream xstream=new XStream();
        ArrayList<Persona> lista = pasarAArrayList();
        xstream.alias("Persona", Persona.class);
        try {
            xstream.toXML(lista, new FileOutputStream("Personas2.xml"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArrayAXMLXSTREAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        ArrayAXMLXSTREAM arrayAXMLXSTREAM = new ArrayAXMLXSTREAM();
        arrayAXMLXSTREAM.crearXML();
    }
}
