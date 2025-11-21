package Laboratorio_Dsoo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class PanelTablero extends JPanel {
    private Juego juego;
    private final int CELL_SIZE = 50; 
    private final int INFO_HEIGHT = 70; 
    private Soldado soldadoSeleccionado = null;

    public PanelTablero(Juego juego) {
        this.juego = juego;
        int filas = juego.getTablero().getFilas();
        int columnas = juego.getTablero().getColumnas();
        
        setPreferredSize(new Dimension(columnas * CELL_SIZE, filas * CELL_SIZE + INFO_HEIGHT)); 
        setBackground(Color.LIGHT_GRAY);
        
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
        int boardHeight = filas * CELL_SIZE; 
        
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                g.setColor(Color.WHITE); g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY); g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
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

        
        g.setColor(Color.DARK_GRAY); g.fillRect(0, boardHeight, getWidth(), INFO_HEIGHT); 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    
        g.setColor(new Color(255, 204, 0)); g.setFont(new Font("SansSerif", Font.BOLD, 16));
        String infoTurno = String.format("TURNO %d | Juega: %s", juego.getTurno(), juego.getEjercitoActual().getNombre().toUpperCase());
        g.drawString(infoTurno, 20, boardHeight + 20); 

        g.setColor(Color.WHITE);
        String infoMapa = "Territorio: " + juego.getMapa().getTipoTerritorio().toUpperCase();
        FontMetrics fmMapa = g.getFontMetrics();
        int xMapa = getWidth() - fmMapa.stringWidth(infoMapa) - 20;
        g.drawString(infoMapa, xMapa, boardHeight + 20); 

        
        if (soldadoSeleccionado != null) {
            g.setColor(new Color(255, 255, 255, 200)); g.fillRect(0, boardHeight + 35, getWidth(), INFO_HEIGHT - 35); 
            g.setColor(Color.BLACK); g.setFont(new Font("SansSerif", Font.PLAIN, 12));
            String tipoClase = soldadoSeleccionado.getClass().getSimpleName();
            String infoUnidad = String.format(
                "UNIDAD: %s (%s) | Vida: %d/%d | Atq: %d | Def: %d | Actitud: %s | Vel: %d | Pos: (%d,%d)",
                soldadoSeleccionado.getNombre(), tipoClase, soldadoSeleccionado.getVidaActual(),
                soldadoSeleccionado.getNivelVida(), soldadoSeleccionado.getNivelAtaque(), 
                soldadoSeleccionado.getNivelDefensa(), soldadoSeleccionado.getActitud().toUpperCase(),
                soldadoSeleccionado.getVelocidad(), soldadoSeleccionado.getFila(), soldadoSeleccionado.getColumna()
            );
            g.drawString(infoUnidad, 10, boardHeight + 55); 
        } else {
             g.setColor(new Color(150, 150, 150)); g.setFont(new Font("SansSerif", Font.ITALIC, 12));
             g.drawString("Haga clic en un soldado de su ejército para ver detalles y realizar una acción.", 10, boardHeight + 55);
        }
        
        
        if (juego.hayGanador()) {
            g.setColor(new Color(0, 0, 0, 150)); g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE); g.setFont(new Font("Arial", Font.BOLD, 30));
            String mensaje = "¡Gana el ejército " + juego.getGanador() + "!";
            FontMetrics fm = g.getFontMetrics();
            g.drawString(mensaje, (getWidth() - fm.stringWidth(mensaje)) / 2, getHeight() / 2);
        }
    }

    private void dibujarSoldado(Graphics g, Soldado s, int fila, int columna) {
        int x = columna * CELL_SIZE; int y = fila * CELL_SIZE;
        Color colorEjercito = s.getEjercito().equals("Inglaterra") ? new Color(200, 50, 50) : new Color(50, 50, 200);
        
        g.setColor(colorEjercito); g.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
        g.setColor(Color.WHITE); g.setFont(new Font("Arial", Font.BOLD, 12));
        
        String tipo = s.getClass().getSimpleName().substring(0, 1);
        String idStr = s.getNombre().replaceAll("[^0-9]", ""); 
        String etiqueta = tipo + idStr;
        
        FontMetrics fm = g.getFontMetrics();
        int etiquetaX = x + (CELL_SIZE - fm.stringWidth(etiqueta)) / 2;
        int etiquetaY = y + (fm.getAscent() + (CELL_SIZE - (fm.getAscent() + fm.getDescent())) / 2);
        
        g.drawString(etiqueta, etiquetaX, etiquetaY);
    }
    
    // --- LÓGICA PRINCIPAL DE CLIC ---
    private void manejarClick(int x, int y) {
        if (juego.hayGanador() || y / CELL_SIZE >= juego.getTablero().getFilas()) {
            repaint();
            return;
        }

        int fila = y / CELL_SIZE;
        int columna = x / CELL_SIZE;
        Soldado clicado = juego.getSoldadoEnPosicion(fila, columna);
        
        if (soldadoSeleccionado == null) {
            if (clicado != null && clicado.getEjercito().equals(juego.getEjercitoActual().getNombre())) {
                soldadoSeleccionado = clicado;
                
                // Mostrar opciones de acción/movimiento inmediatamente después de seleccionar
                mostrarMenuAccion(fila, columna);
            }
        } else {
            // Clic en destino
            manejarMovimiento(fila, columna);
        }
        repaint();
    }

    private void mostrarMenuAccion(int fila, int columna) {
        List<String> opciones = new ArrayList<>(Arrays.asList("Movimiento/Ataque", "Deseleccionar"));
        
        // Añadir acciones especiales basadas en la clase del soldado
        if (soldadoSeleccionado instanceof Caballero) {
            Caballero c = (Caballero) soldadoSeleccionado;
            opciones.add(c.isEstaMontando() ? "Desmontar" : "Montar");
            opciones.add("Alternar Arma");
        } else if (soldadoSeleccionado instanceof Espadachin) {
            opciones.add("Muro de Escudos");
        } else if (soldadoSeleccionado instanceof Lancero) {
            opciones.add("Schiltrom");
        } else if (soldadoSeleccionado instanceof Arquero) {
            opciones.add("Disparar");
        }
        
        Object[] opcionesArray = opciones.toArray();
        String eleccion = (String) JOptionPane.showInputDialog(this,
            "Soldado seleccionado: " + soldadoSeleccionado.getNombre() + ".\n¿Qué acción desea realizar?",
            "Acciones Disponibles", JOptionPane.PLAIN_MESSAGE, null, opcionesArray, opcionesArray[0]);

        if (eleccion == null || eleccion.equals("Deseleccionar")) {
            soldadoSeleccionado = null;
            return;
        } else if (eleccion.equals("Movimiento/Ataque")) {
            JOptionPane.showMessageDialog(this, "Ahora haga clic en la casilla de destino para mover/atacar.");
            return; // Esperar el segundo click
        }
        
        // Manejar Acciones Especiales
        switch (eleccion) {
            case "Montar": ((Caballero) soldadoSeleccionado).montar(); break;
            case "Desmontar": ((Caballero) soldadoSeleccionado).desmontar(); break;
            case "Alternar Arma": ((Caballero) soldadoSeleccionado).alternarArma(); break;
            case "Muro de Escudos": ((Espadachin) soldadoSeleccionado).crearMuroEscudos(); break;
            case "Schiltrom": ((Lancero) soldadoSeleccionado).schiltrom(); break;
            case "Disparar": ((Arquero) soldadoSeleccionado).disparar(); break; 
        }
        
        // Después de una acción especial, el turno termina
        soldadoSeleccionado = null; 
        juego.cambiarTurno(); 
        actualizarTituloFrame();
    }

    private void manejarMovimiento(int nuevaFila, int nuevaColumna) {
        String[] opcionesActitud = {"ofensiva", "defensiva", "retirada"};
        String actitudElegida = (String) JOptionPane.showInputDialog(this,
            "Seleccione la actitud para " + soldadoSeleccionado.getNombre() + 
            "\nDestino: (" + nuevaFila + "," + nuevaColumna + ")",
            "Selección de Actitud", JOptionPane.QUESTION_MESSAGE, null, 
            opcionesActitud, soldadoSeleccionado.getActitud());

        if (actitudElegida == null) return;
        
        soldadoSeleccionado.setActitud(actitudElegida);
        if (juego.moverSoldado(soldadoSeleccionado, nuevaFila, nuevaColumna)) {
            soldadoSeleccionado = null; 
            juego.cambiarTurno(); 
            actualizarTituloFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Movimiento inválido o demasiado lejos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarTituloFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setTitle("Juego de Estrategia - Turno: " + juego.getTurno() + " (" + juego.getEjercitoActual().getNombre() + ")");
        }
    }
}