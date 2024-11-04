import java.util.HashMap;
import java.util.Map;

public class DataHandler {
    private final Map<String, Paciente> pacientes = new HashMap<>();
    private final Map<String, Doctor> doctores = new HashMap<>();
    private final Map<String, Clinica> clinicas = new HashMap<>();
    private static int pacienteCounter = 1;
    private static int doctorCounter = 1;
    private static int clinicaCounter = 1;

    public void writePaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
    }

    public void writeDoctor(Doctor doctor) {
        doctores.put(doctor.getId(), doctor);
    }

    public void writeClinica(Clinica clinica) {
        clinicas.put(clinica.getId(), clinica);
    }

    public Paciente buscarPacientePorId(String id) {
        return pacientes.get(id);
    }

    public Doctor buscarDoctorPorCredenciales(String usuario, String contrasena) {
        for (Doctor doctor : doctores.values()) {
            if (doctor.getNombre().equals(usuario) && contrasena.equals("doctor123")) {
                return doctor;
            }
        }
        return null;
    }

    public boolean esAdministrador(String usuario, String contrasena) {
        return "admin".equals(usuario) && "admin123".equals(contrasena);
    }

    public String generarIdPaciente() {
        return String.format("P%03d", pacienteCounter++);
    }

    public String generarIdDoctor() {
        return String.format("D%03d", doctorCounter++);
    }

    public String generarIdClinica() {
        return String.format("C%03d", clinicaCounter++);
    }
}

}
