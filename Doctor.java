import java.io.Serializable;

/**
 * Clase que representa a un doctor en el sistema.
 * Implementa la interfaz Serializable para permitir la serialización del objeto.
 */
public class Doctor implements Serializable {
    // SerialVersionUID para asegurar la compatibilidad de la versión en el proceso de serialización
    private static final long serialVersionUID = 1L;

    // Identificador único del doctor
    private String id;
    // Nombre del doctor
    private String nombre;
    // Especialidad del doctor
    private String especialidad;

    /**
     * Constructor que inicializa el id, nombre y especialidad del doctor.
     *
     * @param id Identificador único del doctor
     * @param nombre Nombre del doctor
     * @param especialidad Especialidad del doctor
     */
    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    /**
     * Método para obtener el ID del doctor.
     *
     * @return El ID del doctor
     */
    public String getId() {
        return id;
    }

    /**
     * Método para obtener el nombre del doctor.
     *
     * @return El nombre del doctor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener la especialidad del doctor.
     *
     * @return La especialidad del doctor
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Método toString que devuelve una representación en cadena del doctor.
     *
     * @return Una cadena que representa al doctor con su ID, nombre y especialidad
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Especialidad: " + especialidad;
    }
}

