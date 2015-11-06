/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Pruebas;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 *
 * @author siuxoes
 */
public class Principal {
    
    public void crear(){
        System.out.println("Crear");
    }
    public void cerrar(){
        System.out.println("cerrar");
    }
    public void holo(){
        System.out.println("holoholo");
    }
    
    public static void hhh(){
        System.out.println("hhhh");
    }
    
    public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
        
        Method[] methods = Principal.class.getDeclaredMethods();
        Principal principal = new Principal();
        LinkedList<String> lista = new LinkedList<>();
        for(Method x: methods){
            if(!x.toString().contains("main")){
                String t = x.toString().replace("public void Pruebas.Principal.", "");
                t = t.replace("public static void Pruebas.Principal.", "");
                t = t.replace("()", "");
                lista.add(t);
            }
        }
        for(String x: lista){
            Method gs1Method = Principal.class.getMethod(x, new Class[] {});
            gs1Method.invoke(principal, new Object[] {});
        }
    }
}