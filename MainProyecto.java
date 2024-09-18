import java.io.*;
import java.util.*;

public class Main {

    // Nombre del archivo CSV para almacenar todos los datos
    private static final String FILE_NAME = "Proyecto.csv";
    private static final String DELIMITER = ",";  // Delimitador para separar campos en el CSV

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Para leer la entrada del usuario
        boolean running = true;  // Controla el bucle del menú

    while (running) {
            mostrarMenu();  // Muestra el menú de opciones
            String choice = scanner.nextLine();  // Lee la opción seleccionada por el usuario

            // Ejecuta la acción correspondiente según la opción elegida
            switch (choice) {
                case "1":
                    buscarPaciente();
                    break;
                case "2":
                    agregarPaciente();
                    break;
                case "3":
                    eliminarPaciente();
                    break;
                case "4":
                    buscarDoctor();
                    break;
                case "5":
                    agregarDoctor();
                    break;
                case "6":
                    eliminarDoctor();
                    break;
                case "7":
                    infoClinica();
                    break;
                case "8":
                    agregarClinica();
                    break;
                case "9":
                    System.out.println("Saliendo...");
                    running = false;  // Termina el bucle y cierra el programa
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        scanner.close();  // Cierra el scanner al finalizar
    }

    // Muestra el menú de opciones al usuario
    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Buscar paciente");
        System.out.println("2. Agregar paciente");
        System.out.println("3. Eliminar paciente");
        System.out.println("4. Buscar doctor");
        System.out.println("5. Agregar doctor");
        System.out.println("6. Eliminar doctor");
        System.out.println("7. Información de la clínica");
        System.out.println("8. Agregar clínica");
        System.out.println("9. Salir");
    }

    // Busca un paciente por su ID e imprime su información
    private static void buscarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = leerPacientes();  // Lee todos los pacientes desde el archivo
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                System.out.println(paciente);  // Muestra el paciente encontrado
                return;
            }
        }
        System.out.println("Paciente no encontrado.");

        // Busca un paciente por su ID e imprime su información
    private static void buscarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = leerPacientes();  // Lee todos los pacientes desde el archivo
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                System.out.println(paciente);  // Muestra el paciente encontrado
                return;
            }
        }
        System.out.println("Paciente no encontrado.");

        /**
         * Elimina un paciente del registro basado en su ID.
         * Este método solicita al usuario el ID del paciente a eliminar,
         * busca el paciente en el archivo, lo elimina si lo encuentra,
         * y reescribe el archivo sin el paciente eliminado.
         */
    private static void eliminarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del paciente a eliminar: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = leerPacientes();
        boolean eliminado = false; 
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Paciente paciente : pacientes) {
                if (!paciente.getId().equals(id)) {
                    writer.println(paciente);
                } else {
                    eliminado = true; 
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (eliminado) {
            System.out.println("Paciente eliminado con éxito.");
        } else {
            System.out.println("Paciente no encontrado."); 
        }
    }

     private static void buscarDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine();
        List<Doctor> doctores = leerDoctores();  // Lee todos los doctores desde el archivo
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                System.out.println(doctor);  // Muestra el doctor encontrado
                return;
            }
        }
        System.out.println("Doctor no encontrado.");
    }

    /**
     * Agrega un nuevo doctor al sistema.
     * Este método solicita al usuario la información necesaria para crear un nuevo doctor,
     * crea un objeto Doctor con esa información, y lo guarda en el sistema.
     */
    private static void agregarDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID de la clínica: ");
        String clinicaId = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, clinicaId);
        escribirDoctor(doctor);
        System.out.println("Doctor agregado con éxito."); 
    }

    /**
     * Elimina un doctor del sistema basado en su ID.
     * Este método solicita al usuario el ID del doctor a eliminar,
     * busca el doctor en el archivo, lo elimina si lo encuentra,
     * y reescribe el archivo sin el doctor eliminado.
     */
    private static void eliminarDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del doctor a eliminar: ");
        String id = scanner.nextLine();
        List<Doctor> doctores = leerDoctores(); 
        boolean eliminado = false; 
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Doctor doctor : doctores) {
                if (!doctor.getId().equals(id)) {
                    writer.println(doctor);
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (eliminado) {
            System.out.println("Doctor eliminado con éxito.");
        } else {
            System.out.println("Doctor no encontrado."); 
        }
    }

    private static void infoClinica() {
        List<Clinica> clinicas = leerClinicas();
        if (clinicas.isEmpty()) {
            System.out.println("No hay clínicas registradas.");
        } else {
            for (Clinica clinica : clinicas) {
                System.out.println(clinica); 
            }
        }
    }
