package Laboratorio_Dsoo;


public class Main {
    public static void main(String[] args) {
        
        Ejercito ejercitoA = new Ejercito("A");
        Ejercito ejercitoB = new Ejercito("B");

    
        Soldado a1 = new Soldado("SoldadoA1", "A", 0, 0);
        Soldado a2 = new Soldado("SoldadoA2", "A", 1, 1);
        ejercitoA.agregarSoldado(a1);
        ejercitoA.agregarSoldado(a2);

        Soldado b1 = new Soldado("SoldadoB1", "B", 2, 2);
        Soldado b2 = new Soldado("SoldadoB2", "B", 3, 3);
        ejercitoB.agregarSoldado(b1);
        ejercitoB.agregarSoldado(b2);

        System.out.println(ejercitoA);
        System.out.println("Soldado con más vida: " + ejercitoA.getSoldadoMasVida());
        System.out.println("Promedio de vida: " + ejercitoA.promedioVida());
        System.out.println("Poder total: " + ejercitoA.poderTotal());

        System.out.println();
        System.out.println(ejercitoB);
        System.out.println("Soldado con más vida: " + ejercitoB.getSoldadoMasVida());
        System.out.println("Promedio de vida: " + ejercitoB.promedioVida());
        System.out.println("Poder total: " + ejercitoB.poderTotal());
    }
}

     
     


    