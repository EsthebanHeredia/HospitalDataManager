import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String PACIENTE_CSV = "data.csv";

    private String id;
    private String nombre;
    private List<HistorialMedico> historialMedico;
    private List<CitaMedica> citasMedicas;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.historialMedico = new ArrayList<>();
        this.citasMedicas = new ArrayList<>();
    }

        public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<HistorialMedico> getHistorialMedico() { return historialMedico; }
    public void setHistorialMedico(List<HistorialMedico> historialMedico) { this.historialMedico = historialMedico; }
    public List<CitaMedica> getCitasMedicas() { return citasMedicas; }
    public void setCitasMedicas(List<CitaMedica> citasMedicas) { this.citasMedicas = citasMedicas; }
}
