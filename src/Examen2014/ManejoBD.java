/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Examen2014;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author siuxoes
 */
public class ManejoBD {
    
    protected Connection getConnection() throws Exception {
        return (Connection)DriverManager.getConnection(
                "jdbc:mysql://localhost/gestionpeliculas", "root",
                "");
    }
    
    public void insercionInicial(){
        try {
            String titulos[] ={"Vertigo", "Atrapame si puedes", "Los hombres que no amaban a las mujeres", "Pulp Fiction", "Psicosis", "El Señor de la guerra", "Delicatessen", "El club de los poetas muertos"};
            int anios[] = {1955,2002,2011,1994,1960,2005,1991,1989};
            for(int i=0; i<titulos.length; i++){
                String instruccion = "insert into peliculas values(\""+i+"\", \""+titulos[i]+"\",\""+anios[i]+"\")";
                enviar(instruccion);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertar(String titulo, int anio){
        try {
            int index = ultimoIndex() + 1;
            String instruccion = "insert into peliculas values(\""+index+"\", \""+titulo+"\",\""+anio+"\")";
            enviar(instruccion);
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int eliminar(String titulo, int anio){
        int a = 0;
        try {
            int index = ultimoIndex() + 1;
            String instruccion = "delete from peliculas where titulo = \""+titulo+"\" and anio=\""+anio+"\"";
            a = enviarRetornoFilasAfectadas(instruccion);
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    
    public int ultimoIndex(){
        int index = 0;
        try{
            Connection con = getConnection();
            Statement st = (Statement) con.createStatement();
            String query = "select codPeli from peliculas order by codPeli desc limit 1";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                index = Integer.parseInt(rs.getString(1));
            }
        }catch(Exception e){}
        return index;
    }
    
    public void enviar(String instruccion) throws SQLException {
        Statement st = null;
        try {
            Connection con = getConnection();
            String query = instruccion;
            st = (Statement) con.createStatement();
            int a = st.executeUpdate(query);
            System.out.println(a);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            st.close();
        }
    }
    
    public void listado(int anio){
     int contador = 0;
     try{
            Connection con = getConnection();
            Statement st = (Statement) con.createStatement();
            System.out.printf("LISTADO DE PELICULAS "
                    + "POSTERIORES AL AÑO %d\n", anio);
            System.out.printf("%-20s%-20s%-20s\n", 
                    "CODIGO", "TITULO", "AÑO");
            String query = "select codPeli, titulo, anio "
                    + "from peliculas where anio=\""+anio+"\"";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.printf("%-20s%-20s%-"
                        + "20s\n", rs.getString(1), rs.getString(2), rs.getString(3));
                contador++;
                rs.next();
            }
            System.out.printf("Total de películas "
                    + "posteriores a %d: %d\n", anio, contador);
        }catch(Exception e){}
    }
    
    public int enviarRetornoFilasAfectadas(String instruccion) throws SQLException{
        Statement st = null;
        int a = 0;
        try {
            Connection con = getConnection();
            String query = instruccion;
            st = (Statement) con.createStatement();
            a = st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            st.close();
        }
        return a;
    }
    
    public static void main(String[] args) {
        try {
            ManejoBD bD = new ManejoBD();
            //Súper menú de opciones
            //bD.insercionInicial(); SOLO UNA VEZ WEY
            //bD.insertar("Hola2", 2015);
            //System.out.println(bD.eliminar("Hola2", 2015));
            //bD.listado(1994);
        } catch (Exception ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
