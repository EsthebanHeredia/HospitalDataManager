import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clase que representa a un Doctor.
 */
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String especialidad;
    private String contrasena;

    /**
     * Constructor de la clase Doctor.
     * 
     * @param id El ID del doctor.
     * @param nombre El nombre del doctor.
     * @param especialidad La especialidad del doctor.
     * @param contrasena La contraseña del doctor.
     */
    public Doctor(String id, String nombre, String especialidad, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.contrasena = contrasena;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public String getContrasena() { return contrasena; }

    /**
     * Método para iniciar sesión como doctor.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @param dataHandler El objeto DataHandler para manejar los datos.
     */
    public static void iniciarSesion(Scanner scanner, DataHandler dataHandler) {
        System.out.print("Ingrese su usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        AuthResult<Doctor> authResult = new Authenticator(dataHandler)
            .<Doctor>autenticar("doctor", nombre, contrasena, "");
            
        if (authResult.isAuthenticated()) {
            Doctor doctor = authResult.getUser();
            doctor.menuDoctor(scanner, dataHandler);
        } else {
            System.out.println("Credenciales inválidas");
            esperarEnter(scanner);
        }
    }

    /**
     * Método para mostrar el menú del doctor.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @param dataHandler El objeto DataHandler para manejar los datos.
     */
    public void menuDoctor(Scanner scanner, DataHandler dataHandler) {
        boolean running = true;
        while (running) {
            try {
                limpiarPantalla();
                System.out.println("=== MENÚ DOCTOR ===");
                System.out.println("1. Ver pacientes");
                System.out.println("2. Atender paciente");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        dataHandler.verPacientes();
                        esperarEnter(scanner);
                        break;
                    case 2:
                        atenderPaciente(scanner, dataHandler);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción inválida");
                        esperarEnter(scanner);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                esperarEnter(scanner);
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                esperarEnter(scanner);
            }
        }
    }

    /**
     * Método para atender a un paciente.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @param dataHandler El objeto DataHandler para manejar los datos.
     */
    private void atenderPaciente(Scanner scanner, DataHandler dataHandler) {
        try {
            System.out.print("Ingrese el ID del paciente: ");
            String pacienteId = scanner.nextLine();
            Paciente paciente = Paciente.buscarPacientePorId(pacienteId);
            if (paciente == null) {
                System.out.println("Paciente no encontrado.");
                esperarEnter(scanner);
                return;
            }

            List<CitaMedica> citas = paciente.getCitasMedicas();
            if (citas.isEmpty()) {
                System.out.println("El paciente no tiene citas previas.");
                esperarEnter(scanner);
                return;
            }

            CitaMedica ultimaCita = citas.get(citas.size() - 1);
            System.out.println("Última cita:");
            System.out.println("Fecha: " + new SimpleDateFormat("dd MM yyyy HH mm").format(ultimaCita.getFecha()));
            System.out.println("Descripción: " + ultimaCita.getDescripcion());

            System.out.println("Ingrese los detalles de la consulta:");

            System.out.print("Ingrese la fecha de la consulta (formato: dd MM yyyy HH mm): ");
            String fechaStr = scanner.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH mm");
            Date fecha;
            try {
                fecha = sdf.parse(fechaStr);
            } catch (ParseException e) {
                System.err.println("Error al parsear la fecha: " + e.getMessage());
                return;
            }

            System.out.print("Ingrese los síntomas: ");
            String sintomas = scanner.nextLine();
            System.out.print("Ingrese el diagnóstico: ");
            String diagnostico = scanner.nextLine();
            System.out.print("Ingrese la receta: ");
            String receta = scanner.nextLine();

            System.out.println("Seleccione la clínica donde se atendió al paciente:");
            List<Clinica> clinicas = dataHandler.obtenerTodasClinicas();
            for (int i = 0; i < clinicas.size(); i++) {
                System.out.println((i + 1) + ". " + clinicas.get(i).getNombre());
            }
            int clinicaIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (clinicaIndex < 0 || clinicaIndex >= clinicas.size()) {
                System.out.println("Opción de clínica inválida");
                return;
            }
            String clinica = clinicas.get(clinicaIndex).getNombre();

            HistorialMedico historial = new HistorialMedico(fecha, nombre, sintomas, diagnostico, receta, clinica);
            paciente.getHistorialMedico().add(historial);

            // Guardar los cambios del historial médico del paciente
            paciente.guardarPaciente();
            System.out.println("Consulta registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al atender paciente: " + e.getMessage());
        } finally {
            esperarEnter(scanner);
        }
    }

    /**
     * Método para esperar que el usuario presione Enter.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    private static void esperarEnter(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Método para limpiar la pantalla.
     */
    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
