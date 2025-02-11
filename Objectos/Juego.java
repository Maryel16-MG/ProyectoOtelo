/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objectos;

import Objectos.Jugador;
import Objectos.Tablero;
import Utilizables.Colores;
import static Utilizables.Colores.Blanco;
import static Utilizables.Colores.Negro;
import static Utilizables.Colores.Rojo;
import static Utilizables.Colores.Rosa;
import Utilizables.Hilo;
import javax.swing.JButton;



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

    public void ColocarFicha(int fila, int columna, Colores color) {
        if (this.ObtenerJugadorActual().getColorDeFicha() == Rojo) {
            ListaBotones[fila][columna].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojo0" + ".png")));

        } else if (this.ObtenerJugadorActual().getColorDeFicha() == Negro) {
            ListaBotones[fila][columna].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negro0" + ".png")));

        }

        this.Tablero.ObtenerCasiila(fila, columna).setEstado(true);
        this.Tablero.ObtenerCasiila(fila, columna).getFicha().setColor(color);

        ValidarCasilla(fila, columna, 1);

    }

    public Jugador ObtenerJugadorActual() {
        if (TurnoActual) {
            return this.Jugador2;
        } else {
            return this.Jugador1;
        }
    }

    public void AgregarListaBotones(JButton[][] ListaBotones) {
        this.ListaBotones = ListaBotones;
    }

    public boolean ValidarCasilla(int FilaOriginal, int ColumnaOriginal, int Enviado) {
        if (this.Tablero.ObtenerCasiila(FilaOriginal, ColumnaOriginal).getFicha().getColor()
                == Rosa
                || this.Tablero.ObtenerCasiila(FilaOriginal, ColumnaOriginal).getFicha().getColor()
                == Blanco) {
            return true;
        }

        if (Enviado == 0) {
            if (this.Tablero.ObtenerCasiila(FilaOriginal, ColumnaOriginal).getFicha().getColor()
                    == ObtenerJugadorActual().getColorDeFicha()) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (ValidarPosicionCasilla(FilaOriginal, i)) {
                continue;
            }
            for (int j = 0; j < 3; j++) {
                FilaOriginal = FilaOriginal;
                if (ValidarPosicionCasilla(ColumnaOriginal, j)) {
                    continue;
                }
                if (i == 1 && j == 1) {
                    continue;
                }

                if (Enviado == 0
                        && this.Tablero.ObtenerCasiila(DevolverPosicion(i, FilaOriginal), DevolverPosicion(j, ColumnaOriginal)).isEstado()
                        && this.Tablero.ObtenerCasiila(DevolverPosicion(i, FilaOriginal), DevolverPosicion(j, ColumnaOriginal)).getFicha().getColor()
                        == ObtenerJugadorActual().getColorDeFicha()) {
                    this.ComenzarReconocimiento(Enviado, ((3 - 1) - i), ((3 - 1) - j), FilaOriginal, ColumnaOriginal);
                }

                if (Enviado == 1
                        && this.Tablero.ObtenerCasiila(DevolverPosicion(i, FilaOriginal), DevolverPosicion(j, ColumnaOriginal)).isEstado()
                        && this.Tablero.ObtenerCasiila(DevolverPosicion(i, FilaOriginal), DevolverPosicion(j, ColumnaOriginal)).getFicha().getColor()
                        != ObtenerJugadorActual().getColorDeFicha()) {
                    this.ComenzarReconocimiento(Enviado, ((i - 1) + 1), ((j - 1) + 1), FilaOriginal, ColumnaOriginal);
                }
            }
        }
        return true;
    }

    public boolean ValidarLimites(int NuevaPosicionFila, int NuevaPosicionColumna) {
        if (NuevaPosicionFila < 0 || NuevaPosicionFila > 11) {
            return true;
        }
        if (NuevaPosicionColumna < 0 || NuevaPosicionColumna > 11) {
            return true;
        }

        return false;
    }

    public boolean ComenzarReconocimiento(int Enviado, int IndicadorFila, int IndicadorColumna, int Fila, int Columna) {

        int NuevaPosicionFila = DevolverPosicion(IndicadorFila, Fila);
        int NuevaPosicionColumna = DevolverPosicion(IndicadorColumna, Columna);


        if (ValidarLimites(NuevaPosicionFila, NuevaPosicionColumna)) {
            return false;
        }

        if (Enviado == 0 
                && this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).getFicha().getColor() == ObtenerJugadorActual().getColorDeFicha()) {
            return true;
        }

        if (Enviado == 0 && this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).isEstado()) {
            ComenzarReconocimiento(Enviado, IndicadorFila, IndicadorColumna, NuevaPosicionFila, NuevaPosicionColumna);
            return true;
        }

        if (Enviado == 0 && !this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).isEstado()) {
            this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).getFicha().setColor(Blanco);
            return false;
        }

        if (Enviado == 1
                && !this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).isEstado()) {
            return false;
        }

        if (Enviado == 1
                && this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).isEstado()
                && this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).getFicha().getColor() == ObtenerJugadorActual().getColorDeFicha()) {
            return true;
        }

        if (Enviado == 1
                && this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).isEstado()) {
            if (ComenzarReconocimiento(Enviado, IndicadorFila, IndicadorColumna, NuevaPosicionFila, NuevaPosicionColumna)) {
                Hilo Hilo = new Hilo();

                this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).getFicha().setColor(ObtenerJugadorActual().getColorDeFicha());
                Hilo.setBtn(this.ListaBotones[NuevaPosicionFila][NuevaPosicionColumna]);
                Hilo.setColor(ObtenerJugadorActual().getColorDeFicha());
                Hilo.startAnimation();
                return true;
            } else {
                this.Tablero.ObtenerCasiila(NuevaPosicionFila, NuevaPosicionColumna).getFicha().setColor(this.InvertirColores(ObtenerJugadorActual().getColorDeFicha()));
                return false;
            }

        }
        return true;
    }

    public Colores InvertirColores(Colores color) {
        if (color == Negro) {
            return Rojo;
        } else if (color == Rojo) {
            return Negro;
        } else {
            return Rosa;
        }
    }

    public int DevolverPosicion(int Indicador, int PosicionOriginal) {
        int x = ((PosicionOriginal - 1) + Indicador);
        return x;

    }

    public boolean ValidarPosicionCasilla(int Posicion, int Indicador) {
        if (Indicador == 1) {
            return false;
        }

        if (Indicador == 0 && (Posicion - 1) + Indicador < 0) {
            return true;
        }

        if (Indicador == 2 && (Posicion + 1) + Indicador > 11) {
            return true;
        }

        return false;
    }

    public void ConteoDeFichasJugadores() {
        int Negras = 0;
        int Rojas = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Negro) {
                    Negras++;
                } else if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Rojo) {
                    Rojas++;
                }
            }
        }
        this.Jugador1.setFichasObtenidas(Negras);
        this.Jugador2.setFichasObtenidas(Rojas);
    }

    public void Mostrar() {
        System.out.println("[ Z][ 0][ 1][ 2][ 3][ 4][ 5][ 6][ 7][ 8][ 9][10][11]");
        for (int i = 0; i < 12; i++) {
            if (i < 10) {
                System.out.print("[ " + i + "]");

            } else {
                System.out.print("[" + i + "]");

            }
            for (int j = 0; j < 12; j++) {
                if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Rosa) {
                    System.out.print("[ X]");
                } else if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Negro) {
                    System.out.print("[ N]");
                } else if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Blanco) {
                    System.out.print("[ B]");
                } else {
                    System.out.print("[ R]");
                }
            }
            System.out.println("");
        }

    }

    public void MostrarPosiblesJugadas() {
        RetirarBlancosPorCambioDeTurno();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                this.ValidarCasilla(i, j, 0);
            }
        }
    }

    public void RetirarBlancosPorCambioDeTurno() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Rosa) {
                } else if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Negro) {
                } else if (this.Tablero.ObtenerCasiila(i, j).getFicha().getColor() == Blanco) {
                    this.Tablero.ObtenerCasiila(i, j).getFicha().setColor(Rosa);
                } else {
                }
            }
        }
    }

    public void AgregarTextButton(int Fila, int Columna, JButton btn) {
        btn.setSelected(false);
        if (this.Tablero.ObtenerCasiila(Fila, Columna).getFicha().getColor() == Rosa) {
            btn.setIcon(null);
        } else if (this.Tablero.ObtenerCasiila(Fila, Columna).getFicha().getColor() == Negro) {
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negro0" + ".png")));
        } else if (this.Tablero.ObtenerCasiila(Fila, Columna).getFicha().getColor() == Blanco) {
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Libre" + ".png")));
            btn.setSelected(true);
        } else {
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojo0" + ".png")));
        }
    }

}
