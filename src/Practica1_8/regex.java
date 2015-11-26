/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1_8;

/**
 *
 * @author Sebastian Leonte
 */
public class regex {
    
    public static boolean comprobarNumero(char primer){
        return Character.isDigit(primer);
    }

    public static boolean comprobarMayuscula(String contrasenia){
        return !contrasenia.equals(contrasenia.toLowerCase());
    }

    public static boolean comprobarContrasenia(String contrasenia){
        String regex = "^([A-Za-z0-9_]){8,}$";
        if(!comprobarNumero(contrasenia.charAt(0))){
            if(comprobarMayuscula(contrasenia)){
                return contrasenia.matches(regex); // SE CUMPLE
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        String regex = "^([A-Za-z0-9_]){8,}$";
        String contrasenia = "_a123456";
        if(!comprobarNumero(contrasenia.charAt(0))){
            if(comprobarMayuscula(contrasenia)){
                System.out.println(contrasenia.matches(regex));
            }
        }
    }
}
