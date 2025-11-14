// Archivo: Mapa.java
package Laboratorio_Dsoo;

import java.util.Random;

public class Mapa {
    private Tablero tablero;
    private String tipoTerritorio; // bosque, campo abierto, montaña, desierto, playa
    private Ejercito ejercitoA;
    private Ejercito ejercitoB;
    private final String[] TIPOS_TERRITORIO = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
    

    private final String REINO_A = "Inglaterra"; 
    private final String REINO_B = "Francia";   
    
    private Random random = new Random();

    public Mapa(int filas, int columnas) {
        
        this.tablero = new Tablero(filas, columnas);
        this.tipoTerritorio = TIPOS_TERRITORIO[random.nextInt(TIPOS_TERRITORIO.length)];
        
        System.out.println("\n--- MAPA CREADO ---");
        System.out.println("Territorio: '" + this.tipoTerritorio + "'.");
    }

    
    public void generarEjercitos(int numSoldadosA, int numSoldadosB) {
        this.ejercitoA = new Ejercito(REINO_A);
        this.ejercitoB = new Ejercito(REINO_B);
        
        String[] tipos = {"Espadachin", "Arquero", "Caballero", "Lancero"};
        for (int i = 0; i < numSoldadosA; i++) {
            String tipo = tipos[random.nextInt(tipos.length)];
            
            int fA, cA;
            do {
                fA = random.nextInt(tablero.getFilas()); 
                cA = random.nextInt(tablero.getColumnas()); 
            } while (!tablero.estaVacio(fA, cA));

            Soldado sa = crearSoldado(tipo, REINO_A, i + 1, fA, cA);
            
            
            aplicarBonusVida(sa, REINO_A);
            tablero.colocarSoldado(sa); 
            ejercitoA.agregarSoldado(sa);
        }

        
        for (int i = 0; i < numSoldadosB; i++) {
            String tipo = tipos[random.nextInt(tipos.length)];
            
            int fB, cB;
            do {
                fB = random.nextInt(tablero.getFilas()); 
                cB = random.nextInt(tablero.getColumnas()); 
            } while (!tablero.estaVacio(fB, cB));

            Soldado sb = crearSoldado(tipo, REINO_B, i + 1, fB, cB);
            
            aplicarBonusVida(sb, REINO_B);
            tablero.colocarSoldado(sb);
            ejercitoB.agregarSoldado(sb);
        }
        System.out.println("Ejércitos generados: " + REINO_A + " (" + numSoldadosA + " soldados) vs " + REINO_B + " (" + numSoldadosB + " soldados).");
    }
    
    private Soldado crearSoldado(String tipo, String ejercito, int id, int fila, int columna) {
        String nombre = tipo + ejercito + id;
        switch (tipo) {
            case "Espadachin":
                return new Espadachin(nombre, ejercito, fila, columna);
            case "Arquero":
                return new Arquero(nombre, ejercito, fila, columna);
            case "Caballero":
               
                return new Caballero(nombre, fila, columna, ejercito); 
            case "Lancero":
                return new Lancero(nombre, ejercito, fila, columna);
            default:
                return new Soldado(nombre, ejercito, fila, columna); 
        }
    }

    private void aplicarBonusVida(Soldado s, String reino) {
        String bonusTerritorio = obtenerBonusTerritorio(reino);
        
        if (this.tipoTerritorio.equals(bonusTerritorio)) {
            s.setNivelVida(s.getNivelVida() + 1);
            s.setVidaActual(s.getVidaActual() + 1);
            System.out.println("-> Bonus de Territorio aplicado a " + s.getNombre() + 
                               " (+1 Vida por " + this.tipoTerritorio + ")");
        }
    }
    private String obtenerBonusTerritorio(String reino) {
        return switch (reino) {
            case "Inglaterra" -> "bosque";
            case "Francia" -> "campo abierto";
            case "Castilla-Aragón" -> "montaña";
            case "Moros" -> "desierto";
            default -> "playa"; 
        };
    }
    public Tablero getTablero() {
        return tablero;
    }

    public Ejercito getEjercitoA() {
        return ejercitoA;
    }

    public Ejercito getEjercitoB() {
        return ejercitoB;
    }
    
    public String getTipoTerritorio() {
        return tipoTerritorio;
    }
}