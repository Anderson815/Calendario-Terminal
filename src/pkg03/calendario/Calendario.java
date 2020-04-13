/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03.calendario;

/**
 *
 * @author ander
 */
public class Calendario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("CALENDÁRIO\n\n");
        Cal calendario = new Cal();
        calendario.estado();
        calendario.interfaceGrafica();
    }
    
}
