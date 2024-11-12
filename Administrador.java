//Administrador.java
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

    //Getters
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
}
