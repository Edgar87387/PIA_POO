/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminaspoo;

/**
 *
 * @author edgar
 * Declaracion de la clase casilla esta clase sera la que se utilizara para todo el codigo debido a que las casillas cuentan 
 * con varios atributos como se vera acontinuacion
 */
public class Casilla 
{
    //Posicion de la casilla en el arreglo
    private int Fila;
    private int Columna;
    private boolean Mina;//Si la casilla contiene una mina o no
    private int Num_Mina_Alrededor; //Numero de minas alrededor de las casillas
    private boolean Abierta;//Si la casilla fue abierta o no

    /**
     * Contructor del la posicion de la casilla
     * @param Fila posicion de la fila
     * @param Columna posicion de la columna
     */
    public Casilla(int Fila, int Columna)
    {
        this.Fila = Fila;
        this.Columna = Columna;
    }

    //Metodos getter y setter
    public int getFila() 
    {
        return Fila;
    }

    public void setFila(int Fila) 
    {
        this.Fila = Fila;
    }

    public int getColumna() 
    {
        return Columna;
    }

    public void setColumna(int Columna) 
    {
        this.Columna = Columna;
    }

    public boolean isMina() 
    {
        return Mina;
    }

    public void setMina(boolean Mina) 
    {
        this.Mina = Mina;
    }

    public int getNum_Mina_Alrededor() 
    {
        return Num_Mina_Alrededor;
    }
    
    public void setNum_Mina_Alrededor(int Num_Mina_Alrededor)
    {
        this.Num_Mina_Alrededor = Num_Mina_Alrededor;
    }
    
    /**
     * Metodo para incrementar el contador de minas alrededor
     */
    public void IncrementarNumeroMinasCrecanas()
    {
        this.Num_Mina_Alrededor++;
    }
    
    public boolean isAbierta()
    {
        return Abierta;
    }
    
    public void setAbierta(boolean Abierta)
    {
        this.Abierta = Abierta;
    }

    
}
