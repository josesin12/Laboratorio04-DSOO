package Laboratorio_Dsoo;

public class Arquero extends Soldado {

    private int numeroFlechas;

    public Arquero(String nombre, String ejercito, int fila, int columna) {
        super(nombre, ejercito, fila, columna, 1, 3); 
        this.numeroFlechas = 20; 
    }

    public boolean disparar() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está muerto y no puede disparar");
            return false;
        }
        if (numeroFlechas > 0) {
            numeroFlechas--;
            this.actitud = "ofensiva";
            System.out.println(getNombre() + " dispara una flecha. Quedan " + numeroFlechas + " flechas");
            
            return true;
        } else {
            System.out.println(getNombre() + " no tiene flechas. Debe acercarse o defender");
            this.actitud = "defensiva";
            return false;
        }
    }
    @Override
    public void atacar() {
        disparar();
    }
    
    public int getNumeroFlechas() {
        return numeroFlechas;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Tipo=Arquero, Flechas=" + numeroFlechas + "]";
    }
}
