
package Practica1_2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Sebastian Leonte
 */
public class TrabajarFicherosBinarios {

    private Scanner in = new Scanner(System.in);

    public void crearBinarioPrecios() {
        File fichero = new File("precios.bin");
        DataOutputStream outputStream = null;
        try {
            if (fichero.exists()) {
                System.out.println("el archivo existe, se reemplazará");
                outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero, true)));
            } else {
                outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)));
            }
            double numero;
            do {
                numero = 0;
                System.out.println("ingrese el numero que desea ingresar, -1 para salir: ");
                numero = Double.parseDouble(in.nextLine());
                outputStream.writeDouble(numero);
            } while (numero != -1);
            outputStream.close();
        } catch (IOException ioe) {

        }
    }

    public void leerBinario() {
        File fichero = new File("precios.bin");
        DataInputStream dataInputStream = null;
        int totalDatos = 0;
        double total = 0;
        try {
            if (fichero.exists()) {
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)));
                while (true) {
                    double valor = dataInputStream.readDouble();
                    System.out.println("Valor: " + valor);
                    totalDatos++;
                    total += valor;
                }
            } else {
                System.out.println("el archivo no existe, se creera: ");
                crearBinarioPrecios();

            }
        } catch (EOFException eofe) {

        } catch (IOException ioe) {

        } finally {
            System.out.println("Numeros totales: " + totalDatos);
            System.out.println("Media: " + (double) (total / (double) totalDatos));
        }
    }

    public void departamento() {
        File fichero = new File("departamentos.dat");
        DataOutputStream outputStream = null;
        try {
            if (fichero.exists()) {
                System.out.println("el archivo existe, se reemplazará");
                outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero, true)));
            } else {
                outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)));
            }
            String departamento;
            int numero;
            String seguir;
            do {
                System.out.println("Introduzca el numero de departamento");
                numero = Integer.parseInt(in.nextLine());
                outputStream.writeInt(numero);
                System.out.println("Introduzca el nombre de departamento");
                departamento = in.nextLine();
                outputStream.writeUTF(departamento);
                System.out.println("¿Desea seguir? si/no");
                seguir = in.nextLine();
            } while (seguir.equalsIgnoreCase("si"));
            outputStream.close();
        } catch (IOException ioe) {

        }
    }

    public void buscarDepartamento(int num) {
        File fichero = new File("departamentos.dat");
        DataInputStream dataInputStream = null;
        String nombreDepartamento = "No encontrado";
        boolean encontrado = false;
        try {
            if (fichero.exists()) {
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)));
                while (true && !encontrado) {
                    int numero = dataInputStream.readInt();
                    String nombre = dataInputStream.readUTF();
                    if (numero == num) {
                        nombreDepartamento = nombre;
                    }
                }
            } else {
                System.out.println("el archivo no existe, se creera: ");
                crearBinarioPrecios();
            }
        } catch (EOFException eofe) {

        } catch (IOException ioe) {

        } finally {
            System.out.println(num + ", " + nombreDepartamento);
        }
    }

    public void articulos() {
        File fichero = new File("articulos.dat");
        DataOutputStream outputStream = null;
        try {
            if (fichero.exists()) {
                System.out.println("el archivo existe, se reemplazará");
                outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero, true)));
            } else {
                outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)));
            }
            DataInputStream dataInputStream = null;
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("precios.bin")));
                while (true) {
                    double valor = dataInputStream.readDouble();
                    System.out.println("Ingrese el nombre del producto: ");
                    outputStream.writeUTF(in.nextLine());
                    outputStream.writeDouble(valor);
                }

            } catch (EOFException eofe) {

            } catch (IOException ioe) {

            } finally {
                outputStream.close();

            }

        } catch (IOException ioe) {

        }
    }

    public void leerArticulos() {
        File fichero = new File("articulos.dat");
        DataInputStream dataInputStream = null;
        try {
            if (fichero.exists()) {
                System.out.println("Articulo           Precio");
                System.out.println("--------------------------");
                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)));
                while (true) {
                    String nombre = dataInputStream.readUTF();
                    double valor = dataInputStream.readDouble();
                    System.out.println(nombre + "                " + valor);
                }
            } else {
            }
        } catch (EOFException eofe) {
        } catch (IOException ioe) {
        } finally {
        }
    }

    public static void main(String[] args) {
        TrabajarFicherosBinarios trabajarFicherosBinarios = new TrabajarFicherosBinarios();
        //trabajarFicherosBinarios.crearBinarioPrecios();
        //trabajarFicherosBinarios.leerBinario();
        trabajarFicherosBinarios.departamento();
        //trabajarFicherosBinarios.buscarDepartamento(125);
        //trabajarFicherosBinarios.articulos();
        //trabajarFicherosBinarios.leerArticulos();
    }
}
