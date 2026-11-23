import java.time.LocalDate;
import java.util.*;

abstract class Persona {
    private final String nombre, ci;
    protected Persona(String nombre, String ci) {
        this.nombre = nombre; this.ci = ci;
    }
    public String getNombre() { return nombre; }
    public String getCi() { return ci; }
    public abstract String toString();
}

class Estudiante extends Persona {
    private final String codigo;
    private final List<Inscripcion> inscripciones = new ArrayList<>();
    public Estudiante(String nombre, String ci, String codigo) {
        super(nombre, ci); this.codigo = codigo;
    }
    public String getCodigo() { return codigo; }
    public Inscripcion inscribirse(Materia m) {
        Inscripcion i = new Inscripcion(this, m, LocalDate.now());
        inscripciones.add(i); m.registrarInscripcion(i); return i;
    }
    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }
    @Override public String toString() {
        return "Estudiante " + getNombre() + " (codigo " + codigo + ")";
    }
}

class Docente extends Persona {
    private final String especialidad;
    private final List<Materia> materias = new ArrayList<>();
    public Docente(String nombre, String ci, String especialidad) {
        super(nombre, ci); this.especialidad = especialidad;
    }
    public void dictar(Materia m) {
        materias.add(m); m.setDocente(this);
    }
    public Acta generarActa(Materia m) { return new Acta(m); }
    public List<Materia> getMaterias() {
        return Collections.unmodifiableList(materias);
    }
    @Override public String toString() {
        return "Docente " + getNombre() + " (" + especialidad + ")";
    }
}

class Universidad {
    private final String nombre;
    private final List<Carrera> carreras = new ArrayList<>();
    public Universidad(String nombre) { this.nombre = nombre; }
    public Carrera crearCarrera(String n) {
        Carrera c = new Carrera(n); carreras.add(c); return c;
    }
    public String getNombre() { return nombre; }
    public List<Carrera> getCarreras() {
        return Collections.unmodifiableList(carreras);
    }
}

class Carrera {
    private final String nombre;
    private final List<Docente> docentes = new ArrayList<>();
    Carrera(String nombre) { this.nombre = nombre; }
    public void agregarDocente(Docente d) { docentes.add(d); }
    public String getNombre() { return nombre; }
    public List<Docente> getDocentes() {
        return Collections.unmodifiableList(docentes);
    }
}

class Materia {
    private final String codigo;
    private final int creditos;
    private Docente docente;
    private final List<Horario> horarios = new ArrayList<>();
    private final List<Inscripcion> inscripciones = new ArrayList<>();
    public Materia(String codigo, int creditos) {
        this.codigo = codigo; this.creditos = creditos;
    }
    public Horario agregarHorario(LocalDate fecha, String aula) {
        Horario h = new Horario(fecha, aula); horarios.add(h); return h;
    }
    void registrarInscripcion(Inscripcion i) { inscripciones.add(i); }
    void setDocente(Docente d) { docente = d; }
    public String getCodigo() { return codigo; }
    public int getCreditos() { return creditos; }
    public Docente getDocente() { return docente; }
    public List<Horario> getHorarios() {
        return Collections.unmodifiableList(horarios);
    }
    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }
}

class Horario {
    private final LocalDate fecha;
    private final String aula;
    Horario(LocalDate fecha, String aula) {
        this.fecha = fecha; this.aula = aula;
    }
    public LocalDate getFecha() { return fecha; }
    public String getAula() { return aula; }
}

interface Calificable { double calcularNota(); }

class Inscripcion implements Calificable {
    private final Estudiante estudiante;
    private final Materia materia;
    private final LocalDate fecha;
    private double nota;
    Inscripcion(Estudiante e, Materia m, LocalDate f) {
        estudiante = e; materia = m; fecha = f;
    }
    public void registrarNota(double n) {
        if (n < 0 || n > 100)
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
        nota = n;
    }
    @Override public double calcularNota() { return nota; }
    public Estudiante getEstudiante() { return estudiante; }
    public Materia getMateria() { return materia; }
    public LocalDate getFecha() { return fecha; }
}

class Acta {
    private final LocalDate fecha = LocalDate.now();
    private final String contenido;
    Acta(Materia m) {
        StringBuilder sb = new StringBuilder();
        sb.append("ACTA ").append(m.getCodigo()).append(" - ").append(fecha).append('\n');
        for (Inscripcion i : m.getInscripciones())
            sb.append(i.getEstudiante().getNombre()).append(" : ")
              .append(i.calcularNota()).append('\n');
        contenido = sb.toString();
    }
    @Override public String toString() { return contenido; }
}

public class SistemaAcademico {
    public static void main(String[] args) {
        Universidad u = new Universidad("UMSA");
        Carrera c = u.crearCarrera("Informatica");
        Docente d = new Docente("Ana Alba", "1234567", "Ingenieria de Software");
        c.agregarDocente(d);

        Materia m = new Materia("INF-121", 6);
        d.dictar(m);
        m.agregarHorario(LocalDate.of(2026, 7, 2), "Aula 101");
        m.agregarHorario(LocalDate.of(2026, 7, 9), "Aula 101");

        Estudiante e1 = new Estudiante("Carlos Flores", "7654321", "E-001");
        Estudiante e2 = new Estudiante("Maria Salas", "7654322", "E-002");
        Inscripcion i1 = e1.inscribirse(m);
        Inscripcion i2 = e2.inscribirse(m);
        i1.registrarNota(85);
        i2.registrarNota(92);

        List<Persona> personas = Arrays.asList(d, e1, e2);
        personas.forEach(System.out::println);
        Calificable cal = i1;
        System.out.println("Nota de " + e1.getNombre() + ": " + cal.calcularNota());

        Acta acta = d.generarActa(m);
        System.out.println(acta);
    }
}
