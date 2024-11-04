import java.util.HashMap;
import java.util.Map;

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

    // Método para almacenar un objeto Paciente en el Map de pacientes
    public void writePaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    // Método para almacenar un objeto Doctor en el Map de doctores
    public void writeDoctor(Doctor doctor) {
        doctores.put(doctor.getId(), doctor);
    }

    // Método para almacenar un objeto Clinica en el Map de clínicas
    public void writeClinica(Clinica clinica) {
        clinicas.put(clinica.getId(), clinica);
    }

    // Método para buscar y devolver un paciente por su ID en el Map de pacientes
    public Paciente buscarPacientePorId(String id) {
        return pacientes.get(id);
    }

    // Método para buscar un doctor por su nombre de usuario y contraseña en el Map de doctores
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

    // Método para verificar si las credenciales corresponden a un administrador
    public boolean esAdministrador(String usuario, String contrasena) {
        // Verifica si el nombre de usuario y la contraseña son los de un administrador
        return "admin".equals(usuario) && "admin123".equals(contrasena);
    }

    // Genera un nuevo ID único para un paciente, usando el contador de pacientes
    public String generarIdPaciente() {
        return String.format("P%03d", pacienteCounter++);
    }

    // Genera un nuevo ID único para un doctor, usando el contador de doctores
    public String generarIdDoctor() {
        return String.format("D%03d", doctorCounter++);
    }

    // Genera un nuevo ID único para una clínica, usando el contador de clínicas
    public String generarIdClinica() {
        return String.format("C%03d", clinicaCounter++);
    }
}



