package practica;
import java.util.*;
public class Tablero {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
        
    System.out.println("Ingrese el tamaño de filas del tablero:");
    int fila = scanner.nextInt();
    System.out.println("Ingrese el tamaño de la columna:");
    int columna = scanner.nextInt();  
    String[][] tablero = new String[fila][columna];
    for( int i =0; i<= fila; i++){
        for( int x =0; x<= columna; x++){
            System.out.println("hola");
        }
        System.out.println("NO");
    }
    }
}
