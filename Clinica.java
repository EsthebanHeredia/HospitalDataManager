import java.io.Serializable;

/**
 * Representa una clínica con un identificador, nombre y dirección.
 * Implementa la interfaz Serializable para permitir su serialización.
 */
public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String direccion;

    /**
     * Constructor que inicializa una instancia de Clinica con el ID, nombre y dirección especificados.
     *
     * @param id        Identificador único de la clínica.
     * @param nombre    Nombre de la clínica.
     * @param direccion Dirección física de la clínica.
     */
    public Clinica(String id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    /**
     * Obtiene el identificador único de la clínica.
     *
     * @return El ID de la clínica.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la clínica.
     *
     * @return El nombre de la clínica.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la dirección física de la clínica.
     *
     * @return La dirección de la clínica.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve una representación en forma de cadena de la clínica, incluyendo su ID, nombre y dirección.
     *
     * @return Una cadena con los datos de la clínica.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion;
    }
}

