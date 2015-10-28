package Practica1_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author Sebastian Leonte
 */
public class PracticaNIO {

    private Scanner in = new Scanner(System.in);
    private Files files;

    /**
     * Se crea un directorio teniendo en cuenta la ruta inicial
     * Se pide primero la ruta
     * y luego el nombre del directorio
     * @throws IOException 
     */
    public void crearDirectorio() throws IOException {
        System.out.println("Indique la ruta donde desea crear el directorio: ");
        String ruta = in.nextLine();
        if(files.exists(Paths.get(ruta))){
        System.out.println("Indique el nombre del directorio: ");
        String dir = in.nextLine();
        Path path = Paths.get(ruta, dir);
        files.createDirectory(path);
        }else{
            System.out.println("La ruta inicial no existe");
        }
    }

    /**
     * Se crea un directorio, se introduce la ruta completa de éste
     * Se crean todos los directorios no existentes hasta el directorio
     * final
     * @throws IOException 
     */
    public void crearEstructuraDirectorios() throws IOException {
        System.out.println("Indique la ruta de directorios que desea crear: ");
        String ruta = in.nextLine();
        Path path = Paths.get(ruta);
        files.createDirectories(path);
    }

    /**
     * Se copia el archivo a otra ruta
     * Se introduce la ruta del archivo y luego
     * la segunda ruta
     * @throws IOException 
     */
    public void copiarArchivo() throws IOException {
        System.out.println("Indique la ruta del archivo a copiar: ");
        String ruta = in.nextLine();
        Path path = Paths.get(ruta);
        if (files.exists(path)) {
            System.out.println("Indique la ruta de la carpeta de copiado: ");
            String copia = in.nextLine();
            Path destino = Paths.get(copia, path.getFileName().toString());
            files.copy(path, destino);
        } else {
            System.out.println(ruta + " No existe");
        }
    }
    /**
     * Mueve un archivo a otro directorio
     * Pide la ruta del archivo a mover
     * y luego la segunda ruta
     * @throws IOException 
     */
    public void moverArchivo() throws IOException {
        System.out.println("Indique la ruta del archivo a copiar: ");
        String ruta = in.nextLine();
        Path path = Paths.get(ruta);
        if (files.exists(path)) {
            System.out.println("Indique la ruta de la carpeta para mover: ");
            String copia = in.nextLine();
            Path destino = Paths.get(copia, path.getFileName().toString());
            files.move(path, destino);
        } else {
            System.out.println(ruta + " No existe");
        }
    }

    /**
     * Elimina un archivo en una ruta determinada
     * @throws IOException 
     */
    public void eliminarArchivo() throws IOException {
        System.out.println("Indique la ruta del archivo a eliminar: ");
        String ruta = in.nextLine();
        Path path = Paths.get(ruta);
        if (files.exists(path)) {
            files.delete(path);
        } else {
            System.out.println(ruta + " No existe");
        }
    }

    public static void main(String[] args) throws IOException {
        PracticaNIO practicaNIO = new PracticaNIO();
        String respuestaControl = "si";
        do {
            System.out.println("Opciones: \n"
                    + "Crear directorio: 1 \n"
                    + "Crear Estructura de directorios: 2 \n"
                    + "Copiar archivo: 3 \n"
                    + "Mover archivo: 4 \n"
                    + "Eliminar archivo: 5 \n ");
            String opcion = practicaNIO.in.nextLine();
            switch (opcion) {
                case "1":
                    practicaNIO.crearDirectorio();
                    break;
                case "2":
                    practicaNIO.crearEstructuraDirectorios();
                    break;
                case "3":
                    practicaNIO.copiarArchivo();
                    break;
                case "4":
                    practicaNIO.moverArchivo();
                    break;
                case "5":
                    practicaNIO.eliminarArchivo();
                    break;
            }
            System.out.println("¿Desea continuar? Si/no");
            respuestaControl = practicaNIO.in.nextLine();
        } while (respuestaControl.equalsIgnoreCase("si"));

    }
}
