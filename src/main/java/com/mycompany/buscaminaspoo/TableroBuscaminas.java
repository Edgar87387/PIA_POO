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
    /**
     * Constructor del tablero
     * @param NumFila Este sera el numero de filas que tendra el tablero
     * @param NumColumna Este sera el numero de columnas que tendra el tablero
     * @param NumMinas Y final mete el numero de minas que tendra nuestro tablero
     */
    
    public TableroBuscaminas(int NumFila, int NumColumna, int NumMinas) 
    {
        this.NumFila = NumFila;
        this.NumColumna = NumColumna;
        this.NumMinas = NumMinas;
        this.IniciarCasillas();
        this.Generacion_Minas = false;        
    }
    
    /**
     * Este metodo sera el encargado de cerar las casillas esto dependera de 
     * las casillas que elija el ususrio para jugar una vez el usuario ingrese
     * el numero de casillas y columnas se recorrera el arrego agregando cada casilla 
     * al arreglo 
     */
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
    /**
     * En este metodo se van a generar las minas en el tablero de una forma aleatoria
     * se tomara en cuenta que la primera fila elegidapor el jugador no contenga una mina
     * @param PosFilaIgnorar  En esta fila se ignorara y no se colocara una mina
     * @param PosColumnaIgnorar  Al igual que la fila la columna se ignorara
     */
    private void GenerarMinas(int PosFilaIgnorar, int PosColumnaIgnorar)
    {
        int MinasGeneradas = 0;
        while(MinasGeneradas != NumMinas)// Se generararn las minas hasta que se cumpla con el total de minas solicitadas
        { 
            int PosTmpFila;
            int PosTmpColumna;
            do
            {
                PosTmpFila = (int)(Math.random()* casilla.length);
                PosTmpColumna = (int)(Math.random()* casilla[0].length);
            }while((PosTmpFila == PosFilaIgnorar && PosTmpColumna == PosColumnaIgnorar) || casilla[PosTmpFila][PosTmpColumna].isMina());
            casilla[PosTmpFila][PosTmpColumna].setMina(true);//Se marca la casilla seleccionada al azar como mina 
            MinasGeneradas++;//suma en uno e;l numero de minas generadas
        }
        Actualizar_Num_Minas_Alrededor();
        this.Generacion_Minas = true;
        this.ImprimirTablero();
    }
    /**
     * Este metdod fue echo para imprimir el tablero en consola y poder ver las mias 
     * que estan en el mismo 
     */
   public void ImprimirTablero()
    {
        for(int i = 0; i < casilla.length; i++)
        {
            for( int j = 0; j < casilla[i].length; j++)
            {
                System.out.print(casilla[i][j].isMina()?"*":"0");//Si la casilla tiene una mina imprime * sino un 0
            }
            System.out.println("");
        }    
    }
    /**
     * Aqui se imprimiran las pistas del tablero osea los nueros que muestran que tantas minas
     * estan alrededor de la casilla
     */
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
    /**
    * Metodo que obtiene una lista de casillas vecinas alrededor de una casilla dada por su fila y columna
    * @param PosFila Fila de la casilla
    * @param PosColumna Columna de la columna
    * @return Lista de casillas vecinas en esta lista estaran todas las casillas de alrededor
    */
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
                    TmPosFila--;//arriba
                    break;
                case 1:TmPosFila--;//arriba en la derecha
                       TmPosColumna++;
                       break;
                case 2:
                    TmPosColumna++;//derecha
                    break;
                case 3:
                    TmPosColumna++;//abajo en la derecha
                    TmPosFila++;
                    break;
                case 4:
                    TmPosFila++;//abajo
                    break;
                case 5:
                    TmPosFila++;//abajo en la izquierda
                    TmPosColumna--;
                    break;
                case 6:
                    TmPosColumna--;//izquierda
                    break;
                case 7:
                    TmPosFila--;//arriba en la izquierda
                    TmPosColumna--;
                    break;
            }
            //Se verifica que la casilla no salga de los limites del tablero 
            if(TmPosFila >= 0 && TmPosFila < this.casilla.length && TmPosColumna >= 0 && TmPosColumna < this.casilla[0].length)
            {
                ListaCasillas.add(this.casilla[TmPosFila][TmPosColumna]);
            }
        }
        return ListaCasillas;
    }
    /**
     * En este metodo se optendran las casilllas con mina 
     * @return regresa la lista con todas las casillas que tienen mina
     */
    List<Casilla> ObtenerCasillasConMinas()
    {
        List<Casilla> Casillas_Mina = new LinkedList<>();
            for (int i = 0; i < casilla.length; i++) 
            {
                for (int j = 0; j < casilla[i].length; j++) 
                {
                    if (casilla[i][j].isMina()) 
                    {
                        Casillas_Mina.add(casilla[i][j]);
                    }
                }
            }
        return Casillas_Mina;
    }
    
    /**
    * Metodo principal que se ejecuta cuando el jugador selecciona una casilla
    * si la casilla seleccionada es una mina se accioan el evento PrtidaPertida
    * si no tiene minas alrededor, abre a las casillas cercanas que no tienen minas tambien
    * si logras abrir todas las casillas sin mina ya ganaste UwU
    * @param PosFila Fila de la casilla seleccionada
    * @param PosColumna Columna de la casilla seleccionada
    */
    public void Seleccionar_Casilla(int PosFila, int PosColumna) 
    {
        // SI es la primera casilla que abres entoces se genena las minas depues a abrir la primera
        if (!this.Generacion_Minas) 
        {
            this.GenerarMinas(PosFila, PosColumna);
        }
        
        Casilla casillaActual = this.casilla[PosFila][PosColumna];
        //Sila casilla ya fue abierta no se hara nada 
        if (casillaActual.isAbierta()) 
        {
            return;
        }
        //Se marca las casillas cuando son abieratas
        Marcar_Casilla_Abierta(PosFila, PosColumna);
        Casilla_Abierta.accept(casillaActual);
        //Si la casilla abierta es una mina entoces se pierde la partida
        if (casillaActual.isMina()) 
        {
            PartidaPerdida.accept(ObtenerCasillasConMinas());
            return;
        }
        //Si no hay minas alrededor ase abriran las casillas de alrededor que tampoco tengan minaa
        if (casillaActual.getNum_Mina_Alrededor() == 0) 
        {
            List<Casilla> vecinos = Obtener_CasillasAlrededor(PosFila, PosColumna);
            for (Casilla vecino : vecinos) 
            {
                if (!vecino.isAbierta()) 
                {
                    Seleccionar_Casilla(vecino.getFila(), vecino.getColumna());
                }
            }
        }

        if (PartidaGanada()) 
        {
            PartidaWin.accept(ObtenerCasillasConMinas());
        }
    }

    /**
     * El metodo se encarga de marcar las casillas abiertas si es que estas no han sido abiertas
     * @param PosFila Posicion de la fila de la casilla
     * @param PosColumna Posicion de la columna en la casilla
     */
    void Marcar_Casilla_Abierta(int PosFila, int PosColumna)
    {
        if( !this.casilla[PosFila][PosColumna].isAbierta())
        {
             NumCasillasAbiertas++;
             this.casilla[PosFila][PosColumna].setAbierta(true);
        }
        
    }
    /**
     * Confirma si la partida a sido ganada
     * @return true Cuando el numero de casillas abiertas sea mayor o igual
     * al numeor total de casillas menos el numero total de las minas en entablero se gana la partida
     */
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
