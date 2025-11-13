package Laboratorio_Dsoo;

import java.util.Scanner;
import java.util.Random; 

public class Main {
    private static Soldado crearSoldado(String tipo, String ejercito, int id, int fila, int columna) {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Ingrese número de filas del tablero: ");
        int filas = sc.nextInt();
        System.out.print("Ingrese número de columnas del tablero: ");
        int columnas = sc.nextInt();
        sc.nextLine(); 

        Tablero tablero = new Tablero(filas, columnas);

        Ejercito ejercitoA = new Ejercito("A");
        Ejercito ejercitoB = new Ejercito("B");

        System.out.print("¿Cuántos soldados tendrá cada ejército?: ");
        int numSoldados = sc.nextInt();
        sc.nextLine();
        String[] tipos = {"Espadachin", "Arquero", "Caballero", "Lancero"};
        
        for (int i = 0; i < numSoldados; i++) {
          
            String tipoA = tipos[random.nextInt(tipos.length)];
            int fA = i; 
            int cA = 0; 

            Soldado sa = crearSoldado(tipoA, "A", i + 1, fA, cA);
            ejercitoA.agregarSoldado(sa);
            tablero.colocarSoldado(sa);

            String tipoB = tipos[random.nextInt(tipos.length)]; 
            int fB = i; 
            int cB = columnas - 1; 

            Soldado sb = crearSoldado(tipoB, "B", i + 1, fB, cB);
            ejercitoB.agregarSoldado(sb);
            tablero.colocarSoldado(sb);
        }
        System.out.println("\n--- EJÉRCITOS CREADOS ALEATORIAMENTE CON POSICIONES FIJAS ---");
        tablero.mostrarTablero();
        while (true) {
            System.out.println("\n--- TURNO DE MOVIMIENTO ---");
          
            if (obtenerSoldadosVivos(ejercitoA).isEmpty()) {
                System.out.println("\n¡BATALLA TERMINADA! Ha ganado el Ejército B por aniquilación.");
                break;
            }
            if (obtenerSoldadosVivos(ejercitoB).isEmpty()) {
                System.out.println("\n¡BATALLA TERMINADA! Ha ganado el Ejército A por aniquilación.");
                break;
            }
            System.out.println("--¿Qué soldado desea mover? (o escriba 'salir' para terminar):--"); 
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("salir")) break;

            Soldado s = ejercitoA.buscarSoldado(nombre);
            if (s == null) s = ejercitoB.buscarSoldado(nombre);  
            if (s == null || !s.estaVivo()) { 
                System.out.println("No se encontró el soldado " + nombre + " o ya no está vivo.");
                continue;
            }
            System.out.println("¿Hacia dónde desea mover al soldado? (norte||sur||este||oeste): ");
            String direccion = sc.nextLine().toLowerCase();
            System.out.println("que actitud tendra el soldado? (defensiva/ofensiva/retirada/fuga)");
            String actitud = sc.nextLine().toLowerCase();
            int nuevaFila = s.getFila();
            int nuevaColumna = s.getColumna();
            int desplazamiento = 0;
            String actitudNormalizada = actitud.toLowerCase();
            
            switch (actitudNormalizada) {
                case "ofensiva":
                case "fuga":
                    desplazamiento = 2; 
                    break;
                case "defensiva":
                    desplazamiento = 0; 
                    System.out.println("El soldado no se movió nada (Actitud Defensiva).");
                    break;
                case "retirada":
                    desplazamiento = 2; 
                    break;
                default:
                    System.out.println("No existe esa Actitud. Intente de nuevo.");
                    continue; 
            }
            if(desplazamiento > 0){
                switch (direccion) {
                    case "norte":
                        
                        if (actitudNormalizada.equals("retirada")) nuevaFila += desplazamiento; 
                        
                        else nuevaFila -= desplazamiento; 
                        break;          
                    case "sur":
                        
                        if (actitudNormalizada.equals("retirada")) nuevaFila -= desplazamiento; 
                        
                        else nuevaFila += desplazamiento; 
                        break;
                    case "este":
                        
                        if (actitudNormalizada.equals("retirada")) nuevaColumna -= desplazamiento; 
                        
                        else nuevaColumna += desplazamiento; 
                        break;
                    case "oeste":
                    
                        if (actitudNormalizada.equals("retirada")) nuevaColumna += desplazamiento; 
                        
                        else nuevaColumna -= desplazamiento; 
                        break;
                    default:
                        System.out.println("Dirección no válida. Intente de nuevo.");
                        continue; 
                }  
                tablero.moverSoldado(s, nuevaFila, nuevaColumna);
            }   
            tablero.mostrarTablero();
        }     
        System.out.println("Juego terminado.");
        sc.close();
    }
    private static java.util.ArrayList<Soldado> obtenerSoldadosVivos(Ejercito ejercito) {
        java.util.ArrayList<Soldado> vivos = new java.util.ArrayList<>();
        for (Soldado s : ejercito.getSoldados()) {
            if (s.estaVivo()) { 
                vivos.add(s);
            }
        }
        return vivos;
    }
}