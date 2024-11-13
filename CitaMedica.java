// Importa la clase Serializable para permitir la serialización.
import java.io.Serializable;
import java.util.Date;

/**
 * Clase CitaMedica para representar una cita médica.
 * Implementa Serializable para permitir la serialización del objeto.
 */
public class CitaMedica implements Serializable {
    // Versión de la clase para la serialización.
    private static final long serialVersionUID = 1L;

    // Atributo para almacenar la fecha de la cita.
    private Date fecha;

    // Atributo para almacenar la descripción de la cita.
    private String descripcion;

    /**
     * Constructor de CitaMedica.
     * Inicializa la fecha y descripción de la cita.
     *
     * @param fecha Fecha de la cita médica.
     * @param descripcion Descripción de la cita médica.
     */
    public CitaMedica(Date fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha de la cita médica.
     *
     * @return Fecha de la cita.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Obtiene la descripción de la cita médica.
     *
     * @return Descripción de la cita.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve una representación en cadena de la cita médica.
     *
     * @return Cadena con la fecha y la descripción de la cita.
     */
    @Override
    public String toString() {
        return "Fecha: " + fecha + ", Descripción: " + descripcion;
    }
}