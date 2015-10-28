/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_6;

import java.io.Serializable;

/**
 *
 * @author siuxoes
 */
public class Escritor implements Serializable{
    
    private String nombre;
    private String fNacimiento;
    private int edad;

    @Override
    public String toString() {
        return "Escritor{" + "nombre=" + nombre + ", fNacimiento=" + fNacimiento + ", edad=" + edad + '}';
    }

    public Escritor(String nombre, String fNacimiento, int edad) {
        this.nombre = nombre;
        this.fNacimiento = fNacimiento;
        this.edad = edad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fNacimiento
     */
    public String getfNacimiento() {
        return fNacimiento;
    }

    /**
     * @param fNacimiento the fNacimiento to set
     */
    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
}
