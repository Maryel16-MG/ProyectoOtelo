/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrames;


import Objectos.Juego;

import Utilizables.Colores;
import static Utilizables.Colores.Blanco;
import static Utilizables.Colores.Rosa;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class JframeTablero extends javax.swing.JFrame {

    private Juego juego;

    private String Jugador1;
    private String Jugador2;
    private JButton[][] ListaBotones = new JButton[12][12];

    /**
     * Creates new form JframeTablero
     */
    public JframeTablero() {
        initComponents();
    }

    public void IniciarJuego() {
        juego = new Juego(Jugador1, Jugador2);
        System.out.println("COLOR DEL JUGADOR: " + juego.ObtenerJugadorActual().getColorDeFicha());
        AgregarBotonesALista();
        juego.MostrarPosiblesJugadas();
        ActualizarTextosBotones();
        ActivarOpcionesPosible();
        JugadorActual();
        ActualizarInformacion();
        this.ColorJ1.setText(" Color: " + juego.getJugador1().getColorDeFicha());
        this.ColorJ2.setText(" Color: " + juego.getJugador2().getColorDeFicha());
        juego.AgregarListaBotones(ListaBotones);

        ListaBotones[5][5].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negro0" + ".png")));
        ListaBotones[5][6].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojo0" + ".png")));
        ListaBotones[6][6].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negro0" + ".png")));
        ListaBotones[6][5].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojo0" + ".png")));

    }

    public void setJugador1(String Jugador1) {
        this.Jugador1 = Jugador1;
    }

    public void setJugador2(String Jugador2) {
        this.Jugador2 = Jugador2;
    }

    public void ActualizarInformacion() {
        juego.ConteoDeFichasJugadores();
        this.JLabelJugador1.setText(juego.getJugador1().getNombre());
        this.JLabelJugador2.setText(juego.getJugador2().getNombre());
        this.JlabelConteoJ1.setText(String.valueOf(juego.getJugador1().getFichasObtenidas()));
        this.JlabelConteoJ2.setText(String.valueOf(juego.getJugador2().getFichasObtenidas()));
    }

    public void JugadorActual() {
        this.JlabelJugadorActual.setText(this.juego.ObtenerJugadorActual().getNombre());
        this.JLabelColorDelJugadorActual.setText(String.valueOf(this.juego.ObtenerJugadorActual().getColorDeFicha()));
    }

    public void ActualizarTextosBotones() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (juego.getTablero().ObtenerCasiila(i, j).getFicha().getColor() == juego.ObtenerJugadorActual().getColorDeFicha()
                        || juego.getTablero().ObtenerCasiila(i, j).getFicha().getColor() == Blanco
                        || juego.getTablero().ObtenerCasiila(i, j).getFicha().getColor() == Rosa) {
                    juego.AgregarTextButton(i, j, ListaBotones[i][j]);

                }
            }
        }
    }

    public void ActivarOpcionesPosible() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                JButton boton = this.ListaBotones[i][j];
                boton.setFocusable(false);

                if (juego.getTablero().ObtenerCasiila(i, j).getFicha().getColor() == Blanco) {
                    boton.setSelected(false);
                }

            }
        }
    }

    public void ValidarFinal() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (this.juego.getTablero().ObtenerCasiila(i, j).getFicha().getColor() == Blanco
                        || this.juego.getTablero().ObtenerCasiila(i, j).getFicha().getColor() == Rosa) {
                    i = 12;
                    break;
                }

                if (i == 11 && j == 11) {
                    if (juego.getJugador1().getFichasObtenidas() == juego.getJugador2().getFichasObtenidas()) {
                        JOptionPane.showInternalMessageDialog(null, ("  El juego termino en \n Empate con un numero de fichas iguales \n para cada jugador"));
                        break;
                    }
                    if (juego.getJugador1().getFichasObtenidas() < juego.getJugador2().getFichasObtenidas()) {
                        JOptionPane.showInternalMessageDialog(null, ("  Ganador \nJugador: " + juego.getJugador2().getNombre() + "\n Con " + juego.getJugador2().getFichasObtenidas() + " Fichas."));
                        break;

                    }
                    if (juego.getJugador1().getFichasObtenidas() > juego.getJugador2().getFichasObtenidas()) {
                        JOptionPane.showInternalMessageDialog(null, ("  Ganador \nJugador: " + juego.getJugador1().getNombre() + "\n Con " + juego.getJugador1().getFichasObtenidas() + " Fichas."));
                        break;

                    }

                }
            }
        }
    }

    public void AgregarBotonesALista() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                String nombreBoton = "btn" + i + j;

                if (i == 11 && j < 10) {
                    nombreBoton = "btn" + i + "0" + j;
                }
                int finalI = i;
                int finalJ = j;

                try {
                    java.lang.reflect.Field campoBoton = this.getClass().getDeclaredField(nombreBoton);
                    campoBoton.setAccessible(true);

                    ListaBotones[i][j] = (JButton) campoBoton.get(this);
                    ListaBotones[i][j].addActionListener(new ActionListener() {
                        int x = finalI;
                        int z = finalJ;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Aquí va la acción que quieres realizar al hacer clic
                            if (juego.getTablero().ObtenerCasiila(x, z).getFicha().getColor() == Blanco) {
                                juego.ColocarFicha(x, z, juego.ObtenerJugadorActual().getColorDeFicha());

                                juego.CambiarTurno();

                                juego.MostrarPosiblesJugadas();
                                ActivarOpcionesPosible();
                                ActualizarTextosBotones();
                                JugadorActual();
                                ActualizarInformacion();

                                ValidarFinal();
                            }
                        }
                    });
                } catch (Exception e) {
                    System.out.println("No se encontró el botón: " + nombreBoton);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn63 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn1010 = new javax.swing.JButton();
        btn90 = new javax.swing.JButton();
        btn1110 = new javax.swing.JButton();
        btn80 = new javax.swing.JButton();
        btn94 = new javax.swing.JButton();
        btn1011 = new javax.swing.JButton();
        btn95 = new javax.swing.JButton();
        btn76 = new javax.swing.JButton();
        btn1111 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn910 = new javax.swing.JButton();
        btn66 = new javax.swing.JButton();
        btn108 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn77 = new javax.swing.JButton();
        btn410 = new javax.swing.JButton();
        btn67 = new javax.swing.JButton();
        btn411 = new javax.swing.JButton();
        btn02 = new javax.swing.JButton();
        btn70 = new javax.swing.JButton();
        btn03 = new javax.swing.JButton();
        btn48 = new javax.swing.JButton();
        btn06 = new javax.swing.JButton();
        btn07 = new javax.swing.JButton();
        btn04 = new javax.swing.JButton();
        btn05 = new javax.swing.JButton();
        btn010 = new javax.swing.JButton();
        btn011 = new javax.swing.JButton();
        btn08 = new javax.swing.JButton();
        btn09 = new javax.swing.JButton();
        btn911 = new javax.swing.JButton();
        btn1108 = new javax.swing.JButton();
        btn98 = new javax.swing.JButton();
        btn109 = new javax.swing.JButton();
        btn99 = new javax.swing.JButton();
        btn81 = new javax.swing.JButton();
        btn91 = new javax.swing.JButton();
        btn82 = new javax.swing.JButton();
        btn83 = new javax.swing.JButton();
        btn00 = new javax.swing.JButton();
        btn1109 = new javax.swing.JButton();
        btn01 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn110 = new javax.swing.JButton();
        btn111 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn101 = new javax.swing.JButton();
        btn102 = new javax.swing.JButton();
        btn1102 = new javax.swing.JButton();
        btn1103 = new javax.swing.JButton();
        btn1101 = new javax.swing.JButton();
        btn86 = new javax.swing.JButton();
        btn103 = new javax.swing.JButton();
        btn87 = new javax.swing.JButton();
        btn1106 = new javax.swing.JButton();
        btn84 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn106 = new javax.swing.JButton();
        btn85 = new javax.swing.JButton();
        btn1107 = new javax.swing.JButton();
        btn810 = new javax.swing.JButton();
        btn107 = new javax.swing.JButton();
        btn811 = new javax.swing.JButton();
        btn1100 = new javax.swing.JButton();
        btn88 = new javax.swing.JButton();
        btn100 = new javax.swing.JButton();
        btn89 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn210 = new javax.swing.JButton();
        btn310 = new javax.swing.JButton();
        btn211 = new javax.swing.JButton();
        btn311 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn104 = new javax.swing.JButton();
        btn92 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn53 = new javax.swing.JButton();
        btn74 = new javax.swing.JButton();
        btn65 = new javax.swing.JButton();
        btn56 = new javax.swing.JButton();
        btn75 = new javax.swing.JButton();
        btn57 = new javax.swing.JButton();
        btn610 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn710 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn54 = new javax.swing.JButton();
        btn611 = new javax.swing.JButton();
        btn55 = new javax.swing.JButton();
        btn711 = new javax.swing.JButton();
        btn510 = new javax.swing.JButton();
        btn68 = new javax.swing.JButton();
        btn511 = new javax.swing.JButton();
        btn78 = new javax.swing.JButton();
        btn58 = new javax.swing.JButton();
        btn69 = new javax.swing.JButton();
        btn59 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn51 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn60 = new javax.swing.JButton();
        btn49 = new javax.swing.JButton();
        btn64 = new javax.swing.JButton();
        btn52 = new javax.swing.JButton();
        btn93 = new javax.swing.JButton();
        btn1104 = new javax.swing.JButton();
        btn105 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn96 = new javax.swing.JButton();
        btn79 = new javax.swing.JButton();
        btn1105 = new javax.swing.JButton();
        btn61 = new javax.swing.JButton();
        btn97 = new javax.swing.JButton();
        btn62 = new javax.swing.JButton();
        btn72 = new javax.swing.JButton();
        ColorJ2 = new javax.swing.JLabel();
        JlabelJugadorActual = new javax.swing.JLabel();
        btn73 = new javax.swing.JButton();
        btn71 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        JLabelColorDelJugadorActual = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JLabelJugador1 = new javax.swing.JLabel();
        JlabelConteoJ1 = new javax.swing.JLabel();
        JlabelConteoJ2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JLabelJugador2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ColorJ1 = new javax.swing.JLabel();
        JLabelTablero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn63.setContentAreaFilled(false);
        btn63.setFocusPainted(false);
        getContentPane().add(btn63, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 246, 50, 40));

        btn47.setContentAreaFilled(false);
        btn47.setFocusPainted(false);
        getContentPane().add(btn47, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 169, 50, 40));

        btn1010.setContentAreaFilled(false);
        btn1010.setFocusPainted(false);
        getContentPane().add(btn1010, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 402, 50, 40));

        btn90.setContentAreaFilled(false);
        btn90.setFocusPainted(false);
        btn90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn90ActionPerformed(evt);
            }
        });
        getContentPane().add(btn90, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 363, 50, 40));

        btn1110.setContentAreaFilled(false);
        btn1110.setFocusPainted(false);
        getContentPane().add(btn1110, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 442, 50, 40));

        btn80.setContentAreaFilled(false);
        btn80.setFocusPainted(false);
        getContentPane().add(btn80, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 325, 50, 40));

        btn94.setContentAreaFilled(false);
        btn94.setFocusPainted(false);
        getContentPane().add(btn94, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 363, 50, 40));

        btn1011.setContentAreaFilled(false);
        btn1011.setFocusPainted(false);
        getContentPane().add(btn1011, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 402, 50, 40));

        btn95.setContentAreaFilled(false);
        btn95.setFocusPainted(false);
        getContentPane().add(btn95, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 363, 50, 40));

        btn76.setContentAreaFilled(false);
        btn76.setFocusPainted(false);
        getContentPane().add(btn76, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 285, 50, 40));

        btn1111.setContentAreaFilled(false);
        btn1111.setFocusPainted(false);
        getContentPane().add(btn1111, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 442, 50, 40));

        btn44.setContentAreaFilled(false);
        btn44.setFocusPainted(false);
        getContentPane().add(btn44, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 169, 50, 40));

        btn910.setContentAreaFilled(false);
        btn910.setFocusPainted(false);
        getContentPane().add(btn910, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 363, 50, 40));

        btn66.setContentAreaFilled(false);
        btn66.setFocusPainted(false);
        getContentPane().add(btn66, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 246, 50, 40));

        btn108.setContentAreaFilled(false);
        btn108.setFocusPainted(false);
        getContentPane().add(btn108, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 402, 50, 40));

        btn45.setContentAreaFilled(false);
        btn45.setFocusPainted(false);
        getContentPane().add(btn45, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 169, 50, 40));

        btn77.setContentAreaFilled(false);
        btn77.setFocusPainted(false);
        getContentPane().add(btn77, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 285, 50, 40));

        btn410.setContentAreaFilled(false);
        btn410.setFocusPainted(false);
        getContentPane().add(btn410, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 169, 50, 40));

        btn67.setContentAreaFilled(false);
        btn67.setFocusPainted(false);
        getContentPane().add(btn67, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 246, 50, 40));

        btn411.setContentAreaFilled(false);
        btn411.setFocusPainted(false);
        getContentPane().add(btn411, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 169, 50, 40));

        btn02.setContentAreaFilled(false);
        btn02.setFocusPainted(false);
        getContentPane().add(btn02, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 13, 50, 38));

        btn70.setContentAreaFilled(false);
        btn70.setFocusPainted(false);
        getContentPane().add(btn70, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 285, 50, 40));

        btn03.setContentAreaFilled(false);
        btn03.setFocusPainted(false);
        getContentPane().add(btn03, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, 50, 38));

        btn48.setContentAreaFilled(false);
        btn48.setFocusPainted(false);
        getContentPane().add(btn48, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 169, 50, 40));

        btn06.setContentAreaFilled(false);
        btn06.setFocusPainted(false);
        getContentPane().add(btn06, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 13, 50, 38));

        btn07.setContentAreaFilled(false);
        btn07.setFocusPainted(false);
        getContentPane().add(btn07, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 13, 50, 38));

        btn04.setContentAreaFilled(false);
        btn04.setFocusPainted(false);
        getContentPane().add(btn04, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 13, 50, 38));

        btn05.setContentAreaFilled(false);
        btn05.setFocusPainted(false);
        getContentPane().add(btn05, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 13, 50, 38));

        btn010.setContentAreaFilled(false);
        btn010.setFocusPainted(false);
        getContentPane().add(btn010, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 13, 50, 38));

        btn011.setContentAreaFilled(false);
        btn011.setFocusPainted(false);
        getContentPane().add(btn011, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 13, 50, 38));

        btn08.setContentAreaFilled(false);
        btn08.setFocusPainted(false);
        getContentPane().add(btn08, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 13, 50, 38));

        btn09.setContentAreaFilled(false);
        btn09.setFocusPainted(false);
        getContentPane().add(btn09, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 13, 50, 38));

        btn911.setContentAreaFilled(false);
        btn911.setFocusPainted(false);
        getContentPane().add(btn911, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 363, 50, 40));

        btn1108.setContentAreaFilled(false);
        btn1108.setFocusPainted(false);
        btn1108.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1108ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1108, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 442, 50, 40));

        btn98.setContentAreaFilled(false);
        btn98.setFocusPainted(false);
        getContentPane().add(btn98, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 363, 50, 40));

        btn109.setContentAreaFilled(false);
        btn109.setFocusPainted(false);
        getContentPane().add(btn109, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 402, 50, 40));

        btn99.setContentAreaFilled(false);
        btn99.setFocusPainted(false);
        getContentPane().add(btn99, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 363, 50, 40));

        btn81.setContentAreaFilled(false);
        btn81.setFocusPainted(false);
        getContentPane().add(btn81, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 325, 50, 40));

        btn91.setContentAreaFilled(false);
        btn91.setFocusPainted(false);
        getContentPane().add(btn91, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 363, 50, 40));

        btn82.setContentAreaFilled(false);
        btn82.setFocusPainted(false);
        getContentPane().add(btn82, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 325, 50, 40));

        btn83.setContentAreaFilled(false);
        btn83.setFocusPainted(false);
        getContentPane().add(btn83, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 325, 50, 40));

        btn00.setBackground(new java.awt.Color(255, 0, 0));
        btn00.setContentAreaFilled(false);
        btn00.setDefaultCapable(false);
        getContentPane().add(btn00, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 13, 50, 38));

        btn1109.setContentAreaFilled(false);
        btn1109.setFocusPainted(false);
        getContentPane().add(btn1109, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 442, 50, 40));

        btn01.setContentAreaFilled(false);
        btn01.setFocusPainted(false);
        getContentPane().add(btn01, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 13, 50, 38));

        btn12.setContentAreaFilled(false);
        btn12.setFocusPainted(false);
        getContentPane().add(btn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 51, 50, 40));

        btn13.setContentAreaFilled(false);
        btn13.setFocusPainted(false);
        getContentPane().add(btn13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 51, 50, 40));

        btn16.setContentAreaFilled(false);
        btn16.setFocusPainted(false);
        getContentPane().add(btn16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 51, 50, 40));

        btn17.setContentAreaFilled(false);
        btn17.setFocusPainted(false);
        getContentPane().add(btn17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 51, 50, 40));

        btn10.setContentAreaFilled(false);
        btn10.setFocusPainted(false);
        getContentPane().add(btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 51, 50, 40));

        btn14.setContentAreaFilled(false);
        btn14.setFocusPainted(false);
        getContentPane().add(btn14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 51, 50, 40));

        btn15.setContentAreaFilled(false);
        btn15.setFocusPainted(false);
        getContentPane().add(btn15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 51, 50, 40));

        btn110.setContentAreaFilled(false);
        btn110.setFocusPainted(false);
        getContentPane().add(btn110, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 51, 50, 40));

        btn111.setContentAreaFilled(false);
        btn111.setFocusPainted(false);
        getContentPane().add(btn111, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 51, 50, 40));

        btn18.setContentAreaFilled(false);
        btn18.setFocusPainted(false);
        getContentPane().add(btn18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 51, 50, 40));

        btn101.setContentAreaFilled(false);
        btn101.setFocusPainted(false);
        getContentPane().add(btn101, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 402, 50, 40));

        btn102.setContentAreaFilled(false);
        btn102.setFocusPainted(false);
        getContentPane().add(btn102, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 402, 50, 40));

        btn1102.setContentAreaFilled(false);
        btn1102.setFocusPainted(false);
        getContentPane().add(btn1102, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 442, 50, 40));

        btn1103.setContentAreaFilled(false);
        btn1103.setFocusPainted(false);
        getContentPane().add(btn1103, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 442, 50, 40));

        btn1101.setContentAreaFilled(false);
        btn1101.setFocusPainted(false);
        getContentPane().add(btn1101, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 442, 50, 40));

        btn86.setContentAreaFilled(false);
        btn86.setFocusPainted(false);
        getContentPane().add(btn86, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 325, 50, 40));

        btn103.setContentAreaFilled(false);
        btn103.setFocusPainted(false);
        getContentPane().add(btn103, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 402, 50, 40));

        btn87.setContentAreaFilled(false);
        btn87.setFocusPainted(false);
        getContentPane().add(btn87, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 325, 50, 40));

        btn1106.setContentAreaFilled(false);
        btn1106.setFocusPainted(false);
        getContentPane().add(btn1106, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 442, 50, 40));

        btn84.setContentAreaFilled(false);
        btn84.setFocusPainted(false);
        getContentPane().add(btn84, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 325, 50, 40));

        btn19.setContentAreaFilled(false);
        btn19.setFocusPainted(false);
        getContentPane().add(btn19, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 51, 50, 40));

        btn11.setContentAreaFilled(false);
        btn11.setFocusPainted(false);
        getContentPane().add(btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 51, 50, 40));

        btn22.setContentAreaFilled(false);
        btn22.setFocusPainted(false);
        getContentPane().add(btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 93, 50, 40));

        btn33.setContentAreaFilled(false);
        btn33.setFocusPainted(false);
        getContentPane().add(btn33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 131, 50, 40));

        btn23.setContentAreaFilled(false);
        btn23.setFocusPainted(false);
        getContentPane().add(btn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 93, 50, 40));

        btn36.setContentAreaFilled(false);
        btn36.setFocusPainted(false);
        getContentPane().add(btn36, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 131, 50, 40));

        btn26.setContentAreaFilled(false);
        btn26.setFocusPainted(false);
        getContentPane().add(btn26, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 93, 50, 40));

        btn37.setContentAreaFilled(false);
        btn37.setFocusPainted(false);
        getContentPane().add(btn37, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 131, 50, 40));

        btn27.setContentAreaFilled(false);
        btn27.setFocusPainted(false);
        getContentPane().add(btn27, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 93, 50, 40));

        btn30.setContentAreaFilled(false);
        btn30.setFocusPainted(false);
        getContentPane().add(btn30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 131, 50, 40));

        btn106.setContentAreaFilled(false);
        btn106.setFocusPainted(false);
        getContentPane().add(btn106, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 402, 50, 40));

        btn85.setContentAreaFilled(false);
        btn85.setFocusPainted(false);
        getContentPane().add(btn85, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 325, 50, 40));

        btn1107.setContentAreaFilled(false);
        btn1107.setFocusPainted(false);
        getContentPane().add(btn1107, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 442, 50, 40));

        btn810.setContentAreaFilled(false);
        btn810.setFocusPainted(false);
        getContentPane().add(btn810, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 325, 50, 40));

        btn107.setContentAreaFilled(false);
        btn107.setFocusPainted(false);
        getContentPane().add(btn107, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 402, 50, 40));

        btn811.setContentAreaFilled(false);
        btn811.setFocusPainted(false);
        getContentPane().add(btn811, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 325, 50, 40));

        btn1100.setContentAreaFilled(false);
        btn1100.setFocusPainted(false);
        btn1100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1100ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1100, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 442, 50, 40));

        btn88.setContentAreaFilled(false);
        btn88.setFocusPainted(false);
        getContentPane().add(btn88, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 325, 50, 40));

        btn100.setContentAreaFilled(false);
        btn100.setFocusPainted(false);
        getContentPane().add(btn100, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 402, 50, 40));

        btn89.setContentAreaFilled(false);
        btn89.setFocusPainted(false);
        getContentPane().add(btn89, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 325, 50, 40));

        btn20.setContentAreaFilled(false);
        btn20.setFocusPainted(false);
        getContentPane().add(btn20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 93, 50, 40));

        btn24.setContentAreaFilled(false);
        btn24.setFocusPainted(false);
        getContentPane().add(btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 93, 50, 40));

        btn34.setContentAreaFilled(false);
        btn34.setFocusPainted(false);
        getContentPane().add(btn34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 131, 50, 40));

        btn25.setContentAreaFilled(false);
        btn25.setFocusPainted(false);
        getContentPane().add(btn25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 93, 50, 40));

        btn35.setContentAreaFilled(false);
        btn35.setFocusPainted(false);
        getContentPane().add(btn35, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 131, 50, 40));

        btn210.setContentAreaFilled(false);
        btn210.setFocusPainted(false);
        getContentPane().add(btn210, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 93, 50, 40));

        btn310.setContentAreaFilled(false);
        btn310.setFocusPainted(false);
        getContentPane().add(btn310, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 131, 50, 40));

        btn211.setContentAreaFilled(false);
        btn211.setFocusPainted(false);
        getContentPane().add(btn211, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 93, 50, 40));

        btn311.setContentAreaFilled(false);
        btn311.setFocusPainted(false);
        getContentPane().add(btn311, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 131, 50, 40));

        btn28.setContentAreaFilled(false);
        btn28.setFocusPainted(false);
        getContentPane().add(btn28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 93, 50, 40));

        btn104.setContentAreaFilled(false);
        btn104.setFocusPainted(false);
        getContentPane().add(btn104, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 402, 50, 40));

        btn92.setContentAreaFilled(false);
        btn92.setFocusPainted(false);
        getContentPane().add(btn92, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 363, 50, 40));

        btn38.setContentAreaFilled(false);
        btn38.setFocusPainted(false);
        getContentPane().add(btn38, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 131, 50, 40));

        btn29.setContentAreaFilled(false);
        btn29.setFocusPainted(false);
        getContentPane().add(btn29, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 93, 50, 40));

        btn39.setContentAreaFilled(false);
        btn39.setFocusPainted(false);
        getContentPane().add(btn39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 131, 50, 40));

        btn21.setContentAreaFilled(false);
        btn21.setFocusPainted(false);
        getContentPane().add(btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 93, 50, 40));

        btn32.setContentAreaFilled(false);
        btn32.setFocusPainted(false);
        getContentPane().add(btn32, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 131, 50, 40));

        btn31.setContentAreaFilled(false);
        btn31.setFocusPainted(false);
        getContentPane().add(btn31, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 131, 50, 40));

        btn53.setContentAreaFilled(false);
        btn53.setFocusPainted(false);
        getContentPane().add(btn53, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 207, 50, 40));

        btn74.setContentAreaFilled(false);
        btn74.setFocusPainted(false);
        getContentPane().add(btn74, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 285, 50, 40));

        btn65.setContentAreaFilled(false);
        btn65.setFocusPainted(false);
        getContentPane().add(btn65, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 246, 50, 40));

        btn56.setContentAreaFilled(false);
        btn56.setFocusPainted(false);
        getContentPane().add(btn56, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 207, 50, 40));

        btn75.setContentAreaFilled(false);
        btn75.setFocusPainted(false);
        getContentPane().add(btn75, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 285, 50, 40));

        btn57.setContentAreaFilled(false);
        btn57.setFocusPainted(false);
        getContentPane().add(btn57, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 207, 50, 40));

        btn610.setContentAreaFilled(false);
        btn610.setFocusPainted(false);
        getContentPane().add(btn610, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 246, 50, 40));

        btn50.setContentAreaFilled(false);
        btn50.setFocusPainted(false);
        getContentPane().add(btn50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 207, 50, 40));

        btn710.setContentAreaFilled(false);
        btn710.setFocusPainted(false);
        getContentPane().add(btn710, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 285, 50, 40));

        btn40.setContentAreaFilled(false);
        btn40.setFocusPainted(false);
        getContentPane().add(btn40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 169, 50, 40));

        btn54.setContentAreaFilled(false);
        btn54.setFocusPainted(false);
        getContentPane().add(btn54, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 207, 50, 40));

        btn611.setContentAreaFilled(false);
        btn611.setFocusPainted(false);
        getContentPane().add(btn611, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 246, 50, 40));

        btn55.setContentAreaFilled(false);
        btn55.setFocusPainted(false);
        getContentPane().add(btn55, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 207, 50, 40));

        btn711.setContentAreaFilled(false);
        btn711.setFocusPainted(false);
        getContentPane().add(btn711, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 285, 50, 40));

        btn510.setContentAreaFilled(false);
        btn510.setFocusPainted(false);
        getContentPane().add(btn510, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 207, 50, 40));

        btn68.setContentAreaFilled(false);
        btn68.setFocusPainted(false);
        getContentPane().add(btn68, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 246, 50, 40));

        btn511.setContentAreaFilled(false);
        btn511.setFocusPainted(false);
        getContentPane().add(btn511, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 207, 50, 40));

        btn78.setContentAreaFilled(false);
        btn78.setFocusPainted(false);
        getContentPane().add(btn78, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 285, 50, 40));

        btn58.setContentAreaFilled(false);
        btn58.setFocusPainted(false);
        getContentPane().add(btn58, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 207, 50, 40));

        btn69.setContentAreaFilled(false);
        btn69.setFocusPainted(false);
        getContentPane().add(btn69, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 246, 50, 40));

        btn59.setContentAreaFilled(false);
        btn59.setFocusPainted(false);
        getContentPane().add(btn59, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 207, 50, 40));

        btn41.setContentAreaFilled(false);
        btn41.setFocusPainted(false);
        getContentPane().add(btn41, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 169, 50, 40));

        btn51.setContentAreaFilled(false);
        btn51.setFocusPainted(false);
        getContentPane().add(btn51, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 207, 50, 40));

        btn42.setContentAreaFilled(false);
        btn42.setFocusPainted(false);
        getContentPane().add(btn42, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 169, 50, 40));

        btn60.setContentAreaFilled(false);
        btn60.setFocusPainted(false);
        getContentPane().add(btn60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 246, 50, 40));

        btn49.setContentAreaFilled(false);
        btn49.setFocusPainted(false);
        getContentPane().add(btn49, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 169, 50, 40));

        btn64.setContentAreaFilled(false);
        btn64.setFocusPainted(false);
        getContentPane().add(btn64, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 246, 50, 40));

        btn52.setContentAreaFilled(false);
        btn52.setFocusPainted(false);
        getContentPane().add(btn52, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 207, 50, 40));

        btn93.setContentAreaFilled(false);
        btn93.setFocusPainted(false);
        getContentPane().add(btn93, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 363, 50, 40));

        btn1104.setContentAreaFilled(false);
        btn1104.setFocusPainted(false);
        getContentPane().add(btn1104, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 442, 50, 40));

        btn105.setContentAreaFilled(false);
        btn105.setFocusPainted(false);
        getContentPane().add(btn105, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 402, 50, 40));

        btn43.setContentAreaFilled(false);
        btn43.setFocusPainted(false);
        getContentPane().add(btn43, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 169, 50, 40));

        btn96.setContentAreaFilled(false);
        btn96.setFocusPainted(false);
        getContentPane().add(btn96, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 363, 50, 40));

        btn79.setContentAreaFilled(false);
        btn79.setFocusPainted(false);
        getContentPane().add(btn79, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 285, 50, 40));

        btn1105.setContentAreaFilled(false);
        btn1105.setFocusPainted(false);
        getContentPane().add(btn1105, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 442, 50, 40));

        btn61.setContentAreaFilled(false);
        btn61.setFocusPainted(false);
        getContentPane().add(btn61, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 246, 50, 40));

        btn97.setContentAreaFilled(false);
        btn97.setFocusPainted(false);
        getContentPane().add(btn97, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 363, 50, 40));

        btn62.setContentAreaFilled(false);
        btn62.setFocusPainted(false);
        getContentPane().add(btn62, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 246, 50, 40));

        btn72.setContentAreaFilled(false);
        btn72.setFocusPainted(false);
        getContentPane().add(btn72, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 285, 50, 40));

        ColorJ2.setText("asd");
        ColorJ2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(ColorJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 110, 30));
        getContentPane().add(JlabelJugadorActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 90, 30));

        btn73.setContentAreaFilled(false);
        btn73.setFocusPainted(false);
        getContentPane().add(btn73, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 285, 50, 40));

        btn71.setContentAreaFilled(false);
        btn71.setFocusPainted(false);
        getContentPane().add(btn71, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 285, 50, 40));

        btn46.setContentAreaFilled(false);
        btn46.setFocusPainted(false);
        getContentPane().add(btn46, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 169, 50, 40));
        getContentPane().add(JLabelColorDelJugadorActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 90, 30));

        jLabel1.setText("Jugador Actual");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 110, 30));

        jLabel2.setText("Color Actual");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 536, 70, 20));

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Jugador 1:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 100, 30));

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Fichas:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 70, 30));

        JLabelJugador1.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(JLabelJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 70, 30));

        JlabelConteoJ1.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(JlabelConteoJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 60, 30));
        getContentPane().add(JlabelConteoJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 550, 60, 30));

        jLabel5.setText("Fichas:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 70, 30));
        getContentPane().add(JLabelJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 70, 30));

        jLabel6.setText("Jugador 2:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, 100, 30));

        ColorJ1.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(ColorJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 150, 30));

        JLabelTablero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablero.jpg"))); // NOI18N
        getContentPane().add(JLabelTablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -25, 700, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1108ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn1108ActionPerformed

    private void btn1100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn1100ActionPerformed

    private void btn90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn90ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn90ActionPerformed

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
            java.util.logging.Logger.getLogger(JframeTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JframeTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JframeTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JframeTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JframeTablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ColorJ1;
    private javax.swing.JLabel ColorJ2;
    private javax.swing.JLabel JLabelColorDelJugadorActual;
    private javax.swing.JLabel JLabelJugador1;
    private javax.swing.JLabel JLabelJugador2;
    private javax.swing.JLabel JLabelTablero;
    private javax.swing.JLabel JlabelConteoJ1;
    private javax.swing.JLabel JlabelConteoJ2;
    private javax.swing.JLabel JlabelJugadorActual;
    private javax.swing.JButton btn00;
    private javax.swing.JButton btn01;
    private javax.swing.JButton btn010;
    private javax.swing.JButton btn011;
    private javax.swing.JButton btn02;
    private javax.swing.JButton btn03;
    private javax.swing.JButton btn04;
    private javax.swing.JButton btn05;
    private javax.swing.JButton btn06;
    private javax.swing.JButton btn07;
    private javax.swing.JButton btn08;
    private javax.swing.JButton btn09;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn100;
    private javax.swing.JButton btn101;
    private javax.swing.JButton btn1010;
    private javax.swing.JButton btn1011;
    private javax.swing.JButton btn102;
    private javax.swing.JButton btn103;
    private javax.swing.JButton btn104;
    private javax.swing.JButton btn105;
    private javax.swing.JButton btn106;
    private javax.swing.JButton btn107;
    private javax.swing.JButton btn108;
    private javax.swing.JButton btn109;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn110;
    private javax.swing.JButton btn1100;
    private javax.swing.JButton btn1101;
    private javax.swing.JButton btn1102;
    private javax.swing.JButton btn1103;
    private javax.swing.JButton btn1104;
    private javax.swing.JButton btn1105;
    private javax.swing.JButton btn1106;
    private javax.swing.JButton btn1107;
    private javax.swing.JButton btn1108;
    private javax.swing.JButton btn1109;
    private javax.swing.JButton btn111;
    private javax.swing.JButton btn1110;
    private javax.swing.JButton btn1111;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn210;
    private javax.swing.JButton btn211;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn310;
    private javax.swing.JButton btn311;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn38;
    private javax.swing.JButton btn39;
    private javax.swing.JButton btn40;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn410;
    private javax.swing.JButton btn411;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn48;
    private javax.swing.JButton btn49;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn51;
    private javax.swing.JButton btn510;
    private javax.swing.JButton btn511;
    private javax.swing.JButton btn52;
    private javax.swing.JButton btn53;
    private javax.swing.JButton btn54;
    private javax.swing.JButton btn55;
    private javax.swing.JButton btn56;
    private javax.swing.JButton btn57;
    private javax.swing.JButton btn58;
    private javax.swing.JButton btn59;
    private javax.swing.JButton btn60;
    private javax.swing.JButton btn61;
    private javax.swing.JButton btn610;
    private javax.swing.JButton btn611;
    private javax.swing.JButton btn62;
    private javax.swing.JButton btn63;
    private javax.swing.JButton btn64;
    private javax.swing.JButton btn65;
    private javax.swing.JButton btn66;
    private javax.swing.JButton btn67;
    private javax.swing.JButton btn68;
    private javax.swing.JButton btn69;
    private javax.swing.JButton btn70;
    private javax.swing.JButton btn71;
    private javax.swing.JButton btn710;
    private javax.swing.JButton btn711;
    private javax.swing.JButton btn72;
    private javax.swing.JButton btn73;
    private javax.swing.JButton btn74;
    private javax.swing.JButton btn75;
    private javax.swing.JButton btn76;
    private javax.swing.JButton btn77;
    private javax.swing.JButton btn78;
    private javax.swing.JButton btn79;
    private javax.swing.JButton btn80;
    private javax.swing.JButton btn81;
    private javax.swing.JButton btn810;
    private javax.swing.JButton btn811;
    private javax.swing.JButton btn82;
    private javax.swing.JButton btn83;
    private javax.swing.JButton btn84;
    private javax.swing.JButton btn85;
    private javax.swing.JButton btn86;
    private javax.swing.JButton btn87;
    private javax.swing.JButton btn88;
    private javax.swing.JButton btn89;
    private javax.swing.JButton btn90;
    private javax.swing.JButton btn91;
    private javax.swing.JButton btn910;
    private javax.swing.JButton btn911;
    private javax.swing.JButton btn92;
    private javax.swing.JButton btn93;
    private javax.swing.JButton btn94;
    private javax.swing.JButton btn95;
    private javax.swing.JButton btn96;
    private javax.swing.JButton btn97;
    private javax.swing.JButton btn98;
    private javax.swing.JButton btn99;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
