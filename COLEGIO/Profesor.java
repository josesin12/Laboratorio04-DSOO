package COLEGIO;
public class Profesor {
    // Atributos
    private int idProfesor;
    private String nombre;

    // Constructor
    public Profesor(int idProfesor, String nombre) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
    }

    // Métodos
    public void dictarCurso(Curso curso) {
        System.out.println("El profesor " + nombre + " está dictando el curso: " + curso.getTitulo());
    }

    public void verAlumnos(Curso curso) {
        System.out.println("Alumnos del curso " + curso.getTitulo() + ":");
        for (Alumno alumno : curso.getAlumnos()) {
            System.out.println("- " + alumno.getNombre());
        }
    }

    // Getters y Setters
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
