import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String doctor;
    private String clinica;

    /**
     * Constructor de la clase Paciente.
     * Inicializa un nuevo objeto Paciente con la información proporcionada.
     *
     * @param id     El identificador único del paciente
     * @param nombre El nombre del paciente
     * @param doctor El nombre del doctor asignado al paciente
     * @param clinica El nombre de la clínica donde se atiende al paciente
     */
    public Paciente(String id, String nombre, String doctor, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.doctor = doctor;
        this.clinica = clinica;
    }

    /**
     * Obtiene el ID del paciente.
     *
     * @return El ID del paciente.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del paciente.
     *
     * @param id El nuevo ID a asignar al paciente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del paciente.
     *
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del paciente.
     *
     * @param nombre El nuevo nombre a asignar al paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del doctor asignado al paciente.
     *
     * @return El nombre del doctor.
     */
    public String getDoctor() {
        return doctor;
    }

    /**
    *Establece el nombre del doctor 
    *
    * @param El nombre del doctor. 
    */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
    *Obtiene el nombre de la clinica en el que el paciente es atendido.
    *
    *@return El nombre de la clinica.
    */
    public String getClinica() {
        return clinica;
    }

    /**
    *Establece el nombre de la clinica.
    *
    *@param El nombre de la clinica.
    */
    public void setClinica(String clinica) {
        this.clinica = clinica;
    }
    
}
