package Laboratorio_Dsoo;

import java.util.ArrayList;

public class Ejercito {
    private String nombre;
    private ArrayList<Soldado> soldados;

    public Ejercito(String nombre) {
        this.nombre = nombre;
        this.soldados = new ArrayList<>();
    }

    public void agregarSoldado(Soldado s) {
        soldados.add(s);
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public String getNombre() {
        return nombre;
    }

    public Soldado getSoldadoMasVida() {
        if (soldados.isEmpty()) return null;
        Soldado max = soldados.get(0);
        for (Soldado s : soldados) {
            if (s.getVidaActual() > max.getVidaActual()) {
                max = s;
            }
        }
        return max;
    }
    public double promedioVida() {
        if (soldados.isEmpty()) return 0;
        int suma = 0;
        for (Soldado s : soldados) {
            suma += s.getVidaActual();
        }
        return (double) suma / soldados.size();
    }
    public int poderTotal() {
        int suma = 0;
        for (Soldado s : soldados) {
            suma += s.getNivelAtaque() + s.getNivelDefensa() + s.getVidaActual();
        }
        return suma;
    }
    @Override
    public String toString() {
        return "Ejército " + nombre + " con " + soldados.size() + " soldados";
    }
}

