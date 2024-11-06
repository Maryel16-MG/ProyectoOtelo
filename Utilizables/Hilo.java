/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilizables;

import static Utilizables.Colores.Negro;
import static Utilizables.Colores.Rojo;
import javax.swing.JButton;

/**
 *
 * @author user
 */
public class Hilo {
       private JButton btn;
    private Colores color;

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }

    public JButton getBtn() {
        return btn;
    }

    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    public void animationBox() {
        for (int i = 0; i < 4; i++) {
            if (this.getColor() == Rojo) {
                btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negro" + i + ".png")));
            }
            if (this.getColor() == Negro) {
                btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojo" + i + ".png")));
            }
            btn.setContentAreaFilled(false);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }
        }

        if (this.getColor() != Rojo) {
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negro0" + ".png")));

        }
        if (this.getColor() != Negro) {
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojo0" + ".png")));
        }
    }

    public void run() {
        animationBox();
    }

    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
