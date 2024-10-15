import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "admin";
    private final DataHandler data = new DataHandler();

    public static void main(String[] args) {
@@ -48,7 +49,7 @@
        }
    }

        private void showMenuPaciente() {
    private void showMenuPaciente() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Paciente");
@@ -106,14 +107,8 @@
                        buscarDoctor();
                        break;
                    case 3:
                        System.out.println("\n[Accesso Denegado] El usuario y contraseña del administrador se require."); 
                    if (verifyAdminPassword()) {
                        System.out.println("Correct password, now you may exterminate the doctor. (who?)"); //Hecho por Adrian Arimany.
                        eliminarDoctor();
                    } else {
                        System.out.println("The verification can not be completed.");
                    }
                    break;
                        break;
                    case 4:
                        verHistorialMedico();
                        break;
@@ -130,7 +125,7 @@
        }
    }

        private void showMenuClinica() {
    private void showMenuClinica() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Clínica");
@@ -161,7 +156,8 @@
            }
        }
    }
        private boolean buscarPaciente() {
    private boolean buscarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = data.readPacientes();
@@ -175,53 +171,53 @@
        return false;
    }

        private void agregarPaciente() {
            System.out.print("Ingrese ID del paciente: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese nombre del paciente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese ID del doctor a cargo: ");
            String doctorId = scanner.nextLine();
            System.out.print("Ingrese ID de la clínica: ");
            String clinicaId = scanner.nextLine();
            System.out.print("Ingrese historial médico del paciente: ");
            String historialMedico = scanner.nextLine();
            System.out.print("Ingrese enfermedades del paciente: ");
            String enfermedades = scanner.nextLine();
            System.out.print("Ingrese cita médica del paciente: ");
            String citaMedica = scanner.nextLine();
    
            Paciente paciente = new Paciente(id, nombre, doctorId, clinicaId);
            paciente.agregarHistorialMedico(historialMedico);
            paciente.agregarEnfermedad(enfermedades);
            paciente.agregarCitaMedica(citaMedica);
    
            data.writePaciente(paciente);
            System.out.println("Paciente agregado con éxito.");
    private void agregarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del doctor a cargo: ");
        String doctorId = scanner.nextLine();
        System.out.print("Ingrese ID de la clínica: ");
        String clinicaId = scanner.nextLine();
        System.out.print("Ingrese historial médico del paciente: ");
        String historialMedico = scanner.nextLine();
        System.out.print("Ingrese enfermedades del paciente: ");
        String enfermedades = scanner.nextLine();
        System.out.print("Ingrese cita médica del paciente: ");
        String citaMedica = scanner.nextLine();
        Paciente paciente = new Paciente(id, nombre, doctorId, clinicaId);
        paciente.agregarHistorialMedico(historialMedico);
        paciente.agregarEnfermedad(enfermedades);
        paciente.agregarCitaMedica(citaMedica);
        data.writePaciente(paciente);
        System.out.println("Paciente agregado con éxito.");
    }

        private void eliminarPaciente() {
    private void eliminarPaciente() {
        System.out.print("Ingrese ID del paciente a eliminar: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = data.readPacientes();
        boolean eliminado = false;
        List<Paciente> updatedPacientes = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (!paciente.getId().equals(id)) {
                updatedPacientes.add(paciente);
            } else {
                eliminado = true;
            }
        }
        boolean success = data.writePacientes(updatedPacientes);
        if (eliminado && success) {
            System.out.println("Paciente eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el paciente.");
        }
    }

        private boolean buscarDoctor() {
    private boolean buscarDoctor() {
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine();
        List<Doctor> doctores = data.readDoctores();
@@ -235,165 +231,97 @@
        return false;
    }

        private void agregarDoctor() {
            System.out.print("Ingrese ID del doctor: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese nombre del doctor: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese ID de la clínica: ");
            String clinicaId = scanner.nextLine();
    
            Doctor doctor = new Doctor(id, nombre, clinicaId);
            data.writeDoctor(doctor);
            System.out.println("Doctor agregado con éxito.");
    private void agregarDoctor() {
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID de la clínica: ");
        String clinicaId = scanner.nextLine();
        Doctor doctor = new Doctor(id, nombre, clinicaId);
        data.writeDoctor(doctor);
        System.out.println("Doctor agregado con éxito.");
    }

    /**
     * AAZ
     * Elimina a un Doctor del sistema.
     */
    private void eliminarDoctor() {
        System.out.print("Ingrese ID del doctor a eliminar: ");
        String id = scanner.nextLine();
        
        //busca al id del doctor usando el metodo idExists.
        if (!data.idExists("doctores.csv", id)) {
            System.out.println("Doctor no encontrado.");
            return;
        }
        List<Doctor> doctores = data.readDoctores();
        boolean eliminado = false;
        List<Doctor> updatedDoctores = new ArrayList<>();
        for (Doctor doctor : doctores) { // Busca el id del doctor para eliminar.
        for (Doctor doctor : doctores) {
            if (!doctor.getId().equals(id)) {
                updatedDoctores.add(doctor);
            } else {
                eliminado = true;
            }
        }
        
        // Actualiza el doctor.csv file.
        boolean success = data.writeDoctores(updatedDoctores);
        
        if (success) {
        if (eliminado && success) {
            System.out.println("Doctor eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el doctor.");
        }
    }

    /**
     * AAZ
     * Este metodo funciona para ver el historial medico de un paciente; pero primero require buscar el doctor que esta registrado bajo ese paciente.
     */
    private void verHistorialMedico() {
        System.out.print("Ingrese ID del doctor: ");
        String doctorId = scanner.nextLine();
    
        if (!data.idExists("doctores.csv", doctorId)) { //utiliza el metodo en DataHandler idExists.
            System.out.println("Doctor no encontrado.");
            return;
        }
    
        List<Doctor> doctores = data.readDoctores();
        Doctor doctor = null;
        for (Doctor doc : doctores) { //busca el id del doctor
        for (Doctor doc : doctores) {
            if (doc.getId().equals(doctorId)) {
            doctor = doc;
                doctor = doc;
                break;
            }
        }
        
        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        System.out.print("Ingrese ID del paciente: "); 
        System.out.print("Ingrese ID del paciente: ");
        String pacienteId = scanner.nextLine();
    
        // Verifica que el paciente existe en el csv.
        if (!data.idExists("pacientes.csv", pacienteId)) { //utiliza el metodo en DataHandler idExists.
            System.out.println("Paciente no encontrado.");
            return;
        }
        
        // Si el paciente existe, da la informacion del paciente.
        List<Paciente> pacientes = data.readPacientes();
        Paciente paciente = null;
        for (Paciente pac : pacientes) {
            if (pac.getId().equals(pacienteId)) {
                paciente = pac;
                break;
            }
        }
        // Determina si el paciente existe
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }
    
        // Captura el historial medico del paciente como un  String; note que verHistorialMeidco(paciente) es un metodo de la clase Doctor.
        String historialMedico = doctor.verHistorialMedico(paciente);
    
        // Imprime el historial medico del paciente
        System.out.println(historialMedico); 
        doctor.verHistorialMedico(paciente);
    }
    
    /**
     * AAZ
     * Este Metodo funciona para ver todas las clinicas que estan registradas en clinicas.csv
     */
    private void infoClinica() {
        List<Clinica> clinicas = data.readClinicas();
        if (clinicas.isEmpty()) {
            System.out.println("No hay clínicas registradas.");
            return;
        }

        System.out.println("Información de Clínicas:");
        for (Clinica clinica : clinicas) {
            System.out.println(clinica);
        }
    }
    /**
     * AAZ
     * Este metodo funciona para agregar una clinica a su CSV corespondiente, usando sus atributos.
     */
    private void agregarClinica() {
        System.out.print("Ingrese ID de la clínica: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre de la clínica: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese dirección de la clínica: ");
        String direccion = scanner.nextLine();

        Clinica clinica = new Clinica(id, nombre, direccion);
        data.writeClinica(clinica); //Pone la info de arriva dentro de clinicas.csv
        data.writeClinica(clinica);
        System.out.println("Clínica agregada con éxito.");
    }
    /**
     * AAZ
     * Este metodo es para verificar la clave del admin. 
     * @return true = contraseña y usuario corecta; false = contraseña y usuario incorecta. 
     */
    private boolean verifyAdminPassword() {
        int attempts = 2; // the number of times the the user can attempt to answer the password.
        while (attempts > 0) {
            System.out.println("Ponga el usuario del Administrador: ");
            String inputUsername = scanner.nextLine();
            System.out.println("Ponga contraseña del Administrador: ");
            String inputPassword = scanner.nextLine();
            if (Authenticator.verifyCredentials(inputUsername, inputPassword)) { 
                return true;
            }
            attempts--; //remember the -- is like saying doing a substraction for each loop irritation.
            if (attempts > 0) {
                System.out.println("La contraseña o usuario estan equivocada. Prueve de nuevo");
            }
        }
        System.out.println("Maximo numero de intentos terminados");
        return false;
    }
}
}
