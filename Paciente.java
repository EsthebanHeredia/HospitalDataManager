import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Clase Paciente que implementa Serializable para permitir la serialización del objeto
public class Paciente implements Serializable {
    // serialVersionUID para asegurar la compatibilidad de la versión en el proceso de serialización
    private static final long serialVersionUID = 1L;

    // Identificador único del paciente
    private String id;
    // Nombre del paciente
    private String nombre;
    // Lista que almacena el historial médico del paciente
    private List<String> historialMedico = new ArrayList<>();
    // Lista que almacena las citas médicas del paciente
    private List<String> citasMedicas = new ArrayList<>();

    // Constructor que inicializa el id y el nombre del paciente
    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Método para obtener el ID del paciente
    public String getId() {
        return id;
    }

    // Método para obtener el nombre del paciente
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el historial médico del paciente
    public List<String> getHistorialMedico() {
        return historialMedico;
    }

    // Método para agregar una entrada al historial médico del paciente
    public void agregarHistorialMedico(String historial) {
        this.historialMedico.add(historial);
    }

    // Método para agregar una nueva cita médica al paciente
    public void agregarCitaMedica(String cita) {
        this.citasMedicas.add(cita);
    }

    // Método toString que devuelve una representación en cadena del paciente
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre;
    }
}

