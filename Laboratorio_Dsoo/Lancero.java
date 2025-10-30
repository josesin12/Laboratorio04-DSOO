package Laboratorio_Dsoo;

public class Lancero extends Soldado {

    
    private double longitudLanza;

    public Lancero(String nombre, String ejercito, int fila, int columna) {
        
        super(nombre, ejercito, fila, columna, 1, 2); 
        
        this.longitudLanza = (random.nextDouble() * 0.5) + 2.0; 
    }

  
    public void schiltrom() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está muerto y no puede formar schiltrom.");
            return;
        }

        setNivelDefensa(getNivelDefensa() + 1);
        this.actitud = "defensiva";
        this.velocidad = 0;
        System.out.println(getNombre() + " forma un ¡Schiltrom! Nivel de Defensa +1 (Actual: " + getNivelDefensa() + ").");
    }
    
  
    @Override
    public void defender() {
        schiltrom();
    }
    
    public double getLongitudLanza() {
        return longitudLanza;
    }

    @Override
    public String toString() {
        return super.toString() + " [Tipo=Lancero, Lanza=" + String.format("%.2f", longitudLanza) + "]";
    }
}