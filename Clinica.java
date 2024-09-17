import java.io.Serializable; 

public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String nombre;
    private String direccion;

    public Clinica(String nombre, String direccion) { 
        this.nombre = nombre;
        this.direccion = direccion;
    }
 
    // Métodos getters y setters
    public String getNombre() {  
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + "," + direccion;
    }
}


