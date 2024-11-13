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

        public static List<HistorialMedico> parseHistorialMedico(String historialStr) {
        List<HistorialMedico> historial = new ArrayList<>();
        if (historialStr.isEmpty() || historialStr.equals("\"\"")) return historial;

        String[] registros = historialStr.replace("\"", "").split(";");
        for (String registro : registros) {
            if (!registro.isEmpty()) {
                String[] datos = registro.split("\\|");
                if (datos.length >= 6) {
                    try {
                        Date fecha = new Date(Long.parseLong(datos[0]));
                        historial.add(new HistorialMedico(
                            fecha, datos[1], datos[2], datos[3], datos[4], datos[5]
                        ));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear fecha: " + e.getMessage());
                    }
                }
            }
        }
        return historial;
    }
}
