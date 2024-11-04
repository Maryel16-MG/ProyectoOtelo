/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author fabia
 */
public class Tablero {
    private Casilla[][] Casillas = new Casilla[12][12];

    public Tablero() {
        IniciarCasillas();
    }
    
    public void IniciarCasillas(){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                this.Casillas[i][j] = new Casilla();
            }
        }
    }

    public Casilla[][] getCasillas() {
        return Casillas;
    }

    public void setCasillas(Casilla[][] Casillas) {
        this.Casillas = Casillas;
    }
    
    public Casilla ObtenerCasiila(int fila, int columna){
        return Casillas[fila][columna];
    }
    
    public void InsertarCasilla(int fila, int columna, Casilla casilla){
        this.Casillas[fila][columna] = casilla;
    }
}
