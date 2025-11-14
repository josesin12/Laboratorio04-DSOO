// Archivo: Juego.java

// Archivo: Juego.java

package Laboratorio_Dsoo;

import java.util.ArrayList;

public class Juego {
    private Mapa mapa;
    private Tablero tablero;
    private Ejercito ejercitoA;
    private Ejercito ejercitoB;
    private int turno;
    private Ejercito ejercitoActual;

    public Juego(Mapa mapa) {
        this.mapa = mapa;
        this.tablero = mapa.getTablero();
        this.ejercitoA = mapa.getEjercitoA();
        this.ejercitoB = mapa.getEjercitoB();
        this.turno = 1;
        this.ejercitoActual = ejercitoA; // Empieza el Ejército A (Inglaterra)
    }

    /** Cambia el turno al siguiente ejército. */
    public void cambiarTurno() {
        if (ejercitoActual == ejercitoA) {
            ejercitoActual = ejercitoB;
        } else {
            ejercitoActual = ejercitoA;
            turno++; // Aumenta el turno solo cuando el Ejército B finaliza
        }
        System.out.println("\n--- TURNO " + turno + " - Ejército Actual: " + ejercitoActual.getNombre() + " ---");
    }

    /** Mueve el soldado y maneja la lógica de enfrentamiento en el Tablero. */
    public boolean moverSoldado(Soldado s, int nuevaFila, int nuevaColumna) {
        // La lógica de movimiento y combate ya está en Tablero.moverSoldado
        boolean movido = tablero.moverSoldado(s, nuevaFila, nuevaColumna);
        return movido;
    }
    
    // Método para obtener el soldado en una posición específica (útil para la GUI)
    public Soldado getSoldadoEnPosicion(int fila, int columna) {
        return tablero.getSoldado(fila, columna);
    }
    
    // Métodos de estado de juego
    public boolean hayGanador() {
        long vivosA = ejercitoA.getSoldados().stream().filter(Soldado::estaVivo).count();
        long vivosB = ejercitoB.getSoldados().stream().filter(Soldado::estaVivo).count();
        
        return vivosA == 0 || vivosB == 0;
    }
    
    public String getGanador() {
        long vivosA = ejercitoA.getSoldados().stream().filter(Soldado::estaVivo).count();
        
        if (vivosA == 0) return ejercitoB.getNombre();
        if (vivosA > 0 && ejercitoB.getSoldados().stream().filter(Soldado::estaVivo).count() == 0) return ejercitoA.getNombre();
        return null;
    }
    
    // Getters necesarios
    public Tablero getTablero() {
        return tablero;
    }

    public Ejercito getEjercitoA() {
        return ejercitoA;
    }

    public Ejercito getEjercitoB() {
        return ejercitoB;
    }

    public int getTurno() {
        return turno;
    }

    public Ejercito getEjercitoActual() {
        return ejercitoActual;
    }
}