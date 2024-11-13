// Administrador.java
import java.io.*;
import java.util.*;

public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String contrasena;

    public Administrador(String id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContrasena() { return contrasena; }

    public static void iniciarSesion(Scanner scanner, DataHandler dataHandler) {
        System.out.print("Ingrese su usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        AuthResult<Administrador> authResult = new Authenticator(dataHandler)
            .<Administrador>autenticar("administrador", nombre, contrasena, "");
            
        if (authResult.isAuthenticated()) {
            Administrador admin = authResult.getUser();
            admin.menuAdministrador(scanner, dataHandler);
        } else {
            System.out.println("Credenciales inválidas");
            esperarEnter(scanner);
        }
    }

    public void menuAdministrador(Scanner scanner, DataHandler dataHandler) {
        boolean running = true;
        while (running) {
            limpiarPantalla();
            System.out.println("=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Ver pacientes");
            System.out.println("2. Ver doctores");
            System.out.println("3. Ver clínicas");
            System.out.println("4. Agregar paciente");
            System.out.println("5. Agregar doctor");
            System.out.println("6. Agregar clínica");
            System.out.println("7. Agregar administrador");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        dataHandler.verPacientes();
                        esperarEnter(scanner);
                        break;
                    case 2:
                        dataHandler.verDoctores();
                        esperarEnter(scanner);
                        break;
                    case 3:
                        dataHandler.verClinicas();
                        esperarEnter(scanner);
                        break;
                    case 4:
                        dataHandler.agregarPacienteConScanner(scanner);
                        break;
                    case 5:
                        dataHandler.agregarDoctorConScanner(scanner);
                        break;
                    case 6:
                        dataHandler.agregarClinicaConScanner(scanner);
                        break;
                    case 7:
                        dataHandler.agregarAdministradorConScanner(scanner);
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción inválida");
                        esperarEnter(scanner);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido");
                esperarEnter(scanner);
            }
        }
    }

    private static void esperarEnter(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private void limpiarPantalla() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al limpiar pantalla.");
        }
    }
}