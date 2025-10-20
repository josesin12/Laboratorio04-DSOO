package Laboratorio_Dsoo;
import java.util.*;
public class Tablero {
    private int filas;
    private int columnas;
    private Soldado[][] tablero;
    private Random random = new Random();


    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        tablero = new Soldado[filas][columnas];
    }

    public boolean colocarSoldado(Soldado s) {
        if (estaVacio(s.getFila(), s.getColumna())) {
            tablero[s.getFila()][s.getColumna()] = s;
            return true;
        } else {
            System.out.println("La posición (" + s.getFila() + "," + s.getColumna() + ") está ocupada, se produce un enfrentamiento");
            return true;
        }
    }

    public boolean moverSoldado(Soldado s, int nuevaFila, int nuevaColumna) {
        if (nuevaFila < 0 || nuevaFila >= filas || nuevaColumna < 0 || nuevaColumna >= columnas) {
            System.out.println("Movimiento fuera del tablero");
            return false;
        }

        if (!estaVacio(nuevaFila, nuevaColumna)) {
            Soldado defensor = tablero[nuevaFila][nuevaColumna];
            
            if (!s.getEjercito().equals(defensor.getEjercito())) {
                System.out.println("La nueva posición está ocupada por un enemigo, ¡Se produce un :ENFRENTAMIENTO!");
                
                resolverEnfrentamientoProbabilistico(s, defensor); 
                
                return true;
            } else {
                System.out.println("La nueva posición está ocupada por un aliado, Movimiento cancelado");
                return false;
            }
        }

        tablero[s.getFila()][s.getColumna()] = null;
        s.moverA(nuevaFila, nuevaColumna);
        tablero[nuevaFila][nuevaColumna] = s;
        return true;
    }

    private void resolverEnfrentamientoProbabilistico(Soldado atacante, Soldado defensor) {
        Soldado ganador, perdedor;
        int vidaTotal = atacante.getVidaActual() + defensor.getVidaActual();
        
        if (vidaTotal == 0) {
            System.out.println("Error de combate: Vida total es cero, Nadie se mueve");
            return;
        }

        double probAtacante = (double) atacante.getVidaActual() / vidaTotal;
        double probDefensor = (double) defensor.getVidaActual() / vidaTotal;
        
        System.out.printf("  [Vida Atacante (%s): %d, Vida Defensor (%s): %d]. Probabilidad de victoria: %s: %.1f%%, %s: %.1f%%\n",
                          atacante.getNombre(), atacante.getVidaActual(), defensor.getNombre(), defensor.getVidaActual(),
                          atacante.getNombre(), probAtacante * 100,
                          defensor.getNombre(), probDefensor * 100);

        double resultadoDado = random.nextDouble();

        if (resultadoDado < probAtacante) {
            ganador = atacante;
            perdedor = defensor;
            System.out.println(" ¡El ganador es " + ganador.getNombre() + "!");
        } else {
            ganador = defensor;
            perdedor = atacante;
            System.out.println(" ¡El ganador es " + ganador.getNombre() + "!");
        }
        tablero[atacante.getFila()][atacante.getColumna()] = null;
        perdedor.morir();
        tablero[perdedor.getFila()][perdedor.getColumna()] = null; 
        ganador.setVidaActual(ganador.getVidaActual() + 1); 
        ganador.moverA(defensor.getFila(), defensor.getColumna());
        tablero[ganador.getFila()][ganador.getColumna()] = ganador; 
        System.out.println("  Ganador " + ganador.getNombre() + " gana +1 vida (Vida actual: " + ganador.getVidaActual() + ") Ocupa la posición (" + ganador.getFila() + "," + ganador.getColumna() + ").");
        System.out.println("  Perdedor " + perdedor.getNombre() + " ha muerto y desaparece del tablero");
    }

    public boolean estaVacio(int fila, int columna) {
        return tablero[fila][columna] == null;
    }

    public void mostrarTablero() {
        System.out.println("------------TABLERO--------------");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == null) System.out.print("[  ] ");
                else System.out.print("[" + tablero[i][j].getEjercito() + "] ");
            }
            System.out.println();
        }
    }
    
    public void moverSoldado(){
        System.out.println();
    }
}
