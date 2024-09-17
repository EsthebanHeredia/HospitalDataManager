import java.io.Serializable;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String clinica;
    
    public Doctor(String id, String nombre, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.clinica = clinica;
    }

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
