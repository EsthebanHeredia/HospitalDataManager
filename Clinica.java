import java.io.Serializable; 


/**
 * Representa una clínica médica.
 * Esta clase implementa Serializable para permitir la serialización de objetos Clinica.
 */
public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String nombre;
    private String direccion;


    /**
     * Constructor para crear una nueva instancia de Clinica.
     *
     * @param nombre El nombre de la clínica.
     * @param direccion La dirección de la clínica.
     */
    public Clinica(String nombre, String direccion) { 
        this.nombre = nombre;
        this.direccion = direccion;
    }
 
    /**
     * Obtiene el nombre de la clínica.
     *
     * @return El nombre de la clínica.
     */
    public String getNombre() {  
        return this.nombre;
    }

    /**
     * Establece el nombre de la clínica.
     *
     * @param nombre El nuevo nombre de la clínica.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección de la clínica.
     *
     * @return La dirección de la clínica.
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Establece la dirección de la clínica.
     *
     * @param direccion La nueva dirección de la clínica.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    /**
     * Devuelve una representación en cadena de la clínica.
     *
     * @return Una cadena que contiene el nombre y la dirección de la clínica, separados por una coma.
     */
    @Override
    public String toString() {
        return nombre + "," + direccion;
    }
}


