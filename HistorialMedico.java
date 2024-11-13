import java.io.Serializable;
import java.util.Date;

public class HistorialMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Date fecha;
    private String doctor;
    private String diagnostico;
    private String sintomas;
    private String receta;
    private String clinica;

    public HistorialMedico(Date fecha, String doctor, String diagnostico, String sintomas, String receta, String clinica) {
        this.fecha = fecha;
        this.doctor = doctor;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.receta = receta;
        this.clinica = clinica;
    }

        // Getters
    public Date getFecha() { return fecha; }
    public String getDoctor() { return doctor; }
    public String getDiagnostico() { return diagnostico; }
    public String getSintomas() { return sintomas; }
    public String getReceta() { return receta; }
    public String getClinica() { return clinica; }

        @Override
    public String toString() {
        return "Fecha: " + fecha +
               ", Doctor: " + doctor +
               ", Diagnóstico: " + diagnostico +
               ", Síntomas: " + sintomas +
               ", Receta: " + receta +
               ", Clínica: " + clinica;
    }

}
