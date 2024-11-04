import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private List<String> historialMedico = new ArrayList<>();
    private List<String> citasMedicas = new ArrayList<>();

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getHistorialMedico() {
        return historialMedico;
    }

    public void agregarHistorialMedico(String historial) {
        this.historialMedico.add(historial);
    }

    public void agregarCitaMedica(String cita) {
        this.citasMedicas.add(cita);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre;
    }
}
