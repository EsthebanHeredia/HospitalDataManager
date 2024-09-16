import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String doctor;
    private String clinica;

    public Paciente(String id, String nombre, String doctor, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.doctor = doctor;
        this.clinica = clinica;
    }

}