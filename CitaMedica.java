import java.io.Serializable;
import java.util.Date;

public class CitaMedica implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Date fecha;
    private String descripcion;

    public CitaMedica(Date fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

        // Getters
    public Date getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "Fecha: " + fecha + ", Descripción: " + descripcion;
    }
    
}
