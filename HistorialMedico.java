import java.io.Serializable;
import java.util.Date;

/**
 * Clase HistorialMedico que representa el historial médico de un paciente.
 * Implementa la interfaz Serializable para permitir su almacenamiento y
 * recuperación en archivos.
 */
public class HistorialMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Fecha en la que se realizó la consulta médica. */
    private Date fecha;
    
    /** Nombre del doctor que atendió al paciente. */
    private String doctor;
    
    /** Diagnóstico proporcionado al paciente. */
    private String diagnostico;
    
    /** Síntomas que el paciente presentaba en la consulta. */
    private String sintomas;
    
    /** Receta o prescripción médica recomendada al paciente. */
    private String receta;
    
    /** Nombre de la clínica donde se realizó la consulta. */
    private String clinica;

    /**
     * Constructor de la clase HistorialMedico.
     * 
     * @param fecha la fecha de la consulta
     * @param doctor el nombre del doctor que atendió al paciente
     * @param diagnostico el diagnóstico proporcionado al paciente
     * @param sintomas los síntomas que el paciente presentaba
     * @param receta la receta o prescripción médica
     * @param clinica el nombre de la clínica donde se realizó la consulta
     */
    public HistorialMedico(Date fecha, String doctor, String diagnostico, String sintomas, String receta, String clinica) {
        this.fecha = fecha;
        this.doctor = doctor;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.receta = receta;
        this.clinica = clinica;
    }

        // Getters
    /** 
     * @return la fecha de la consulta.
     */
    public Date getFecha() { return fecha; }

    /** 
     * @return el nombre del doctor que atendió al paciente.
     */
    public String getDoctor() { return doctor; }

    /** 
     * @return el diagnóstico proporcionado al paciente.
     */
    public String getDiagnostico() { return diagnostico; }

    /** 
     * @return los síntomas que el paciente presentaba.
     */
    public String getSintomas() { return sintomas; }

    /** 
     * @return la receta o prescripción médica.
     */
    public String getReceta() { return receta; }

    /** 
     * @return el nombre de la clínica donde se realizó la consulta.
     */
    public String getClinica() { return clinica; }

    /**
     * Representación en cadena del historial médico.
     * 
     * @return una cadena de texto con todos los datos del historial médico.
     */
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
