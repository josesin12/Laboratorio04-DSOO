
package Laboratorio_Dsoo;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.ArrayList; 

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
        
        // 1. Crear el Mapa
        Mapa mapa = new Mapa(filas, columnas);
        
        // 2. Generar los ejércitos
        mapa.generarEjercitos(numSoldadosA, numSoldadosB);
        
        System.out.println("\n--- INICIALIZACIÓN DE LA GUI ---");
        
        // 3. INICIALIZACIÓN DE LA GUI (Reemplaza al bucle while(true) de la consola)
        Juego juego = new Juego(mapa);
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Juego de Estrategia - Turno: " + juego.getTurno() + " (" + juego.getEjercitoActual().getNombre() + ")");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            PanelTablero panelTablero = new PanelTablero(juego);
            frame.add(panelTablero, BorderLayout.CENTER);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    
        sc.close();
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