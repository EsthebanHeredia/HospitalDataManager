import java.io.Serializable;

public class Authenticator implements Serializable {
    private static final long serialVersionUID = 1L;
    private final DataHandler dataHandler;

    public Authenticator(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

        @SuppressWarnings("unchecked")
    public <T> AuthResult<T> autenticar(String role, String nombre, String contrasena, String id) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío");
        }

        try {
            Object user = null;
            switch (role.toLowerCase()) {
                case "administrador":
                    if (nombre == null || contrasena == null) {
                        throw new IllegalArgumentException("Credenciales incompletas");
                    }
                    user = dataHandler.buscarAdministradorPorCredenciales(nombre, contrasena);
                    break;
                case "doctor":
                    if (nombre == null || contrasena == null) {
                        throw new IllegalArgumentException("Credenciales incompletas");
                    }
                    user = dataHandler.buscarDoctorPorCredenciales(nombre, contrasena);
                    break;
                case "paciente":
                    if (id == null || id.trim().isEmpty()) {
                        throw new IllegalArgumentException("ID de paciente requerido");
                    }
                    user = dataHandler.buscarPacientePorId(id);
                    break;
                default:
                    throw new IllegalArgumentException("Rol no reconocido: " + role);
            }
            return new AuthResult<>((T) user);
        } catch (Exception e) {
            System.err.println("Error en autenticación: " + e.getMessage());
            return new AuthResult<>(null);
        }
    }
}
