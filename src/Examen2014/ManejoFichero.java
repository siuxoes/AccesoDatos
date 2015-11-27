/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Examen2014;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Leonte
 */
public class ManejoFichero{
    
    private static Scanner in = new Scanner(System.in);
    
    public void escribirInicial(File file){
        ObjectOutputStream objectOutputStream;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            String titulos[] ={"Vertigo", "Atrapame si puedes", "Los hombres que no amaban a las mujeres", "Pulp Fiction", "Psicosis", "El Señor de la guerra", "Delicatessen", "El club de los poetas muertos"};
            int anios[] = {1955,2002,2011,1994,1960,2005,1991,1989};
            for(int i=0; i<titulos.length;i++){
                objectOutputStream.writeObject(new Pelicula((i+1), titulos[i], anios[i]));
            }
            objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ManejoFichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean busquedaTitulo(String titulo, File file){
        ObjectInputStream inputStream;
        boolean encontrado = false;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(file));
            while(true || encontrado == true ){
                Pelicula pelicula = (Pelicula)inputStream.readObject();
                if(pelicula.getTitulo().equalsIgnoreCase(titulo)){
                    System.out.println(pelicula.toString());
                    encontrado = true;
                }
            }
        } catch (FileNotFoundException e) {
        } catch(EOFException eofe){
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }finally{
            return encontrado;
        }
    }
    
    public int busquedaAño(File file){
        ObjectInputStream inputStream;
        int anio = 0;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(file));
            while(true){
                Pelicula pelicula = (Pelicula)inputStream.readObject();
                if(pelicula.getAnio() < anio || anio == 0){
                    anio = pelicula.getAnio();
                }
            }
        } catch (FileNotFoundException e) {
            
        } catch(EOFException eofe){
            
        } catch (IOException e) {
            
        } catch (ClassNotFoundException e) {
            
        }finally{
            return anio;
        }
    }
    
    public static void main(String[] args) {
        try{
            ManejoFichero fichero = new ManejoFichero();
            System.out.println("Introduzca el nombre del fichero: ");
            File file = new File((in.nextLine() + ".obs"));
            if(file.exists()){
                System.out.println("El archivo existe, desea añadir mas registros? si/no ");
                if(in.nextLine().equalsIgnoreCase("si")){
                    fichero.escribirInicial(file);
                }else{
                    System.out.println("Entonces... ");
                    main(args);
                }
            }else{
                fichero.escribirInicial(file);
            }
            int opcion = 0;
            System.out.println("1 Ver los datos de una película dado un código de película dado por teclado");
            System.out.println("2 Año de producción de la película más antigua.");
            System.out.println("3 Salir");
            opcion = Integer.parseInt(in.nextLine());
            do{
                if(opcion == 1){
                    System.out.println("Nombre de la pelicula");
                    String titulo = in.nextLine();
                    System.out.println(titulo);
                    System.out.println(fichero.busquedaTitulo(titulo, file));
                }else if(opcion == 2){
                    System.out.println(fichero.busquedaAño(file));
                }
                System.out.println("1 Ver los datos de una película dado un código de película dado por teclado");
                System.out.println("2 Año de producción de la película más antigua.");
                System.out.println("3 Salir");
                opcion = Integer.parseInt(in.nextLine());
            }while(Integer.parseInt(in.nextLine()) != 3);
        }catch(Exception e){}
    }
    
}
