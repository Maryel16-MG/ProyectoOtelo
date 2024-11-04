/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import static Utilizables.Colores.Negro;
import static Utilizables.Colores.Rojo;
import javax.swing.JButton;

/**
 *
 * @author thyfa
 */
public class Juego {
    
    private JButton[][] ListaBotones = new JButton[12][12];
    private Tablero Tablero;
    private Jugador Jugador1;
    private Jugador Jugador2;
    private boolean TurnoActual; //False es turno del jugador1 y True es para el jugador2

    public Juego(Tablero Tablero, Jugador Jugador1, Jugador Jugador2) {
        this.Tablero = Tablero;
        this.Jugador1 = Jugador1;
        this.Jugador2 = Jugador2;
        this.TurnoActual = false;
        ColocarFichasIniciales();
    }

    public Juego(String Jugador1, String Jugador2) {
        this.Tablero = new Tablero();
        this.Jugador1 = new Jugador(Jugador1, Negro);
        this.Jugador2 = new Jugador(Jugador2, Rojo);
        this.TurnoActual = false;
        ColocarFichasIniciales();
    }

    public void ColocarFichasIniciales() {
        this.Tablero.ObtenerCasiila(5, 5).setEstado(true);
        this.Tablero.ObtenerCasiila(5, 6).setEstado(true);
        this.Tablero.ObtenerCasiila(6, 6).setEstado(true);
        this.Tablero.ObtenerCasiila(6, 5).setEstado(true);

        this.Tablero.ObtenerCasiila(5, 5).getFicha().setColor(Negro);
        this.Tablero.ObtenerCasiila(5, 6).getFicha().setColor(Rojo);
        this.Tablero.ObtenerCasiila(6, 6).getFicha().setColor(Negro);
        this.Tablero.ObtenerCasiila(6, 5).getFicha().setColor(Rojo);
    }

    public Tablero getTablero() {
        return Tablero;
    }

    public void setTablero(Tablero Tablero) {
        this.Tablero = Tablero;
    }

    public Jugador getJugador1() {
        return Jugador1;
    }

    public void setJugador1(Jugador Jugador1) {
        this.Jugador1 = Jugador1;
    }

    public Jugador getJugador2() {
        return Jugador2;
    }

    public void setJugador2(Jugador Jugador2) {
        this.Jugador2 = Jugador2;
    }

    public boolean isTurnoActual() {
        return TurnoActual;
    }

    public void setTurnoActual(boolean TurnoActual) {
        this.TurnoActual = TurnoActual;
    }

    public void CambiarTurno() {

        if (TurnoActual) {
            TurnoActual = false;
        } else {
            TurnoActual = true;
        }
        RetirarBlancosPorCambioDeTurno();
    }

}
