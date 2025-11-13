package Laboratorio_Dsoo;
import java.util.Random;

public class Soldado {
    Random random = new Random();

    private String nombre;
    private int nivelAtaque;
    private int nivelDefensa;
    private int nivelVida;
    private int vidaActual;
    private int fila;
    private int columna;
    private String ejercito; 
    private boolean vive;
    protected String actitud;
    protected int velocidad;

    public Soldado(String nombre, String ejercito, int fila, int columna) {
        this.nombre = nombre;
        this.ejercito = ejercito;
        this.nivelAtaque = random.nextInt(5) + 1;  
        this.nivelDefensa = random.nextInt(5) + 1; 
        this.nivelVida = random.nextInt(5) + 1;    
        this.vidaActual = nivelVida;
        this.fila = fila;
        this.columna = columna;
        this.vive = true;
        this.actitud = "defensiva";
        this.velocidad = 0;
    }
    public Soldado(String nombre, String ejercito, int fila, int columna, int minVida, int maxVida) {
        this.nombre = nombre;
        this.ejercito = ejercito;
        this.nivelAtaque = random.nextInt(5) + 1;
        this.nivelDefensa = random.nextInt(5) + 1;
        this.nivelVida = random.nextInt(maxVida - minVida + 1) + minVida;
        this.vidaActual = nivelVida;
        this.fila = fila;
        this.columna = columna;
        this.vive = true;
        this.actitud = "defensiva";
        this.velocidad = 0;
    }
    public boolean estaVivo() {
        return this.vive;
    }
    public void morir() {
        this.vive = false;
        this.vidaActual = 0;
        this.velocidad = 0;
        this.actitud = "muerto";
    }
    public void atacar() {
        if (estaVivo()) {
            this.actitud = "ofensiva";
            avanzar();
        } else {
            System.out.println(nombre + " está muerto y no puede atacar.");
        }
    }
    public void defender() {
        if (estaVivo()) {
            this.actitud = "defensiva";
            this.velocidad = 0;
        } else {
            System.out.println(nombre + " está muerto y no puede defender.");
        }
    }
    public void avanzar() {
        if (estaVivo()) {
            this.velocidad++;
        } else {
            System.out.println(nombre + " está muerto y no puede avanzar.");
        }
    }
    public void retroceder() {
        if (estaVivo()) {
            if (velocidad > 0) {
                this.velocidad--;
            } else {
                this.velocidad = -1; 
            }
            this.actitud = "retirada";
        } else {
            System.out.println(nombre + " está muerto y no puede retroceder.");
        }
    }
    public void huir() {
        if (estaVivo()) {
            this.actitud = "fuga";
            this.velocidad += 2;
        } else {
            System.out.println(nombre + " está muerto y no puede huir.");
        }
    }
    public void serAtacado(int daño) {
        if (! estaVivo()) return;

        int dañoReal = Math.max(0, daño - this.nivelDefensa);
        this.vidaActual -= dañoReal;
        
        if (vidaActual <= 0) {
            morir();
        }
    }
    public void moverA(int nuevaFila, int nuevaColumna) {
    this.fila = nuevaFila;
    this.columna = nuevaColumna;
}

    public int getVidaActual() {
         return vidaActual; 
        }
    public void setVidaActual(int vidaActual) {
     this.vidaActual = vidaActual;
}   
    public int getNivelVida() {
         return nivelVida; 
        }
    public void setNivelVida(int nivelVida ) {
         this.nivelVida = nivelVida;
    }
    public int getNivelAtaque() { 
        return nivelAtaque; 
    }
    public int getNivelDefensa() { 
        return nivelDefensa; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getEjercito() { 
        return ejercito; 
    }
    public int getFila() { 
        return fila; 
    }
    public int getColumna() { 
        return columna; 
    }
    public String getActitud() { 
        return actitud; 
    }
    public int getVelocidad() { 
        return velocidad; 
    }

    public void setNivelDefensa(int nivelDefensa) { 
     this.nivelDefensa = nivelDefensa;
}


    @Override
    public String toString() {
        return nombre + " [Ejército=" + ejercito + ", Vida=" + vidaActual +", Atq=" + nivelAtaque +", Def="
         + nivelDefensa +", Actitud=" + actitud +", Vel=" + velocidad +", Pos=(" + fila + "," + columna + ")]";
    }
}






