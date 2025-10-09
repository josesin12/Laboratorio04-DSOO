package Laboratorio_Dsoo;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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


        for (int i = 0; i < numSoldados; i++) {
            Soldado sa = new Soldado("SoldadoA" + (i + 1), "A", i, 0);
            ejercitoA.agregarSoldado(sa);
            tablero.colocarSoldado(sa);

            Soldado sb = new Soldado("SoldadoB" + (i + 1), "B", i, columnas - 1);
            ejercitoB.agregarSoldado(sb);
            tablero.colocarSoldado(sb);
        }

        tablero.mostrarTablero();


        while (true) {
            System.out.println("\n¿Qué soldado desea mover? (o escriba 'salir' para terminar): ");
            String nombre = sc.nextLine();

            if (nombre.equalsIgnoreCase("salir")) break;

            Soldado s = ejercitoA.buscarSoldado(nombre);
            if (s == null) s = ejercitoB.buscarSoldado(nombre);

            if (s == null) {
                System.out.println("No se encontró el soldado " + nombre);
                continue;
            }

            System.out.println("¿Hacia dónde desea mover al soldado? (norte/sur/este/oeste): ");
            String direccion = sc.nextLine().toLowerCase();

            int nuevaFila = s.getFila();
            int nuevaColumna = s.getColumna();

            switch (direccion) {
                case "norte":
                    nuevaFila--;
                    break;
                case "sur":
                    nuevaFila++;
                    break;
                case "este":
                    nuevaColumna++;
                    break;
                case "oeste":
                    nuevaColumna--;
                    break;
                default:
                    System.out.println("Dirección no válida. Intente de nuevo.");
                    continue;
            }

            tablero.moverSoldado(s, nuevaFila, nuevaColumna);
            tablero.mostrarTablero();
        }

        System.out.println("Juego terminado.");
        sc.close();
    }
}
