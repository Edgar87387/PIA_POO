/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.buscaminaspoo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
//import javax.swing.ImageIcon;

/**
 *
 * @author edgar
 */
public class Juego extends javax.swing.JFrame 
{
    //Atributos principales de la clase
    int Num_Filas = 10; //Se especifica un nuero de filas y columnas por defecto
    int Num_Columnas = 10;
    int Num_Minas = 10;//Se da por defecto 10 minas 
    
    JButton [][] BotonesTablero;
    TableroBuscaminas tablerobuscaminas;

    /**
     * Constructor del juego donde se inicia el juego y se configuran los menus
     */
    public Juego() 
    {
        initComponents();
        JuegoNuevo();
    }
    /**
     * este metodo se encarga de crear botones nuevos sin embargo antes de esto si existen ya 
     * se eliminaran los botones existentes
     */
    void Descargar_Controles()
    {
        if( BotonesTablero != null )
        {
            for(int i = 0; i < BotonesTablero.length; i++)
            {
                for(int j = 0; j < BotonesTablero[i].length; j++)
                {
                    if(BotonesTablero[i][j] != null)
                    {
                        getContentPane().remove(BotonesTablero[i][j]);
                    }
                }
            }
        }
    }
    /**
     * Este metodo se encargara de reiniciar el juego volviendo descargar los
     * controles, cargando los controles, creando el tablero y evitamos que se repitan los componentes
     */
    private void JuegoNuevo()
    {
        Descargar_Controles();
        CargarControles();
        CrearTablero();
        repaint();
    }
    
    /**
     * Este metodo crea el tablero y define las acciones que ocurriran
     * si pierdes, aganas o solamente abres la casilla
     */
    private void CrearTablero() 
    {
        tablerobuscaminas = new TableroBuscaminas(Num_Filas, Num_Columnas, Num_Minas);
        tablerobuscaminas.setPartidaPerdida(new Consumer<List<Casilla>>() 
        {
            @Override
            public void accept(List<Casilla> t) 
            {
                for (Casilla Casilla_Mina : t) 
                {
                     BotonesTablero[Casilla_Mina.getFila()][Casilla_Mina.getColumna()].setText("*");
                     //Llevo ya mucho tiempo aqui no voy a poner iamgenes de momento :(
                    //ImageIcon iconoMina = new ImageIcon("Source Packages/imagenes/bomba.png");
                    //BotonesTablero[Casilla_Mina.getFila()][Casilla_Mina.getColumna()].setIcon(iconoMina);
                }

                int respuesta = JOptionPane.showConfirmDialog(
                    null, 
                    "!!!You Lose!!!\n¿Quieres jugar de nuevo?", 
                    "Game Over", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.OK_OPTION) 
                {
                    JuegoNuevo(); 
                } else 
                {
                    System.exit(0); 
                }
            }
        });
        
        tablerobuscaminas.setPartidaWin(new Consumer<List<Casilla>>() 
        {
            @Override
            public void accept(List<Casilla> t) 
            {
                for (Casilla Casilla_Mina : t) 
                {
                     BotonesTablero[Casilla_Mina.getFila()][Casilla_Mina.getColumna()].setText("UwU");
                }

                int respuesta = JOptionPane.showConfirmDialog(
                    null, 
                    "!!!You Win!!!\n¿Quieres jugar de nuevo?", 
                    "Felicidades", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.OK_OPTION) 
                {
                    JuegoNuevo(); 
                } else 
                {
                    System.exit(0); 
                }
            }
        });

        tablerobuscaminas.setCasilla_Abierta(new Consumer<Casilla>()
        {
            @Override
            public void accept(Casilla t)
            {
                 BotonesTablero[t.getFila()][ t.getColumna()].setEnabled(false);
                 BotonesTablero[t.getFila()][t.getColumna()].setText(t.getNum_Mina_Alrededor() == 0?"": t.getNum_Mina_Alrededor()+"");

            }
        });
    }

    /**
     * Aqui se creara la interfaz grafica conlas dimenciones especificadas
     */
    private void CargarControles()
    {
        int PosXreferencia = 25;
        int PosYreferencia = 25;
        int Ancho = 30;
        int Alto = 30;

        BotonesTablero = new JButton[Num_Filas][Num_Columnas];
        for (int i = 0; i < BotonesTablero.length; i++) 
        {
            for (int j = 0; j < BotonesTablero[i].length; j++) {
                BotonesTablero[i][j] = new JButton();
                BotonesTablero[i][j].setName(i+","+j);
                BotonesTablero[i][j].setBorder(null);
                if(i ==0 && j == 0)
                {
                    BotonesTablero[i][j].setBounds(PosXreferencia, PosYreferencia, Ancho, Alto);
                } 
                else if( i == 0 && j != 0)
                {
                    BotonesTablero[i][j].setBounds(BotonesTablero[i][j-1].getX()+BotonesTablero[i][j-1].getWidth(), PosYreferencia, Ancho, Alto);
                }
                else
                {
                    BotonesTablero[i][j].setBounds(BotonesTablero[i-1][j].getX(), BotonesTablero[i-1][j].getY()+BotonesTablero[i-1][j].getHeight(),Ancho, Alto);
                }
                //Evento para cuando se de click en una casilla
                BotonesTablero[i][j].addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        btnClick(e);
                    }
                });
                getContentPane().add(BotonesTablero[i][j]);
            }
        }
        //Ajusta el tamaño de la ventana al tablero
        this.setSize(BotonesTablero[Num_Filas-1][Num_Columnas-1].getX()+ BotonesTablero[Num_Filas-1][Num_Columnas-1].getWidth()+30, BotonesTablero[Num_Filas-1][Num_Columnas-1].getY() + BotonesTablero[Num_Filas-1][Num_Columnas-1].getHeight()+70);

    }

    /**
     * Si se le da click a una casilla
     * @param e Evento de tipo ActionEvent
     */
    private void btnClick(ActionEvent e) 
    {
        JButton btn = (JButton)e.getSource();
        String[] coordenada = btn.getName().split(",");
        int PosFila = Integer.parseInt(coordenada[0]);
        int PosColumna = Integer.parseInt(coordenada[1]);
        /**JOptionPane.showMessageDialog(rootPane,PosFila + "," + PosColumna);**/
        tablerobuscaminas.Seleccionar_Casilla(PosFila, PosColumna);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Menu UwU");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Reset");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Tamaño");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Numero de minas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try 
        {
            int num = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamanio de la matriz: "));
            if(num < 2 || num > 30) {
                JOptionPane.showMessageDialog(this, "Tamanio invalido. Debe estar entre 2 y 30.");
                return;
            }
            this.Num_Filas = num;
            this.Num_Columnas = num;
            if(Num_Minas >= Num_Filas * Num_Columnas) {
                JOptionPane.showMessageDialog(this, "!!!!!!!!!!!!!Reduzca el numero de minas!!!!!!!!!!!!!!!");
                return;
            }
            JuegoNuevo();
        } catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Entrada invalida. Solo se permiten numeros enteros.");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try 
        {
            int num = Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de minas: "));
            if(num < 1 || num >= Num_Filas * Num_Columnas) {
                JOptionPane.showMessageDialog(this, "Numero de minas invalido. Debe ser mayor que 0 y menor que el total de casillas (" + (Num_Filas * Num_Columnas) + ").");
                return;
            }
            this.Num_Minas = num;
            JuegoNuevo();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Solo se permiten numeros enteros.");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JuegoNuevo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
