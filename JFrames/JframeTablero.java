/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrames;


import Objetos.Juego;
import static Utilizables.Colores.Blanco;
import static Utilizables.Colores.Rosa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.ColorJ1.setText(String.valueOf(juego.getJugador1().getFichasObtenidas()));
        this.JlabelConteoJ2.setText(String.valueOf(juego.getJugador2().getFichasObtenidas()));
    }

    public void JugadorActual() {
        this.JlabelJugadorActual.setText(this.juego.ObtenerJugadorActual().getNombre());
        this.JlabelJugadorActual.setText(String.valueOf(this.juego.ObtenerJugadorActual().getColorDeFicha()));
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLabelTablero = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ColorJ1 = new javax.swing.JLabel();
        JlabelJugadorActual = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JLabelJugador2 = new javax.swing.JLabel();
        JlabelConteoJ2 = new javax.swing.JLabel();
        ColorJ2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JLabelJugador1 = new javax.swing.JLabel();
        JlabelConteoJ4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLabelTablero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablero.jpg"))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Jugador 1:");

        ColorJ1.setForeground(new java.awt.Color(255, 0, 0));
        ColorJ1.setText("asd");

        jLabel2.setText("Color Actual");

        jLabel1.setText("Jugador Actual");

        JlabelConteoJ2.setText("Fichas:");

        ColorJ2.setText("asd");
        ColorJ2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setText("Jugador 2:");

        JLabelJugador1.setForeground(new java.awt.Color(255, 0, 0));
        JLabelJugador1.setText("Jugador1");

        JlabelConteoJ4.setForeground(new java.awt.Color(255, 0, 0));
        JlabelConteoJ4.setText("Fichas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLabelJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(JLabelJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ColorJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JlabelJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(JlabelConteoJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ColorJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(204, 204, 204))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JLabelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(351, 351, 351)
                    .addComponent(JlabelConteoJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(616, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 490, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(ColorJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ColorJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(JlabelConteoJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLabelJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(JLabelJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JlabelJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JLabelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(554, Short.MAX_VALUE)
                    .addComponent(JlabelConteoJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel JLabelJugador1;
    private javax.swing.JLabel JLabelJugador2;
    private javax.swing.JLabel JLabelTablero;
    private javax.swing.JLabel JlabelConteoJ2;
    private javax.swing.JLabel JlabelConteoJ4;
    private javax.swing.JLabel JlabelJugadorActual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
