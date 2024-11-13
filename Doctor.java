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
}
