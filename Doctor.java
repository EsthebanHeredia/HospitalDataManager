import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String nombre;
    private String especialidad;
    private String contrasena;

    // Constructor
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
}
