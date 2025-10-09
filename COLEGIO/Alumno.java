package COLEGIO;
import java.util.ArrayList;
import java.util.List;

public class Alumno {

    private int idAlumno;
    private String nombre;
    private List<Curso> Cursos;


public Alumno( int idAlumno, String nombre){
    this.idAlumno =idAlumno;
    this.nombre =nombre;
    this.Cursos = new ArrayList<>(); 
}
public void inscribirse(Curso curso){
    Cursos.add(curso);
    curso.agregarAlumno(this);
    System.out.println("El alumno " + nombre + " se inscribió en el curso: " + curso.getTitulo());
}
public void verCursos() {
        System.out.println("Cursos del alumno " + nombre + ":");
        for (Curso curso : Cursos) {
            System.out.println("- " + curso.getTitulo());}
}
 public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Curso> getCursos() {
        return Cursos;
    }  
}
      