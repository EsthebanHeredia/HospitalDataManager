import java.io.Serializable;
import java.util.list;
/**
 * The Doctor class represents a doctor with an ID, name, and clinic.
 * This class implements Serializable to allow its instances to be serialized.
 */
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String clinica;

    /**
     * Constructs a new Doctor with the specified ID, name, and clinic.
     *
     * @param id      the ID of the doctor
     * @param nombre  the name of the doctor
     * @param clinica the clinic of the doctor
     */
    public Doctor(String id, String nombre, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.clinica = clinica;
    }

    /**
     * Returns the ID of the doctor.
     *
     * @return the ID of the doctor
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the doctor.
     *
     * @param id the new ID of the doctor
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the doctor.
     *
     * @return the name of the doctor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the doctor.
     *
     * @param nombre the new name of the doctor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the clinic of the doctor.
     *
     * @return the clinic of the doctor
     */
    public String getClinica() {
        return clinica;
    }

    /**
     * Sets the clinic of the doctor.
     *
     * @param clinica the new clinic of the doctor
     */
    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

        public void verHistorialMedico(Paciente paciente) {
        List<String> historial = paciente.getHistorialMedico();
        System.out.println("Historial médico de " + paciente.getNombre() + ":");
        for (String registro : historial) {
            System.out.println(registro);
        }
    }

    /**
     * Returns a string representation of the doctor.
     * The string representation consists of the ID, name, and clinic separated by commas.
     *
     * @return a string representation of the doctor
     */
    @Override
    public String toString() {
        return id + "," + nombre + "," + clinica;
    }
}
