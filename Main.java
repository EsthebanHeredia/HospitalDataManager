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
    }
        System.out.println("Paciente no encontrado.");

        /**
     * Agrega un nuevo paciente al archivo CSV.
     */
    private static void agregarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del doctor a cargo: ");
        String doctorId = scanner.nextLine();
        System.out.print("Ingrese ID de la clínica: ");
        String clinicaId = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre, doctorId, clinicaId);
        escribirPaciente(paciente);
        System.out.println("Paciente agregado con éxito.");
    }
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
    /**
     * Muestra la información de todas las clínicas registradas en el sistema.
     * Si no hay clínicas registradas, se informa al usuario.
     * Para cada clínica registrada, se imprime su información.
     */
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

    /**
     * Agrega una nueva clínica al archivo CSV.
     */
    private static void agregarClinica() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID de la clínica: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre de la clínica: ");
        String nombre = scanner.nextLine();

        Clinica clinica = new Clinica(id, nombre);
        escribirClinica(clinica);
        System.out.println("Clínica agregada con éxito.");
    }
    /**
     * Lee y retorna una lista de pacientes desde el archivo CSV.
     *
     * @return Lista de objetos Paciente leída desde el archivo.
     */
    private static List<Paciente> leerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 4) {
                    pacientes.add(new Paciente(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    /**
     * Lee y retorna una lista de doctores desde el archivo CSV.
     *
     * @return Lista de objetos Doctor leída desde el archivo.
     */
    private static List<Doctor> leerDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 3) {
                    doctores.add(new Doctor(parts[0], parts[1], parts[2]));
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return doctores;
    }

    /**
     * Lee y retorna una lista de clínicas desde el archivo CSV.
     *
     * @return Lista de objetos Clinica leída desde el archivo.
     */
    private static List<Clinica> leerClinicas() {
        List<Clinica> clinicas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 2) {
                    clinicas.add(new Clinica(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clinicas;
    }
}

