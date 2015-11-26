/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package appHibernateSebastianLeonte;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class Main {
    
    public static void insertar(){
        SessionFactory session = SessionFactoryUtil.getSessionFactory();
        Session s = session.openSession();
        Transaction transaction = s.beginTransaction();
        System.out.println("Insertando");
        Vuelos vuelos = new Vuelos();
        vuelos.setCodVuelo("AB-BY-4811");
        vuelos.setHoraSalida("02/04/99-14:30");
        vuelos.setDestino("Paris");
        vuelos.setProcedencia("Madrid");
        vuelos.setPlazasFumador(100);
        vuelos.setPlazasNoFumador(100);
        vuelos.setPlazasPrimera(100);
        vuelos.setPlazasTurista(100);
        s.save(vuelos);
        transaction.commit();
        s.close();
        session.close();
    }
    public static void eliminar(){
        SessionFactory session = SessionFactoryUtil.getSessionFactory();
        Session s = session.openSession();
        Transaction transaction = s.beginTransaction();
        System.out.println("Elimiando");
        Vuelos vuelos = (Vuelos)s.load(Vuelos.class, (String)"AB-BY-4811");
        s.delete(vuelos);
        transaction.commit();
        s.close();
        session.close();
    }
    
    public static void modificar(){
        SessionFactory session = SessionFactoryUtil.getSessionFactory();
        Session s = session.openSession();
        Transaction transaction = s.beginTransaction();
        System.out.println("Modificando");
        Vuelos vuelos = (Vuelos)s.load(Vuelos.class, (String)"AB-BY-4811");
        vuelos.setProcedencia("Hola");
        s.update(vuelos);
        s.save(vuelos);
        transaction.commit();
        s.close();
        session.close();
    }
    
    public static void consulta(){
        SessionFactory session = SessionFactoryUtil.getSessionFactory();
        Session s = session.openSession();
        Transaction transaction = s.beginTransaction();
        System.out.println("Modificando");
        Vuelos vuelo = new Vuelos();
        Query q= s.createQuery("from Vuelos where Destino='BARCELONA' or Destino='MADRID'");
        List <Vuelos> listaVuelo=q.list();
        Iterator <Vuelos> iter =listaVuelo.iterator();
        while(iter.hasNext()){
            vuelo=(Vuelos) iter.next();
            System.out.println("PROCEDENCIA: "+vuelo.getProcedencia()+"\t");
            System.out.println("DESTINO: "+vuelo.getDestino()+"\t");
            System.out.println("HORA DE SALIDA: "+vuelo.getHoraSalida());
        }
        s.close();
        session.close();
    }
    
    public static void main(String[] args) {
        //insertar();
        //modificar();
        //eliminar();
        consulta();
    }
}
