package COLEGIO;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    // Atributos
    private int idCurso;
    private String titulo;
    private Profesor profesor;               // Un curso tiene un profesor
    private List<Alumno> alumnos;            // Un curso puede tener muchos alumnos

    // Constructor
    public Curso(int idCurso, String titulo) {
        this.idCurso = idCurso;
        this.titulo = titulo;
        this.alumnos = new ArrayList<>();
    }

    // Métodos
    public void asignarProfesor(Profesor profesor) {
        this.profesor = profesor;
        System.out.println("Profesor " + profesor.getNombre() + " asignado al curso: " + titulo);
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
        System.out.println("Alumno " + alumno.getNombre() + " agregado al curso: " + titulo);
    }

    // Getters y Setters
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }
}
