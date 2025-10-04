package Laboratorio_Dsoo;

public class Persona {
    private String nombre;
    private int id;
    private int edad;

public Persona() {
    this.nombre = "sin Nombre";
    this.id = 0;
    this.edad= 0;
    }
public Persona(String nombre, int id, int edad) {
    this.nombre = nombre;
    this.id = id;
    this.edad = edad;
    }
public Persona(String nombre) {
    this.nombre = nombre;
    }
public String getNombre() {
    return nombre;
    }
public void setNombre(String nombre) {
    this.nombre = nombre;
    }
public int getEdad() {
    return edad;
    }
public void setEdad(int edad) {
    if(edad >= 0){
        this.edad= edad;
    }
}
public boolean esMayorDeEdad(){
   return this.edad >=18;
   }
public String toString(){
   return "Persona[ nombre=" + nombre + ", id=" + id + ", edad=" + edad + "]";
   }
}

