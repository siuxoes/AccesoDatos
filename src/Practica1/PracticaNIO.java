package Practica1;

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

    public void crearDirectorio() throws IOException {
        System.out.println("Indique la ruta donde desea crear el directorio: ");
        String ruta = in.nextLine();
        System.out.println("Indique el nombre del directorio: ");
        String dir = in.nextLine();
        Path path = Paths.get(ruta, dir);
        files.createDirectory(path);
    }

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
        //practicaNIO.crearDirectorio();
        //practicaNIO.copiarArchivo();
        //practicaNIO.moverArchivo();
        practicaNIO.eliminarArchivo();
    }
}
