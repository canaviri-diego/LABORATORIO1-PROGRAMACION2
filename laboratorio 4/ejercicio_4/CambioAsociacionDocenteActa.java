import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Cambio central: Docente conserva las actas que genera.
class DocenteConActas {
    private final List<ActaSimple> actas = new ArrayList<>();
    public ActaSimple generarActa(String contenido) {
        ActaSimple a = new ActaSimple(contenido);
        actas.add(a);
        return a;
    }
    public List<ActaSimple> getActas() {
        return Collections.unmodifiableList(actas);
    }
}
class ActaSimple {
    private final String contenido;
    ActaSimple(String c) { contenido = c; }
    @Override public String toString() { return contenido; }
}
public class CambioAsociacionDocenteActa {
    public static void main(String[] args) {
        DocenteConActas d = new DocenteConActas();
        d.generarActa("Acta INF-121");
        System.out.println("Actas del docente: " + d.getActas().size());
    }
}
