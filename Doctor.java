import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clase que representa a un doctor en el sistema de administración hospitalaria.
 * Implementa la interfaz Serializable para la persistencia de datos.
 */
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String especialidad;
    private String contrasena;

    /**
     * Constructor para inicializar un doctor con su ID, nombre, especialidad y contraseña.
     *
     * @param id          El ID del doctor.
     * @param nombre      El nombre del doctor.
     * @param especialidad La especialidad médica del doctor.
     * @param contrasena  La contraseña para la autenticación.
     */
    public Doctor(String id, String nombre, String especialidad, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.contrasena = contrasena;
    }

    // Getters

    /**
     * Obtiene el ID del doctor.
     *
     * @return El ID del doctor.
     */
    public String getId() { return id; }

    /**
     * Obtiene el nombre del doctor.
     *
     * @return El nombre del doctor.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene la especialidad médica del doctor.
     *
     * @return La especialidad del doctor.
     */
    public String getEspecialidad() { return especialidad; }

    /**
     * Obtiene la contraseña del doctor.
     *
     * @return La contraseña del doctor.
     */
    public String getContrasena() { return contrasena; }

    /**
     * Método para iniciar sesión como doctor.
     *
     * @param scanner      Objeto Scanner para la entrada del usuario.
     * @param dataHandler  Manejador de datos para la autenticación y acceso a datos.
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
     * Muestra el menú principal del doctor y maneja las acciones correspondientes.
     *
     * @param scanner      Objeto Scanner para la entrada del usuario.
     * @param dataHandler  Manejador de datos para el acceso a datos de pacientes.
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
     * Permite al doctor atender a un paciente, registrando una consulta.
     *
     * @param scanner      Objeto Scanner para la entrada del usuario.
     * @param dataHandler  Manejador de datos para acceder y guardar información.
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

            // Mostrar la última cita del paciente
            CitaMedica ultimaCita = citas.get(citas.size() - 1);
            System.out.println("Última cita:");
            System.out.println("Fecha: " + new SimpleDateFormat("dd MM yyyy HH mm").format(ultimaCita.getFecha()));
            System.out.println("Descripción: " + ultimaCita.getDescripcion());

            // Registrar nueva consulta
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

            // Crear historial médico y guardarlo
            HistorialMedico historial = new HistorialMedico(fecha, nombre, sintomas, diagnostico, receta, clinica);
            paciente.getHistorialMedico().add(historial);
            paciente.guardarPaciente();
            System.out.println("Consulta registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al atender paciente: " + e.getMessage());
        } finally {
            esperarEnter(scanner);
        }
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     *
     * @param scanner Objeto Scanner para la entrada del usuario.
     */
    private static void esperarEnter(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Limpia la consola.
     */
    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
