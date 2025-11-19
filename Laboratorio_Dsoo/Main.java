
package Laboratorio_Dsoo;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.ArrayList; 
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // ... (CÓDIGO DE LECTURA DE FILAS/COLUMNAS) ...
    
        System.out.print("Ingrese número de filas del tablero: ");
        int filas = sc.nextInt();
        System.out.print("Ingrese número de columnas del tablero: ");
        int columnas = sc.nextInt();
        sc.nextLine(); 

        int numSoldadosA;
        do {
            System.out.print("Ingrese la cantidad de soldados para el Ejército Inglaterra (1-10): ");
            numSoldadosA = sc.nextInt();
            sc.nextLine();
        } while (numSoldadosA < 1 || numSoldadosA > 10);

        int numSoldadosB;
        do {
            System.out.print("Ingrese la cantidad de soldados para el Ejército Francia (1-10): ");
            numSoldadosB = sc.nextInt();
            sc.nextLine();
        } while (numSoldadosB < 1 || numSoldadosB > 10);            
        Mapa mapa = new Mapa(filas, columnas);
        
        mapa.generarEjercitos(numSoldadosA, numSoldadosB);
        
        System.out.println("\n--- INICIALIZACIÓN DE LA GUI ---");
        
        Juego juego = new Juego(mapa);
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Juego de Estrategia - Turno: " + juego.getTurno() + " (" + juego.getEjercitoActual().getNombre() + ")");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // AÑADIR BARRA DE MENÚ AL FRAME
            frame.setJMenuBar(crearBarraMenu(frame)); // Pasamos el frame para poder cerrarlo
            
            PanelTablero panelTablero = new PanelTablero(juego);
            frame.add(panelTablero, BorderLayout.CENTER);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    
        sc.close();
    }
    
    private static JMenuBar crearBarraMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // MENÚ JUEGO
        JMenu menuJuego = new JMenu("Juego");
        
        JMenuItem itemPausa = new JMenuItem("Pausar/Reanudar");
        itemPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de Pausa (ej. deshabilitar MouseListener del PanelTablero, aunque es más complejo)
                JOptionPane.showMessageDialog(frame, "Funcionalidad de Pausa no implementada aún.", "Pausa", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de que quieres salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose(); // Cierra la ventana
                    System.exit(0);  // Termina el programa
                }
            }
        });
        
        menuJuego.add(itemPausa);
        menuJuego.addSeparator(); // Línea de separación
        menuJuego.add(itemSalir);
        
        // MENÚ AYUDA
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de...");
        itemAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Juego de Estrategia - Versión 1.0\nDesarrollado en POO con Java Swing.", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuAyuda.add(itemAcercaDe);
        
        // AÑADIR MENÚS A LA BARRA
        menuBar.add(menuJuego);
        menuBar.add(menuAyuda);
        
        return menuBar;
    }


    private static ArrayList<Soldado> obtenerSoldadosVivos(Ejercito ejercito) {
        ArrayList<Soldado> vivos = new ArrayList<>();
        for (Soldado s : ejercito.getSoldados()) {
            if (s.estaVivo()) { 
                vivos.add(s);
            }
        }
        return vivos;
    }
}