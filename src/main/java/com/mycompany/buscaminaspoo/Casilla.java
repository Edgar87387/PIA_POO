/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminaspoo;

/**
 *
 * @author edgar
 */
public class Casilla 
{
    private int Fila;
    private int Columna;
    private boolean Mina;
    private int Num_Mina_Alrededor;
    private boolean Abierta;

    public Casilla(int Columna, int Fila) 
    {
        this.Columna = Columna;
        this.Fila = Fila;
    }

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

    public int getNum_Mina_Alrededor() {
        return Num_Mina_Alrededor;
    }
    
    public void setNum_Mina_Alrededor(int Num_Mina_Alrededor){
        this.Num_Mina_Alrededor = Num_Mina_Alrededor;
    }
    
    public void IncrementarNumeroMinasCrecanas()
    {
        this.Num_Mina_Alrededor++;
    }
    
    public boolean isAbierta(){
        return Abierta;
    }
    
    public void setAbierta(boolean Abierta)
    {
        this.Abierta = Abierta;
    }

    
}
