import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String doctor;
    private String clinica;

    // Constructor de la clase Paciente
    public Paciente(String id, String nombre, String doctor, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.doctor = doctor;
        this.clinica = clinica;
    }

    // Getters y Setters de la clase

    // Devuelve el valor del ID del paciente.
    public String getId() {
        return id;
    }

    // Setter que permite modificar el valor del ID del paciente.
    public void setId(String id) {
        this.id = id;
    }

    // Getter que devuelve el nombre del paciente.
    public String getNombre() {
        return nombre;
    }

    // Setter que permite modificar el valor del nombre del paciente.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Este metodo funciona para obtener el nombre del doctor.
    public String getDoctor() {
        return doctor;
    }
}