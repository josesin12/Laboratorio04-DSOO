// Archivo: PanelTablero.java
// Archivo: PanelTablero.java

package Laboratorio_Dsoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTablero extends JPanel {
    private Juego juego;
    private final int CELL_SIZE = 50; 
    private Soldado soldadoSeleccionado = null;

    public PanelTablero(Juego juego) {
        this.juego = juego;
        int filas = juego.getTablero().getFilas();
        int columnas = juego.getTablero().getColumnas();
        
        setPreferredSize(new Dimension(columnas * CELL_SIZE, filas * CELL_SIZE));
        setBackground(Color.LIGHT_GRAY);
        
        // Añadir el MouseListener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClick(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tablero tablero = juego.getTablero();
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();
        
        // 1. Dibujar la cuadrícula
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(Color.WHITE);
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Soldado s = tablero.getSoldado(i, j);
                if (s != null) {
                    if (s == soldadoSeleccionado) {
                        g.setColor(Color.YELLOW);
                        g.fillRect(j * CELL_SIZE + 1, i * CELL_SIZE + 1, CELL_SIZE - 2, CELL_SIZE - 2);
                    }
                    dibujarSoldado(g, s, i, j);
                }
            }
        }
        
        // 3. Mostrar mensaje de fin de juego si aplica
        if (juego.hayGanador()) {
            g.setColor(new Color(0, 0, 0, 150)); 
            g.fillRect(0, 0, getWidth(), getHeight());
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String mensaje = "¡Gana el ejército " + juego.getGanador() + "!";
            
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
            int y = getHeight() / 2;
            g.drawString(mensaje, x, y);
        }
    }

    private void dibujarSoldado(Graphics g, Soldado s, int fila, int columna) {
        int x = columna * CELL_SIZE;
        int y = fila * CELL_SIZE;
        Color colorEjercito = s.getEjercito().equals("Inglaterra") ? new Color(200, 50, 50) : new Color(50, 50, 200);
        
        g.setColor(colorEjercito);
        g.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        String tipo = s.getClass().getSimpleName().substring(0, 1);
        String idStr = s.getNombre().substring(s.getNombre().length() - 1);
        String etiqueta = tipo + idStr;
        
        FontMetrics fm = g.getFontMetrics();
        int etiquetaX = x + (CELL_SIZE - fm.stringWidth(etiqueta)) / 2;
        int etiquetaY = y + (fm.getAscent() + (CELL_SIZE - (fm.getAscent() + fm.getDescent())) / 2);
        
        g.drawString(etiqueta, etiquetaX, etiquetaY);
    }
    private void manejarClick(int x, int y) {
        if (juego.hayGanador()) return;

        int fila = y / CELL_SIZE;
        int columna = x / CELL_SIZE;
        
        Soldado clicado = juego.getSoldadoEnPosicion(fila, columna);
        
        if (soldadoSeleccionado == null) {
            if (clicado != null && clicado.getEjercito().equals(juego.getEjercitoActual().getNombre())) {
                soldadoSeleccionado = clicado;
                System.out.println("Seleccionado: " + clicado.getNombre() + ". Elija destino.");
            }
        } else {
            if (clicado == soldadoSeleccionado) {
                soldadoSeleccionado = null; // Deseleccionar
                System.out.println("Deseleccionado.");
            } else {
                String[] opcionesActitud = {"ofensiva", "defensiva", "retirada"};
                String actitudElegida = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione la actitud para " + soldadoSeleccionado.getNombre() + 
                    "\nPosición actual: (" + soldadoSeleccionado.getFila() + "," + soldadoSeleccionado.getColumna() + ")" +
                    "\nPosición de destino: (" + fila + "," + columna + ")",
                    "Selección de Actitud",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesActitud,
                    soldadoSeleccionado.getActitud() 
                );

                if (actitudElegida == null) {
                    System.out.println("Acción cancelada.");
                    return;
                }
                
                soldadoSeleccionado.setActitud(actitudElegida);
                boolean movido = juego.moverSoldado(soldadoSeleccionado, fila, columna);
                
                if (movido) {
                    System.out.println(soldadoSeleccionado.getNombre() + " se movió/atacó con actitud " + actitudElegida);
                    soldadoSeleccionado = null; 
                    juego.cambiarTurno(); 
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    if (frame != null) {
                        frame.setTitle("Juego de Estrategia - Turno: " + juego.getTurno() + " (" + juego.getEjercitoActual().getNombre() + ")");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Movimiento inválido o demasiado lejos.", "Error de Movimiento", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Movimiento inválido. El soldado permanece seleccionado.");
                }
            }
        }
        
        repaint();
    }
}