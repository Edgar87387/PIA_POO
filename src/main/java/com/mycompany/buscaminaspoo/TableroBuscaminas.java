/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminaspoo;

import java.util.*;
import java.util.function.Consumer;

/**
 *
 * @author edgar
 */
public class TableroBuscaminas 
{
    Casilla[][] casilla;
    
    int NumFila;
    int NumColumna;
    int NumMinas;
    private Consumer<List<Casilla>> PartidaPerdida;
    
    public TableroBuscaminas(int NumFila, int NumColumna, int NumMinas) 
    {
        this.NumFila = NumFila;
        this.NumColumna = NumColumna;
        this.NumMinas = NumMinas;
        IniciarCasillas();
        
    }
    
    public void IniciarCasillas()
    {
        casilla = new Casilla[this.NumFila][this.NumColumna];
        for(int i = 0; i < casilla.length; i++)
        {
            for( int j = 0; j < casilla[i].length; j++)
            {
                casilla[i][j] = new Casilla(i, j);
            }
        }
        GenerarMinas();
    }
    
    private void GenerarMinas()
    {
        int MinasGeneradas = 0;
        while(MinasGeneradas != NumMinas)
        {
            int PosTmpFila = (int)(Math.random()* casilla.length);
            int PosTmpColumna = (int)(Math.random()* casilla[0].length);
            if(!casilla[PosTmpFila][PosTmpColumna].isMina()){
                casilla[PosTmpFila][PosTmpColumna].setMina(true);
                MinasGeneradas++;
            }
        }
        Actualizar_Num_Minas_Alrededor();
    }
    
   public void ImprimirTablero()
    {
        for(int i = 0; i < casilla.length; i++)
        {
            for( int j = 0; j < casilla[i].length; j++)
            {
                System.out.print(casilla[i][j].isMina()?"*":"0");
            }
            System.out.println("");
        }    
    }
    
    void Imprimir_Pistas()
    {
        for(int i = 0; i < casilla.length; i++)
        {
            for( int j = 0; j < casilla[i].length; j++)
            {
                System.out.print(casilla[i][j].getNum_Mina_Alrededor());
            }
            System.out.println("");
        }   
    }
    private void Actualizar_Num_Minas_Alrededor()
    {
        for(int i = 0; i < casilla.length; i++)
        {
            for( int j = 0; j < casilla[i].length; j++)
            {
                if(casilla[i][j].isMina())
                {
                    List<Casilla> Num_CasillasAlrededor = CasillasAlrededor( i, j);
                    Num_CasillasAlrededor.forEach((c) -> c.IncrementarNumeroMinasCrecanas());
                }
            }
     
        }  
    }
    
    private List<Casilla> CasillasAlrededor(int PosFila, int PosColumna)
    {
        List<Casilla> ListaCasillas = new LinkedList<>();
        for(int i = 0; i < 8; i++)
        {
            int TmPosFila = PosFila;
            int TmPosColumna = PosColumna;
            switch(i)
            {
                case 0:
                    TmPosFila--;
                    break;
                case 1:TmPosFila--;
                       TmPosColumna++;
                       break;
                case 2:
                    TmPosColumna++;
                    break;
                case 3:
                    TmPosColumna++;
                    TmPosFila++;
                    break;
                case 4:
                    TmPosFila++;
                    break;
                case 5:
                    TmPosFila++;
                    TmPosColumna--;
                    break;
                case 6:
                    TmPosColumna--;
                    break;
                case 7:
                    TmPosFila--;
                    TmPosColumna--;
                    break;
            }
            if(TmPosFila >= 0 && TmPosFila < this.casilla.length && TmPosColumna >= 0 && TmPosColumna < this.casilla[0].length)
            {
                ListaCasillas.add(this.casilla[TmPosFila][TmPosColumna]);
            }
        }
        return ListaCasillas;
    }
    
    public void Seleccionar_Casilla(int PosFila,int PosColumna){
        if (this.casilla[PosFila][PosColumna].isMina()) {
            List<Casilla> Casillas_Mina = new LinkedList<>();
            for (int i = 0; i < casilla.length; i++) {
                for (int j = 0; j < casilla[i].length; j++) {
                    if (casilla[i][j].isMina()) 
                    {
                        Casillas_Mina.add(casilla[i][j]);
                    }
                }

            }
            PartidaPerdida.accept(Casillas_Mina);
        }
    }
    
    
    
    public static void main(String[] args) 
    {
       
    }

    public void setPartidaPerdida(Consumer<List<Casilla>> PartidaPerdida) 
    {
        this.PartidaPerdida = PartidaPerdida;
    }
}
