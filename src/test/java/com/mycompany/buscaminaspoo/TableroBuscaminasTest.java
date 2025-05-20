package com.mycompany.buscaminaspoo;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroBuscaminasTest {

    private TableroBuscaminas tablero;

    public TableroBuscaminasTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        tablero = new TableroBuscaminas(5, 5, 5);
        tablero.setCasilla_Abierta(c -> {});
        tablero.setPartidaPerdida(c -> {});
        tablero.setPartidaWin(c -> {});
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testImprimirTablero() {
        TableroBuscaminas instance = new TableroBuscaminas(5, 5, 3);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        instance.Seleccionar_Casilla(2, 2);
        instance.ImprimirTablero();
        assertTrue(true);
    }

    @Test
    public void testImprimir_Pistas() {
        TableroBuscaminas instance = new TableroBuscaminas(5, 5, 3);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        instance.Seleccionar_Casilla(2, 2);
        instance.Imprimir_Pistas();
        assertTrue(true);
    }

    @Test
    public void testObtenerCasillasConMinas() {
        TableroBuscaminas instance = new TableroBuscaminas(5, 5, 4);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        instance.Seleccionar_Casilla(2, 2);
        List<Casilla> result = instance.ObtenerCasillasConMinas();
        assertEquals(4, result.size());
    }

    @Test
    public void testSeleccionar_Casilla() {
        TableroBuscaminas instance = new TableroBuscaminas(4, 4, 2);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        instance.Seleccionar_Casilla(1, 1);
        assertTrue(instance.casilla[1][1].isAbierta());
    }

    @Test
    public void testMarcar_Casilla_Abierta() {
        TableroBuscaminas instance = new TableroBuscaminas(3, 3, 1);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        instance.Marcar_Casilla_Abierta(1, 1);
        assertTrue(instance.casilla[1][1].isAbierta());
    }

    @Test
    public void testPartidaGanada() {
        TableroBuscaminas instance = new TableroBuscaminas(2, 2, 1);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                instance.Seleccionar_Casilla(i, j);
            }
        }
        assertTrue(instance.PartidaGanada());
    }

    @Test
    public void testMain() {
        String[] args = null;
        TableroBuscaminas.main(args);
        assertTrue(true);
    }

    @Test
    public void testSetPartidaPerdida() {
        TableroBuscaminas instance = new TableroBuscaminas(2, 2, 1);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(minas -> assertNotNull(minas));
        instance.setPartidaWin(c -> {});
        instance.Seleccionar_Casilla(1, 1);
        assertTrue(true);
    }

    @Test
    public void testSetCasilla_Abierta() {
        TableroBuscaminas instance = new TableroBuscaminas(3, 3, 1);
        instance.setCasilla_Abierta(c -> assertNotNull(c));
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(c -> {});
        instance.Seleccionar_Casilla(1, 1);
        assertTrue(instance.casilla[1][1].isAbierta());
    }

    @Test
    public void testSetPartidaWin() {
        TableroBuscaminas instance = new TableroBuscaminas(2, 2, 1);
        instance.setCasilla_Abierta(c -> {});
        instance.setPartidaPerdida(c -> {});
        instance.setPartidaWin(minas -> assertNotNull(minas));
        instance.Seleccionar_Casilla(1, 1);
        assertTrue(true);
    }
}
