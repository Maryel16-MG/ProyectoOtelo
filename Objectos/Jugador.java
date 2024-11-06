/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objectos;

import Utilizables.Colores;

/**
 *
 * @author user
 */
public class Jugador {
    private String Nombre;
    private Colores ColorDeFicha;
    private int FichasObtenidas;
    
     public Jugador(String Nombre, Colores ColorDeFicha, int FichasObtenidas) {
        this.Nombre = Nombre;
        this.ColorDeFicha = ColorDeFicha;
        this.FichasObtenidas = FichasObtenidas;
    }

    public Jugador(String Nombre, Colores ColorDeFicha) {
        this.Nombre = Nombre;
        this.ColorDeFicha = ColorDeFicha;
    }
    
    
    public Jugador(String Nombre, int FichasObtenidas) {
        this.Nombre = Nombre;
        this.FichasObtenidas = FichasObtenidas;
    }

    public Jugador(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getFichasObtenidas() {
        return FichasObtenidas;
    }

    public void setFichasObtenidas(int FichasObtenidas) {
        this.FichasObtenidas = FichasObtenidas;
    }

    public Colores getColorDeFicha() {
        return ColorDeFicha;
    }

    public void setColorDeFicha(Colores ColorDeFicha) {
        this.ColorDeFicha = ColorDeFicha;
    }
}
