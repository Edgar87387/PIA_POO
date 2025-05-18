/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.buscaminaspoo;

/**
 *
 * @author edgar
 */
public class BuscaminasPOO 
{

    public static void main(String[] args) 
    {
        TableroBuscaminas Tablero = new TableroBuscaminas( 5, 5, 5);
        Tablero.ImprimirTablero();
        System.out.println("---");
        Tablero.Imprimir_Pistas(); 
    }
}
