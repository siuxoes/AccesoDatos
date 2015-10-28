/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperacionesConFichero {

    private final Scanner in = new Scanner(System.in);

    public void mostrarFicherosDirectorio() {
        System.out.println("Por favor introduzca la ruta absoluta de un directorio: ");
        String rutaDirectorio = in.nextLine();
        File directorio = new File(rutaDirectorio);
        if (directorio.exists() && directorio.isDirectory()) {
            File[] listaFicheros = directorio.listFiles();
            for (File file : listaFicheros) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No existe o no es un directorio válido");
        }
    }

    public void mostrarDetallesFichero() {
        System.out.println("Introduzca la ruta de un fichero: ");
        String fichero = in.nextLine();
        File file = new File(fichero);
        if (file.exists() && file.isFile()) {
            System.out.println("Nombre: " + file.getName());
            System.out.println("Ruta: " + file.getPath());
            System.out.println("Ruta absoluta: " + file.getAbsolutePath());
            System.out.println("Permiso de lectura: " + file.canRead());
            System.out.println("Permiso de escritura: " + file.canWrite());
            System.out.format("Tamaño: %d bytes \n", file.length());
            System.out.println("Es directorio: " + file.isDirectory());
            System.out.println("Es fichero: " + file.isFile());
        } else {
            System.out.println("No existe fichero o no es un fichero");
        }
    }

    public void crearDirectorioY2Ficheros() {
        File dirInicial;
        do {
            System.out.println("Indique la ruta donde desea crear el directorio: ");
            String rutaInicial = in.nextLine();
            dirInicial = new File(rutaInicial);
        } while (!dirInicial.isDirectory());
        System.out.println("Indique el nombre del directorio a crear: ");
        String dirACrear = in.nextLine();
        File dirNuevo = new File(dirInicial, dirACrear);
        dirNuevo.mkdir();
        try {
            System.out.println("Indique el nombre del primer archivo: ");
            String nombre1 = in.nextLine();
            File fichero1 = new File(dirNuevo, nombre1);
            fichero1.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesConFichero.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Indique el nombre del segundo archivo: ");
        File fichero2 = new File(dirNuevo, in.nextLine());
        try {
            fichero2.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesConFichero.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarDirectorio() {
        File dir;
        do {
            System.out.println("Indique la ruta absoluta del directorio a borrar: ");
            dir = new File(in.nextLine());
        } while (!dir.exists() && !dir.isDirectory());
        String[] entries = dir.list();
        for (String s : entries) {
            File currentFile = new File(dir.getPath(), s);
            currentFile.delete();
        }
        dir.delete();
    }

    public void borrarDirRecursivo(File ficheroActual) {
        File[] lista = ficheroActual.listFiles();
        for (File f : lista) {
            if (f.isFile()) {
                f.delete();
                System.out.println("Borrando archivo: " + f.toString());
            } else {
                borrarDirRecursivo(f);
            }
        }
        ficheroActual.delete();
        System.out.println("Borrando carpeta: " + ficheroActual.toString());
    }

    public static void main(String[] args) {
        OperacionesConFichero operacionesConFichero = new OperacionesConFichero();
        // operacionesConFichero.mostrarFicherosDirectorio();
        // operacionesConFichero.mostrarDetallesFichero();
        //operacionesConFichero.crearDirectorioY2Ficheros();
        //operacionesConFichero.borrarDirectorio();
        File f = new File("C:\\holo");
        operacionesConFichero.borrarDirRecursivo(f);
    }
}
