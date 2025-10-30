package Laboratorio_Dsoo;

public class Espadachin extends Soldado {

    private double longitudEspada;

    public Espadachin(String nombre, String ejercito, int fila, int columna) {
        super(nombre, ejercito, fila, columna, 3, 4); 
        this.longitudEspada = (random.nextDouble() * 0.5) + 1.0; 
    }

   
    public void crearMuroEscudos() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está muerto y no puede formar un muro.");
            return;
        }
        
        int defensaAumento = 2;
        setNivelDefensa(getNivelDefensa() + defensaAumento);
        this.actitud = "defensiva";
        this.velocidad = 0;
        System.out.println(getNombre() + " forma un ¡Muro de Escudos! Defensa temporalmente subida en " + defensaAumento + ".");
    }
    @Override
    public void defender() {
        crearMuroEscudos();
    }
    
    public double getLongitudEspada() {
        return longitudEspada;
    }

    @Override
    public String toString() {
        return super.toString() + " [Tipo=Espadachín, Espada=" + String.format("%.2f", longitudEspada) + "]";
    }
} 
    

