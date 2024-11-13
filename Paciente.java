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

        public static List<CitaMedica> parseCitasMedicas(String citasStr) {
        List<CitaMedica> citas = new ArrayList<>();
        if (citasStr.isEmpty() || citasStr.equals("\"\"")) return citas;

        String[] registros = citasStr.replace("\"", "").split(";");
        for (String registro : registros) {
            if (!registro.isEmpty()) {
                String[] datos = registro.split("\\|");
                if (datos.length >= 2) {
                    try {
                        Date fecha = new Date(Long.parseLong(datos[0]));
                        citas.add(new CitaMedica(fecha, datos[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear fecha de cita: " + e.getMessage());
                    }
                }
            }
        }
        return citas;
    }

        public static String formatHistorialMedico(List<HistorialMedico> historialMedico) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (HistorialMedico hm : historialMedico) {
            sb.append(hm.getFecha().getTime()).append("|")
              .append(hm.getDoctor()).append("|")
              .append(hm.getSintomas()).append("|")
              .append(hm.getDiagnostico()).append("|")
              .append(hm.getReceta()).append("|")
              .append(hm.getClinica()).append(";");
        }
        sb.append("\"");
        return sb.toString();
    }

        public static String formatCitasMedicas(List<CitaMedica> citasMedicas) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (CitaMedica cm : citasMedicas) {
            sb.append(cm.getFecha().getTime()).append("|")
              .append(cm.getDescripcion()).append(";");
        }
        sb.append("\"");
        return sb.toString();
    }
}
