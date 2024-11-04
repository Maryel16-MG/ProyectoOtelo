/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package othello;

import Objetos.Juego;
import Utilizables.Colores;

/**
 *
 * @author 9575
 */
public class Othello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Juego juego = new Juego("Emmanuel", "Miguel");

        System.out.println("El jugador actual es " + juego.ObtenerJugadorActual().getNombre());;
        System.out.println("El color del jugador es " + juego.ObtenerJugadorActual().getColorDeFicha());;

        juego.MostrarPosiblesJugadas();

        juego.Mostrar();

//        juego.ColocarFicha(9, 9, Colores.Negro);
//
//        juego.MostrarPosiblesJugadas();
//
//        juego.Mostrar();
//
//        juego.CambiarTurno();
//
//        juego.MostrarPosiblesJugadas();
//
//        juego.Mostrar();
//
//        juego.ColocarFicha(5, 4, Colores.Rojo);
//
//        juego.MostrarPosiblesJugadas();
//
//        juego.Mostrar();

    }
}
