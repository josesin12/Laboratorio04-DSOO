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

    public Soldado buscarSoldado(String nombre) {
        for (Soldado s : soldados) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }
}
