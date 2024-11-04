/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author thyfa
 */
public class Casilla {
    private boolean Estado; //Todas las casillas inicias desocupadas
    private Ficha ficha;

    public Casilla(boolean Estado, Ficha ficha) {
        this.Estado = Estado;
        this.ficha = ficha;
    }

    public Casilla() {
        this.Estado = false;
        this.ficha = new Ficha(Rosa);
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    
    
}
