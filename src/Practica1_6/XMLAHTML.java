/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Practica1_6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Sebastian Leonte
 */
public class XMLAHTML {
    public static void main(String[] args) throws IOException {
        
        String hojaEstilo="Estilo.xsl";
        
        String cds="musica.xml";

        
        File pagHTML = new File("miPaginaMusica.html");
        
        FileOutputStream os = new FileOutputStream(pagHTML);
        
        Source estilos = new StreamSource(hojaEstilo);

        
        Source datos=new StreamSource(cds);
        
        
        Result result= new StreamResult(os);
        
        try{
            
            Transformer transformer =
                    
                    TransformerFactory.newInstance().newTransformer(estilos);
            
            transformer.transform(datos, result);
            
        }catch (Exception e){System.out.println("Error: "+e);}
        
        
        
    } 
}
