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
