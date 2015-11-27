/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen2014;

import java.io.Serializable;

/**
 *
 * @author Sebastian Leonte
 */
public class Pelicula implements Serializable{
    
    private int codPeli;
    private String titulo;

    @Override
    public String toString() {
        return "Pelicula{" + "codPeli=" + codPeli + ", titulo=" + titulo + ", anio=" + anio + '}';
    }
    private int anio;


    public Pelicula(int codPeli, String titulo, int anio) {
        this.codPeli = codPeli;
        this.titulo = titulo;
        this.anio = anio;
    }

    public Pelicula() {
    }

    public int getCodPeli() {
        return codPeli;
    }

    public void setCodPeli(int codPeli) {
        this.codPeli = codPeli;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
}
