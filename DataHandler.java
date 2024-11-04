import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de manejar el almacenamiento y recuperación de pacientes, doctores y clínicas.
 */
public class DataHandler {
    // Map para almacenar los pacientes, usando su ID como clave
    private final Map<String, Paciente> pacientes = new HashMap<>();
    
    // Map para almacenar los doctores, usando su ID como clave
    private final Map<String, Doctor> doctores = new HashMap<>();
    
    // Map para almacenar las clínicas, usando su ID como clave
    private final Map<String, Clinica> clinicas = new HashMap<>();
    
    // Contadores estáticos para generar IDs únicos de pacientes, doctores y clínicas
    private static int pacienteCounter = 1;
    private static int doctorCounter = 1;
    private static int clinicaCounter = 1;

    /**
     * Almacena un objeto Paciente en el Map de pacientes.
     *
     * @param paciente El objeto Paciente a almacenar
     */
    public void writePaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    /**
     * Almacena un objeto Doctor en el Map de doctores.
     *
     * @param doctor El objeto Doctor a almacenar
     */
    public void writeDoctor(Doctor doctor) {
        doctores.put(doctor.getId(), doctor);
    }

    /**
     * Almacena un objeto Clinica en el Map de clínicas.
     *
     * @param clinica El objeto Clinica a almacenar
     */
    public void writeClinica(Clinica clinica) {
        clinicas.put(clinica.getId(), clinica);
    }

    /**
     * Busca y devuelve un paciente por su ID en el Map de pacientes.
     *
     * @param id El ID del paciente a buscar
     * @return El objeto Paciente correspondiente al ID, o null si no se encuentra
     */
    public Paciente buscarPacientePorId(String id) {
        return pacientes.get(id);
    }

    /**
     * Busca un doctor por su nombre de usuario y contraseña en el Map de doctores.
     *
     * @param usuario El nombre de usuario del doctor
     * @param contrasena La contraseña del doctor
     * @return El objeto Doctor correspondiente a las credenciales, o null si no se encuentra
     */
    public Doctor buscarDoctorPorCredenciales(String usuario, String contrasena) {
        // Recorre todos los doctores y verifica si el nombre y contraseña coinciden
        for (Doctor doctor : doctores.values()) {
            if (doctor.getNombre().equals(usuario) && contrasena.equals("doctor123")) {
                return doctor;
            }
        }
        // Si no se encuentra coincidencia, devuelve null
        return null;
    }

    /**
     * Verifica si las credenciales corresponden a un administrador.
     *
     * @param usuario El nombre de usuario del administrador
     * @param contrasena La contraseña del administrador
     * @return true si las credenciales son válidas, false en caso contrario
     */
    public boolean esAdministrador(String usuario, String contrasena) {
        // Verifica si el nombre de usuario y la contraseña son los de un administrador
        return "admin".equals(usuario) && "admin123".equals(contrasena);
    }

    /**
     * Genera un nuevo ID único para un paciente.
     *
     * @return El nuevo ID único para el paciente en formato "PXXX"
     */
    public String generarIdPaciente() {
        return String.format("P%03d", pacienteCounter++);
    }

    /**
     * Genera un nuevo ID único para un doctor.
     *
     * @return El nuevo ID único para el doctor en formato "DXXX"
     */
    public String generarIdDoctor() {
        return String.format("D%03d", doctorCounter++);
    }

    /**
     * Genera un nuevo ID único para una clínica.
     *
     * @return El nuevo ID único para la clínica en formato "CXXX"
     */
    public String generarIdClinica() {
        return String.format("C%03d", clinicaCounter++);
    }
}




