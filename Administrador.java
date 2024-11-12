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
}
