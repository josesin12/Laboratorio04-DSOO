package Laboratorio_Dsoo;

public class Tablero {
    private int filas;
    private int columnas;
    private Soldado[][] tablero;

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
            System.out.println("La posición (" + s.getFila() + "," + s.getColumna() + ") está ocupada.");
            return false;
        }
    }

    public boolean moverSoldado(Soldado s, int nuevaFila, int nuevaColumna) {
        if (nuevaFila < 0 || nuevaFila >= filas || nuevaColumna < 0 || nuevaColumna >= columnas) {
            System.out.println("Movimiento fuera del tablero.");
            return false;
        }
        if (!estaVacio(nuevaFila, nuevaColumna)) {
            System.out.println("La nueva posición está ocupada.");
            return false;
        }

        tablero[s.getFila()][s.getColumna()] = null;
        s.moverA(nuevaFila, nuevaColumna);
        tablero[nuevaFila][nuevaColumna] = s;
        return true;
    }

    public boolean estaVacio(int fila, int columna) {
        return tablero[fila][columna] == null;
    }

    public void mostrarTablero() {
        System.out.println("\n=== TABLERO ===");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == null) System.out.print("[  ] ");
                else System.out.print("[" + tablero[i][j].getEjercito() + "] ");
            }
            System.out.println();
        }
    }
}

