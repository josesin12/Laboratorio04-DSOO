package Laboratorio_Dsoo;
import java.util.*;
public class Caballero extends Soldado{
    private String armaActual;
    private boolean estaMontando;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    public Caballero(String nombre, int fila, int columna, String ejercito){
        super(nombre, ejercito, fila, columna, 3, 5);
        this.armaActual = "lanza";
        this.estaMontando = false;
    }
    public void alternarArma() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " no puede alternar su arma, está muerto.");
            return;
        }

        if (this.armaActual.equalsIgnoreCase("lanza")) {
            this.armaActual = "espada";
            System.out.println(getNombre() + " cambia su arma a espada.");
        } else if (this.armaActual.equalsIgnoreCase("espada")) {
            this.armaActual = "lanza";
            System.out.println(getNombre() + " cambia su arma a lanza.");
        }
        
    }
    public void cambiarArmaA(String nuevaArma) {
         if (!estaVivo()) return;
         this.armaActual = nuevaArma;
    }
    public void desmontar() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está muerto y no puede desmontar.");
            return;
        }
        if (!this.estaMontando) {
            System.out.println(getNombre() + " no puede desmontar, ya está a pie.");
            return;
        }
        
        this.estaMontando = false;
        defender();
        cambiarArmaA("espada"); 
        System.out.println(getNombre() + " desmonta. Ahora está en actitud DEFENSIVA con espada.");
    }
    public void montar() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está muerto y no puede montar.");
            return;
        }
        if (this.estaMontando) {
            System.out.println(getNombre() + " ya está montando.");
            return;
        }
        
        this.estaMontando = true;
        cambiarArmaA("lanza"); 
        this.actitud = "ofensiva"; 
        this.velocidad = 2; 
        System.out.println(getNombre() + " ¡MONTA! Cambia a lanza y se prepara para invertir.");
        investir(); 
    }
    public void investir(){
        if (! estaVivo()){
            System.out.println(getNombre()+ "no puede investir, esta muerto");
            return;
        }
        int veces;
        if(estaMontando){
            veces = 3;
            System.out.println(getNombre()+"embiste montando (x" + veces + " ataques)");
        }
        else{
            veces = 2;
            System.out.println(getNombre()+" embiste desmontado (x" + veces + " ataques).");
        }

    }
    public String getArmaActual() {
        return armaActual;
    }
    
    public boolean isEstaMontando() {
        return estaMontando;
    }

    @Override
    public String toString() {
        String estadoMontaje = estaMontando ? "Montado" : "A pie";
        return super.toString() + " [Tipo=Caballero, Arma=" + armaActual + ", Estado=" + estadoMontaje + "]";
    }
    }
    

