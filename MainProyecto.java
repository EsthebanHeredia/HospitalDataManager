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

