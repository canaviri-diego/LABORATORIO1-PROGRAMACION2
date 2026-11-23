import java.time.LocalDate;
import java.util.*;

abstract class Persona {
    private final String nombre, ci;
    protected Persona(String n, String ci) { nombre=n; this.ci=ci; }
    public String getNombre() { return nombre; }
    public String getCi() { return ci; }
    public abstract String toString();
}
class Estudiante extends Persona {
    private final String codigo;
    private final List<Inscripcion> inscripciones = new ArrayList<>();
    public Estudiante(String n,String ci,String cod){super(n,ci);codigo=cod;}
    public Inscripcion inscribirse(Materia m) {
        for (Inscripcion i: inscripciones)
            if (i.getMateria()==m)
                throw new IllegalStateException("El estudiante ya esta inscrito");
        Inscripcion i=new Inscripcion(this,m,LocalDate.now());
        inscripciones.add(i); m.registrarInscripcion(i); return i;
    }
    public List<Inscripcion> getInscripciones(){return Collections.unmodifiableList(inscripciones);}
    @Override public String toString(){return "Estudiante "+getNombre()+" ("+codigo+")";}
}
class Docente extends Persona {
    private final String especialidad;
    private final List<Materia> materias=new ArrayList<>();
    private final List<Acta> actas=new ArrayList<>();
    public Docente(String n,String ci,String esp){super(n,ci);especialidad=esp;}
    public void dictar(Materia m){
        if(m.getDocente()!=null && m.getDocente()!=this)
            throw new IllegalStateException("La materia ya tiene otro docente");
        if(!materias.contains(m)){materias.add(m);m.setDocente(this);}
    }
    public Acta generarActa(Materia m){Acta a=new Acta(m);actas.add(a);return a;}
    public List<Materia> getMaterias(){return Collections.unmodifiableList(materias);}
    @Override public String toString(){return "Docente "+getNombre()+" ("+especialidad+")";}
}
class Universidad {
    private final String nombre;
    private final List<Carrera> carreras=new ArrayList<>();
    public Universidad(String n){nombre=n;}
    public Carrera crearCarrera(String n){Carrera c=new Carrera(n);carreras.add(c);return c;}
    public String getNombre(){return nombre;}
}
class Carrera {
    private final String nombre;
    private final List<Docente> docentes=new ArrayList<>();
    Carrera(String n){nombre=n;}
    public void agregarDocente(Docente d){
        if(!docentes.contains(d)) docentes.add(d);
    }
    public String getNombre(){return nombre;}
    public List<Docente> getDocentes(){return Collections.unmodifiableList(docentes);}
}
class Materia {
    private final String codigo; private final int creditos;
    private Docente docente;
    private final List<Horario> horarios=new ArrayList<>();
    private final List<Inscripcion> inscripciones=new ArrayList<>();
    public Materia(String c,int cr){codigo=c;creditos=cr;}
    public Horario agregarHorario(LocalDate f,String a){Horario h=new Horario(f,a);horarios.add(h);return h;}
    public static class Horario {
        private final LocalDate fecha; private final String aula;
        private Horario(LocalDate f,String a){fecha=f;aula=a;}
        public LocalDate getFecha(){return fecha;}
        public String getAula(){return aula;}
    }
    void registrarInscripcion(Inscripcion i){inscripciones.add(i);}
    void setDocente(Docente d){
        if(docente!=null && docente!=d) throw new IllegalStateException("La materia ya tiene docente");
        docente=d;
    }
    public String getCodigo(){return codigo;}
    public Docente getDocente(){return docente;}
    public List<Inscripcion> getInscripciones(){return Collections.unmodifiableList(inscripciones);}
}
interface Calificable { double calcularNota(); }
class Inscripcion implements Calificable {
    private final Estudiante estudiante; private final Materia materia;
    private final LocalDate fecha; private Double nota=null;
    Inscripcion(Estudiante e,Materia m,LocalDate f){estudiante=e;materia=m;fecha=f;}
    public void registrarNota(double n){
        if(n<0 || n>100) throw new IllegalArgumentException("Nota fuera del rango 0 a 100");
        nota=n;
    }
    @Override public double calcularNota(){
        if(nota==null) throw new IllegalStateException("La nota aun no fue registrada");
        return nota;
    }
    public Estudiante getEstudiante(){return estudiante;}
    public Materia getMateria(){return materia;}
    public LocalDate getFecha(){return fecha;}
}
class Acta {
    private final String contenido;
    Acta(Materia m){
        StringBuilder s=new StringBuilder("ACTA "+m.getCodigo()+" - "+LocalDate.now()+"\n");
        for(Inscripcion i:m.getInscripciones())
            s.append(i.getEstudiante().getNombre()).append(" : ").append(i.calcularNota()).append('\n');
        contenido=s.toString();
    }
    @Override public String toString(){return contenido;}
}
public class SistemaAcademicoCorregido {
    public static void main(String[] args) {
        Universidad u=new Universidad("UMSA");
        Carrera c1=u.crearCarrera("Informatica");
        Carrera c2=u.crearCarrera("Electronica");
        Docente d1=new Docente("Ana Alba","1234567","Software");
        Docente d2=new Docente("Luis Rojas","2222222","Redes");
        c1.agregarDocente(d1); c2.agregarDocente(d1); // Agregacion: puede pertenecer a ambas.
        Materia m=new Materia("INF-121",6);
        d1.dictar(m);
        // d2.dictar(m); // Produce excepcion: ya existe docente asignado.
        Estudiante e=new Estudiante("Carlos Flores","7654321","E-001");
        Inscripcion i=e.inscribirse(m);
        // e.inscribirse(m); // Produce excepcion: inscripcion duplicada.
        try { i.calcularNota(); }
        catch(IllegalStateException ex){System.out.println(ex.getMessage());}
        i.registrarNota(85);
        System.out.println(d1.generarActa(m));
        // new Materia.Horario(LocalDate.now(),"Aula 1"); // No compila: constructor privado.
    }
}
