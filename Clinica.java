// Importa la clase Serializable para permitir la serialización de objetos.
import java.io.Serializable;

/**
 * Clase Clinica para representar una clínica.
 * Implementa Serializable para permitir la serialización de sus instancias.
 */
public class Clinica implements Serializable {
    // Versión de la clase para la serialización.
    private static final long serialVersionUID = 1L;

    // Identificador único de la clínica.
    private String id;

    // Nombre de la clínica.
    private String nombre;

    // Dirección de la clínica.
    private String direccion;

    /**
     * Constructor de la clase Clinica.
     * Inicializa los atributos id, nombre y direccion.
     *
     * @param id Identificador único de la clínica.
     * @param nombre Nombre de la clínica.
     * @param direccion Dirección de la clínica.
     */
    public Clinica(String id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    /**
     * Obtiene el identificador de la clínica.
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
     * Obtiene la dirección de la clínica.
     *
     * @return La dirección de la clínica.
     */
    public String getDireccion() {
        return direccion;
    }
}