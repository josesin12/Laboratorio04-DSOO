package Laboratorio_Dsoo;

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
        // Constructor COMPLETO: Inicializa el primer ejército
        this.ejercitoActual = ejercitoA; 
    }

    public void cambiarTurno() {
        if (ejercitoActual == ejercitoA) {
            ejercitoActual = ejercitoB;
        } else {
            ejercitoActual = ejercitoA;
            turno++; 
        }
        System.out.println("\n--- TURNO " + turno + " - Ejército Actual: " + ejercitoActual.getNombre() + "-------");
    }

    public boolean moverSoldado(Soldado s, int nuevaFila, int nuevaColumna) {
        // Asumiendo que Tablero.moverSoldado maneja el ataque/movimiento
        boolean movido = tablero.moverSoldado(s, nuevaFila, nuevaColumna);
        return movido;
    }
    
    
    public Soldado getSoldadoEnPosicion(int fila, int columna) {
        return tablero.getSoldado(fila, columna);
    }
    
    public boolean hayGanador() {
        long vivosA = ejercitoA.getSoldados().stream().filter(Soldado::estaVivo).count();
        long vivosB = ejercitoB.getSoldados().stream().filter(Soldado::estaVivo).count();
        
        return vivosA == 0 || vivosB == 0;
    }
    
    public String getGanador() {
        long vivosA = ejercitoA.getSoldados().stream().filter(Soldado::estaVivo).count();
        long vivosB = ejercitoB.getSoldados().stream().filter(Soldado::estaVivo).count();
        
        if (vivosA == 0 && vivosB > 0) return ejercitoB.getNombre();
        if (vivosA > 0 && vivosB == 0) return ejercitoA.getNombre();
        return null;
    }
    
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Método getMapa (único): Necesario para que PanelTablero obtenga el tipo de territorio.
     */
    public Mapa getMapa() {
        return mapa;
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