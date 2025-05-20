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
    
    boolean Generacion_Minas;
    int NumCasillasAbiertas;
    
    private Consumer<List<Casilla>> PartidaPerdida;
    private Consumer<List<Casilla>> PartidaWin;
    private Consumer<Casilla> Casilla_Abierta;
    
    public TableroBuscaminas(int NumFila, int NumColumna, int NumMinas) 
    {
        this.NumFila = NumFila;
        this.NumColumna = NumColumna;
        this.NumMinas = NumMinas;
        this.IniciarCasillas();
        this.Generacion_Minas = false;        
    }
    
    private void IniciarCasillas()
    {
        casilla = new Casilla[this.NumFila][this.NumColumna];
        for(int i = 0; i < casilla.length; i++)
        {
            for( int j = 0; j < casilla[i].length; j++)
            {
                casilla[i][j] = new Casilla(i, j);
            }
        }
    }
    
    private void GenerarMinas(int PosFilaIgnorar, int PosColumnaIgnorar)
    {
        int MinasGeneradas = 0;
        while(MinasGeneradas != NumMinas)
        {
            int PosTmpFila;
            int PosTmpColumna;
            do{
                PosTmpFila = (int)(Math.random()* casilla.length);
                PosTmpColumna = (int)(Math.random()* casilla[0].length);
            }while((PosTmpFila == PosFilaIgnorar && PosTmpColumna == PosColumnaIgnorar) || casilla[PosTmpFila][PosTmpColumna].isMina());
            casilla[PosTmpFila][PosTmpColumna].setMina(true);
            MinasGeneradas++;
        }
        Actualizar_Num_Minas_Alrededor();
        this.Generacion_Minas = true;
        this.ImprimirTablero();
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
                    List<Casilla> kasillasAlrededor = Obtener_CasillasAlrededor( i, j);
                    kasillasAlrededor.forEach((c) -> c.IncrementarNumeroMinasCrecanas());
                }
            }
     
        }  
    }
    
    private List<Casilla> Obtener_CasillasAlrededor(int PosFila, int PosColumna)
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
    
    List<Casilla> ObtenerCasillasConMinas()
    {
        List<Casilla> Casillas_Mina = new LinkedList<>();
            for (int i = 0; i < casilla.length; i++) 
            {
                for (int j = 0; j < casilla[i].length; j++) 
                {
                    if (casilla[i][j].isMina()) {
                        Casillas_Mina.add(casilla[i][j]);
                    }
                }
            }
        return Casillas_Mina;
    }
    
    public void Seleccionar_Casilla(int PosFila, int PosColumna) 
    {
        if(!this.Generacion_Minas)
        {
            this.GenerarMinas(PosFila, PosColumna);
        }
        Casilla_Abierta.accept(this.casilla[PosFila][PosColumna]);
        if (this.casilla[PosFila][PosColumna].isMina()) 
        {
            PartidaPerdida.accept(ObtenerCasillasConMinas());
        } else if (this.casilla[PosFila][PosColumna].getNum_Mina_Alrededor() == 0) 
            {
                Marcar_Casilla_Abierta(PosFila, PosColumna);
                List<Casilla> Casillas_Alrededor = Obtener_CasillasAlrededor(PosFila, PosColumna);
                for (Casilla c : Casillas_Alrededor) 
                {
                    if (!c.isAbierta()) 
                    {
                        Seleccionar_Casilla(c.getFila(), c.getColumna());
                    }
                }
            } 
            else 
            {
                Marcar_Casilla_Abierta(PosFila, PosColumna);
            }
            if (PartidaGanada()) 
            {
                PartidaWin.accept(ObtenerCasillasConMinas());
            }
    }
    
    void Marcar_Casilla_Abierta(int PosFila, int PosColumna)
    {
        if( !this.casilla[PosFila][PosColumna].isAbierta())
        {
             NumCasillasAbiertas++;
             this.casilla[PosFila][PosColumna].setAbierta(true);
        }
        
    }

    boolean PartidaGanada()
    {
        return NumCasillasAbiertas >= (NumFila * NumColumna) - NumMinas;
    }
    
    
    public static void main(String[] args) 
    {
        

    }

    public void setPartidaPerdida(Consumer<List<Casilla>> PartidaPerdida) 
    {
        this.PartidaPerdida = PartidaPerdida;
    }

    public void setCasilla_Abierta(Consumer<Casilla> Casilla_Abierta) 
    {
        this.Casilla_Abierta = Casilla_Abierta;
    }

    public void setPartidaWin(Consumer<List<Casilla>> PartidaWin) 
    {
        this.PartidaWin = PartidaWin;
    }
    
}
