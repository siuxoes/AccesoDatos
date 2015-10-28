package Practica1_4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by siuxoes on 10/3/15.
 */
public class GestionPersonas {


    private final Scanner in = new Scanner(System.in);
    private final File file = new File("Personas.obs");
    
    public void escrituraInicial() {
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            String dnis[] = {"83487622", "13481112", "22356622", "33455614", "77733182", "83765629", "22487611", "56787682", "80087600", "53077609"};
            String nombres[] = {"Ana", "Javier", "Luisa", "Tomás", "Julio", "Pedro", "Rocio", "German", "Maria", "Serafín"};
            int edades[] = {17, 22, 19, 15, 20, 26, 25, 19, 17, 20};
            for (int i = 0; i < dnis.length; i++) {
                objectOutputStream.writeObject(new Persona(dnis[i], nombres[i], edades[i]));
            }
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void creacionInicial() {
        if (file.exists()) {
            System.out.println("El archivo ya existe, ¿desea reemplazar? SI/NO");
            if (in.nextLine().equalsIgnoreCase("Si")) {
                escrituraInicial();
            }
        }else {
            escrituraInicial();
        }
    }

    public void datosPersonaSegunDNI(){
        ObjectInputStream objectInputStream = null;
        boolean encontrado = false;
        System.out.println("Ingrese el DNI a buscar: ");
        String dni = in.nextLine();
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true && !encontrado){
                Persona persona = (Persona)objectInputStream.readObject();
                if(persona.getDni().equalsIgnoreCase(dni)){
                    System.out.println(persona.toString());
                    encontrado = true;
                }
            }
        } catch (FileNotFoundException e) {

        } catch(EOFException eofe){

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }finally{
            if(!encontrado){
                System.out.println("Persona con DNI: " + dni + " No encontrada \n");
            }
        }
    }

    public void datosPersonaMayor(){
        ObjectInputStream objectInputStream = null;
        int edadMayor = 0;
        Persona p = null;
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true ){
                Persona persona = (Persona)objectInputStream.readObject();
                if(persona.getEdad() >= edadMayor){
                    edadMayor = persona.getEdad();
                    p = persona;
                }
            }
        } catch (FileNotFoundException e) {

        } catch(EOFException eofe){

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }finally{
           System.out.println(p.toString() + "\n");
        }
    }

    public void listadoPersonasRangoEdades(){
        ObjectInputStream objectInputStream = null;
        System.out.println("Introduce la edad menor: ");
        int edad1 = Integer.parseInt(in.nextLine());
        System.out.println("Introduce la edad mayor: ");
        int edad2 = Integer.parseInt(in.nextLine());
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true ){
                Persona persona = (Persona)objectInputStream.readObject();
                if(persona.getEdad() >= edad1 && persona.getEdad() <= edad2){
                    System.out.println(persona.toString());
                }
            }
        } catch (FileNotFoundException e) {

        } catch(EOFException eofe){

        } catch (IOException | ClassNotFoundException e) {

        }finally{

        }
    }

    public void mediaEdades(){
        ObjectInputStream objectInputStream = null;
        int cont = 0;
        int suma = 0;
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true){
                Persona persona = (Persona)objectInputStream.readObject();
                cont++;
                suma += persona.getEdad();
            }
        } catch (FileNotFoundException e) {

        } catch(EOFException eofe){

        } catch (IOException | ClassNotFoundException e) {

        }finally{
            System.out.println("La media de edades es: " + (double)(suma / cont) + "\n");
        }
    }

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

        }finally {
            return arrayList;
        }
    }
    
    public void arraylistAFichero(ArrayList<Persona> arrayList){
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            for(Persona p: arrayList){
                objectOutputStream.writeObject(p);
            }
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void añadirPersona(){
        ArrayList<Persona> arrayList = pasarAArrayList();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            System.out.println("Ingrese el nombre: ");
            String nombre = in.nextLine();
            System.out.println("Ingrese el dni: ");
            String dni = in.nextLine();
            System.out.println("Ingrese la edad: ");
            int edad = Integer.parseInt(in.nextLine());
            for(Persona p: arrayList){
                objectOutputStream.writeObject(p);
            }
            objectOutputStream.writeObject(new Persona(dni, nombre, edad));
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void eliminarPersona(){
        ArrayList<Persona> arrayList = pasarAArrayList();
        ObjectOutputStream objectOutputStream = null;
        String dni;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            System.out.println("Ingrese el dni de la persona a borar: ");
            dni = in.nextLine();
            Persona persona = null;
            for(Persona p: arrayList){
                if(p.getDni().equalsIgnoreCase(dni)){
                    persona = p;
                    break;
                }
            }
            if(persona != null){
                arrayList.remove(persona);
            }else{
                System.out.println("persona con dni: " + dni + " no encotrada");
            }
            for(Persona p: arrayList){
                objectOutputStream.writeObject(p);
            }
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        GestionPersonas gestionPersonas = new GestionPersonas();
        gestionPersonas.creacionInicial();
        int opcion;
        do{
            System.out.println("1. Consultas");
            System.out.println("2. Mantenimiento");
            System.out.println("3. Salida");
            opcion = Integer.parseInt(gestionPersonas.in.nextLine());
            if(opcion == 1){
                do {
                    System.out.println("1. Mostrar los datos de una persona dado su DNI.");
                    System.out.println("2. Mostar los datos de la Persona con mayor edad en el fichero.");
                    System.out.println("3. Listado de Personas comprendidas en un rango de edades.");
                    System.out.println("4. Media de edad en el fichero.");
                    System.out.println("5. Salir del submenú.");
                    opcion = Integer.parseInt(gestionPersonas.in.nextLine());
                    switch(opcion){
                        case 1:
                            gestionPersonas.datosPersonaSegunDNI();
                            break;
                        case 2:
                            gestionPersonas.datosPersonaMayor();
                            break;
                        case 3:
                            gestionPersonas.listadoPersonasRangoEdades();
                            break;
                        case 4:
                            gestionPersonas.mediaEdades();
                            break;
                        case 5:
                            break;
                    }
                }while(opcion!=5);
            } else if(opcion == 2){
                do {
                    System.out.println("1. Alta de una persona.");
                    System.out.println("2. Baja de una persona.");
                    System.out.println("3. Salir del submenú.");
                    opcion = Integer.parseInt(gestionPersonas.in.nextLine());
                    switch (opcion){
                        case 1:
                            gestionPersonas.añadirPersona();
                            break;
                        case 2:
                            gestionPersonas.eliminarPersona();
                            break;
                        case 3:
                            break;
                    }
                }while(opcion!=3);
                opcion = 0;
            }
        }while(opcion != 3);
    }
}
