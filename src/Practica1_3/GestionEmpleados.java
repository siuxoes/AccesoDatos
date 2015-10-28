/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Leonte
 */
public class GestionEmpleados {

    private Scanner in = new Scanner(System.in);

    public void escribirArchivoAccesoDirecto() {
        System.out.println("Indique el nombre del archivo a crear: ");
        File file = new File(in.nextLine() + ".bin");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            System.out.println("Indique el departamento: ");
            int departamento = Integer.parseInt(in.nextLine());
            System.out.println("Indique el salario: ");
            double salario = Double.parseDouble(in.nextLine());
            long longitudFichero = randomAccessFile.length();
            int numEmpleado = (int) (longitudFichero / 16) + 1;
            randomAccessFile.seek(longitudFichero);
            randomAccessFile.writeInt(numEmpleado);
            randomAccessFile.writeInt(departamento);
            randomAccessFile.writeDouble(salario);
            randomAccessFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerEmpleado(int numEmpleado) {
        System.out.println("Indique el nombre del fichero de donde leer el Empleado: ");
        File file = new File(in.nextLine() + ".bin");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            int longitudFichero = (int) randomAccessFile.length();
            if ((numEmpleado * 16) <= longitudFichero) {
                int pos = (numEmpleado - 1) * 16;
                randomAccessFile.seek(pos);
                System.out.println("Numero de empleado: " + randomAccessFile.readInt());
                System.out.println("Departamento: " + randomAccessFile.readInt());
                System.out.println("Salario: " + randomAccessFile.readDouble());

            } else {
                System.out.println("No existe ese empleado");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void detallesFichero() {
        System.out.println("Indique el nombre del fichero para obtener detalles: ");
        File file = new File(in.nextLine() + ".bin");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            System.out.println("Nombre del Fichero: " + file.getName().toString());
            // Tambien se puede hacer file.length()
            System.out.println("Tamaño del fichero: " + randomAccessFile.length() + " bytes");
            System.out.println("Numero de empleados: " + (int) randomAccessFile.length() / 16);
            System.out.println("Posicion Actual: " + randomAccessFile.getFilePointer());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarUnEmpleado(int numEmpleado) {
        System.out.println("Indique el nombre del fichero de donde leer el Empleado: ");
        File file = new File(in.nextLine() + ".bin");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            int longitudFichero = (int) randomAccessFile.length();
            if ((numEmpleado * 16) <= longitudFichero) {
                int pos = (numEmpleado - 1) * 16;
                randomAccessFile.seek(pos);
                System.out.println("Datos Antiguos:");
                System.out.println("Numero de empleado: " + randomAccessFile.readInt());
                System.out.println("Departamento: " + randomAccessFile.readInt());
                System.out.println("Salario: " + randomAccessFile.readDouble());
                System.out.println("Indique el departamento: ");
                int departamento = Integer.parseInt(in.nextLine());
                System.out.println("Indique el salario: ");
                double salario = Double.parseDouble(in.nextLine());
                randomAccessFile.seek(pos);
                randomAccessFile.writeInt(numEmpleado);
                randomAccessFile.writeInt(departamento);
                randomAccessFile.writeDouble(salario);
                System.out.println("Datos Nuevos:");
                System.out.println("Numero de empleado: " + numEmpleado);
                System.out.println("Departamento: " + departamento);
                System.out.println("Salario: " + salario);
            } else {
                System.out.println("No existe ese empleado");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        gestionEmpleados.escribirArchivoAccesoDirecto();  
        //gestionEmpleados.detallesFichero();
        gestionEmpleados.modificarUnEmpleado(1);
        //gestionEmpleados.leerEmpleado(1);
    }
}
