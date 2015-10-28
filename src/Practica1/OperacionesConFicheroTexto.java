/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siuxoes
 */
public class OperacionesConFicheroTexto {
    
    private Scanner in = new Scanner(System.in);
    
    public void copiarFicheroACopia() throws IOException{
    System.out.println("Indique la ruta del fichero de texto a leer: ");
        String ruta = in.nextLine();
        File fichero = new File(ruta);
        String absolute = fichero.getAbsolutePath();
        String filepath = absolute.substring(0,absolute.lastIndexOf(File.separator))+"\\";
        if(fichero.exists() && fichero.isFile()){
            File copia = new File(filepath, "copia.txt");
            if(!copia.exists()){
                copia.createNewFile();
            }
            System.out.println(copia.getAbsolutePath());
            BufferedReader bufferedReader = null;
            PrintWriter printWriter = null;
                try{
                    bufferedReader = new BufferedReader(new FileReader(fichero));
                    printWriter = new PrintWriter(new FileWriter(copia), true);
                    String l;
                    while((l = bufferedReader.readLine()) != null){
                    printWriter.println(l);
                            }
                    printWriter.close();
                }catch(FileNotFoundException fnfe){}
                catch(IOException ioe){}
        }else{
            System.out.println("El fichero no existe o no es un fichero");
        }
    }
    
    public void añadirLineaFinalArchivo() throws IOException{
   System.out.println("Indique la ruta del fichero de texto a leer: ");
        String ruta = in.nextLine();
        File fichero = new File(ruta);
        if(fichero.exists() && fichero.isFile()){
            BufferedReader bufferedReader = null;
            PrintWriter printWriter = null;
                try{
                    bufferedReader = new BufferedReader(new FileReader(fichero));
                    printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fichero, true)));
                    printWriter.println("\n" + in.nextLine());                   
                    printWriter.close();
                }catch(FileNotFoundException fnfe){}
                catch(IOException ioe){}
        }else{
            System.out.println("El fichero no existe o no es un fichero");
        }
    }
    
    public void leerFicheroTexto(){
        System.out.println("Indique la ruta del fichero de texto a leer: ");
        String ruta = in.nextLine();
        File fichero = new File(ruta);
        if(fichero.exists() && fichero.isFile()){
            BufferedReader bufferedReader = null;
                try{
                    bufferedReader = new BufferedReader(new FileReader(fichero));
                    String l;
                    while((l = bufferedReader.readLine()) != null){
                    System.out.println(l);
                            }
                }catch(FileNotFoundException fnfe){}
                catch(IOException ioe){}
        }else{
            System.out.println("El fichero no existe o no es un fichero");
        }
    }
    
    public void provincias() throws IOException{
        String prov[]={"Albacete", "Avila", "Alicante", "Badajoz", "Barcelona", "Bilbao", "Caceres", "Cádiz", "Corboba", "Huelva", "Sevilla", "Soria", "Toledo", "Valencia", "Zamora", "Zaragoza"}; 
        System.out.println("Indique la ruta del fichero a escribir: ");
        String ruta = in.nextLine();
        File fichero = new File(ruta);
        fichero.createNewFile();
        if(fichero.exists() && fichero.isFile()){
           PrintWriter printWriter = null;
                try{
                    printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));
                    for(String s: prov){
                        printWriter.print(s+" ");
                    }
                    printWriter.close();
                }catch(FileNotFoundException fnfe){}
                catch(IOException ioe){}
        }else{
            System.out.println("El fichero no existe o no es un fichero");
        }
    }
    
    public void abcedario() throws IOException{
        System.out.println("Indique la ruta del fichero a escribir: ");
        String ruta = in.nextLine();
        File fichero = new File(ruta);
        fichero.createNewFile();
        if(fichero.exists() && fichero.isFile()){
           PrintWriter printWriter = null;
                try{
                    printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));
                    for(char s='A';s<='Z';s++){
                        printWriter.println(s+","+Character.toLowerCase(s));
                    }
                    printWriter.close();
                }catch(FileNotFoundException fnfe){}
                catch(IOException ioe){}
        }else{
            System.out.println("El fichero no existe o no es un fichero");
        }
    }
    
    public static void main(String[] args) throws IOException{
        OperacionesConFicheroTexto operacionesConFicheroTexto = new OperacionesConFicheroTexto();
        
            //operacionesConFicheroTexto.leerFicheroTexto();
            //operacionesConFicheroTexto.copiarFicheroACopia();
            //operacionesConFicheroTexto.añadirLineaFinalArchivo();
           // operacionesConFicheroTexto.provincias();
        operacionesConFicheroTexto.abcedario();
     
    }
}
