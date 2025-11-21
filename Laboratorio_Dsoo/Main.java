package Laboratorio_Dsoo;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
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
        Juego juego = new Juego(mapa);
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Juego de Estrategia - Turno 1");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            PanelTablero panelTablero = new PanelTablero(juego);
            frame.add(panelTablero, BorderLayout.CENTER);

            frame.setJMenuBar(crearMenu(frame, juego)); 
            
            frame.pack();
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
            
            FileHandler.log("Juego iniciado. Tablero: " + filas + "x" + columnas);
        });
        
        sc.close();
    }
    
    private static String generarRankingData(Juego juego) {
        int puntosA = 0;
        for (Soldado s : juego.getEjercitoA().getSoldados()) {
            if (s.estaVivo()) {
                puntosA += s.getVidaActual();
            }
        }
        
        int puntosB = 0;
        for (Soldado s : juego.getEjercitoB().getSoldados()) {
            if (s.estaVivo()) {
                puntosB += s.getVidaActual();
            }
        }

        String ranking = "--- RANKING DE VIDA RESTANTE ---\n";
        ranking += juego.getEjercitoA().getNombre() + ": " + puntosA + " puntos de vida\n";
        ranking += juego.getEjercitoB().getNombre() + ": " + puntosB + " puntos de vida\n";
        return ranking;
    }

    private static JMenuBar crearMenu(JFrame frame, Juego juego) {
        JMenuBar menuBar = new JMenuBar();
        
        // --- MENÚ ARCHIVO ---
        JMenu menuArchivo = new JMenu("Archivo");
        
        JMenuItem itemAbrirLogs = new JMenuItem("Abrir logs");
        itemAbrirLogs.addActionListener(e -> new Archivo("logs.txt", "Logs del Juego"));
        
        JMenuItem itemGuardarLogs = new JMenuItem("Guardar logs");
        itemGuardarLogs.addActionListener(e -> {
            FileHandler.log("Guardado de logs iniciado por el usuario.");
            JOptionPane.showMessageDialog(frame, "Logs guardados en 'logs.txt'.");
        });
        
        JMenuItem itemAbrirRanking = new JMenuItem("Abrir Ranking");
        itemAbrirRanking.addActionListener(e -> new Archivo("ranking.txt", "Ranking de Jugadores"));
        
        JMenuItem itemGuardarRanking = new JMenuItem("Guardar Ranking");
        itemGuardarRanking.addActionListener(e -> {
            FileHandler.escribirLinea("ranking.txt", generarRankingData(juego), false); 
            JOptionPane.showMessageDialog(frame, "Ranking guardado en 'ranking.txt'.");
        });

        // Montaje del Menú Archivo
        menuArchivo.add(new JMenuItem("Nuevo")); 
        menuArchivo.addSeparator();
        menuArchivo.add(itemAbrirLogs);
        menuArchivo.add(itemGuardarLogs);
        menuArchivo.addSeparator();
        menuArchivo.add(itemAbrirRanking);
        menuArchivo.add(itemGuardarRanking);
        menuArchivo.addSeparator();
        
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);
        
        menuBar.add(menuArchivo);
        
        // --- MENÚ AYUDA ---
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Sobre el juego");
        itemAcercaDe.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, 
                "Juego de Estrategia.\nDesarrollado en Java Swing para la Práctica 12.", 
                "Sobre el Juego", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        menuAyuda.add(itemAcercaDe);
        menuBar.add(menuAyuda);
        
        return menuBar;
    }
}