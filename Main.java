import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "admin";
    private final DataHandler data = new DataHandler();

    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("1. Gestionar Pacientes");
            System.out.println("2. Gestionar Doctores");
            System.out.println("3. Gestionar Clínicas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        showMenuPaciente();
                        break;
                    case 2:
                        showMenuDoctor();
                        break;
                    case 3:
                        showMenuClinica();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

        private void showMenuPaciente() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Buscar Paciente");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        agregarPaciente();
                        break;
                    case 2:
                        buscarPaciente();
                        break;
                    case 3:
                        eliminarPaciente();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

    private void showMenuDoctor() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Buscar Doctor");
            System.out.println("3. Eliminar Doctor");
            System.out.println("4. Ver Historial Médico de Paciente");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        agregarDoctor();
                        break;
                    case 2:
                        buscarDoctor();
                        break;
                    case 3:
                        eliminarDoctor();
                        break;
                    case 4:
                        verHistorialMedico();
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

        private void showMenuClinica() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Clínica");
            System.out.println("2. Ver Información de Clínicas");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        agregarClinica();
                        break;
                    case 2:
                        infoClinica();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }
        private boolean buscarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = data.readPacientes();
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                System.out.println("Paciente encontrado: " + paciente);
                return true;
            }
        }
        System.out.println("Paciente no encontrado.");
        return false;
    }
}
