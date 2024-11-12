import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final DataHandler dataHandler = new DataHandler();
    private final Authenticator authenticator = new Authenticator(dataHandler);

    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            try {
                limpiarPantalla();
                System.out.println("=== SISTEMA DE GESTIÓN DE CLÍNICA ===");
                System.out.println("1. Ingresar como Paciente");
                System.out.println("2. Ingresar como Doctor");
                System.out.println("3. Ingresar como Administrador");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        Paciente.iniciarSesion(scanner);
                        break;
                    case 2:
                        Doctor.iniciarSesion(scanner, dataHandler);
                        break;
                    case 3:
                        Administrador.iniciarSesion(scanner, dataHandler);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción inválida");
                        esperarEnter();
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                esperarEnter();
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                esperarEnter();
            }
        }
        scanner.close();
    }

        private void esperarEnter() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}

