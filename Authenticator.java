// Authenticator.java
import java.io.Serializable; // Importa Serializable para permitir la serialización de la clase.

/**
 * Clase Authenticator para manejar la autenticación de usuarios.
 * Implementa Serializable para permitir la serialización de objetos de esta clase.
 */
public class Authenticator implements Serializable {
    // Versión de la clase para la serialización.
    private static final long serialVersionUID = 1L;

    // Objeto DataHandler para manejar la persistencia de datos.
    private final DataHandler dataHandler;

    /**
     * Constructor de Authenticator.
     * @param dataHandler Objeto DataHandler que se utilizará para buscar datos de usuario.
     */
    public Authenticator(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    /**
     * Método para autenticar a un usuario basado en su rol y credenciales.
     * Usa genéricos para devolver un resultado de autenticación para distintos tipos de usuario.
     * 
     * @param role Rol del usuario a autenticar (administrador, doctor, paciente).
     * @param nombre Nombre del usuario para autenticación (administrador o doctor).
     * @param contrasena Contraseña del usuario para autenticación (administrador o doctor).
     * @param id ID del paciente (solo requerido si el rol es "paciente").
     * @param <T> Tipo del usuario autenticado (Administrador, Doctor o Paciente).
     * @return AuthResult<T> Resultado de la autenticación, conteniendo el usuario autenticado o null si falla.
     * @throws IllegalArgumentException Si el rol es nulo o vacío, o si faltan credenciales necesarias.
     */
    @SuppressWarnings("unchecked") // Anotación para suprimir advertencias de conversión de tipos genéricos.
    public <T> AuthResult<T> autenticar(String role, String nombre, String contrasena, String id) {
        // Valida que el rol no sea nulo ni vacío.
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío");
        }

        try {
            Object user = null; // Variable para almacenar el usuario autenticado.

            // Determina el tipo de usuario basado en el rol proporcionado.
            switch (role.toLowerCase()) {
                case "administrador":
                    // Valida que las credenciales (nombre y contraseña) no sean nulas.
                    if (nombre == null || contrasena == null) {
                        throw new IllegalArgumentException("Credenciales incompletas");
                    }
                    // Busca al administrador en el DataHandler usando las credenciales proporcionadas.
                    user = dataHandler.buscarAdministradorPorCredenciales(nombre, contrasena);
                    break;

                case "doctor":
                    // Valida que las credenciales (nombre y contraseña) no sean nulas.
                    if (nombre == null || contrasena == null) {
                        throw new IllegalArgumentException("Credenciales incompletas");
                    }
                    // Busca al doctor en el DataHandler usando las credenciales proporcionadas.
                    user = dataHandler.buscarDoctorPorCredenciales(nombre, contrasena);
                    break;

                case "paciente":
                    // Valida que el ID del paciente no sea nulo ni vacío.
                    if (id == null || id.trim().isEmpty()) {
                        throw new IllegalArgumentException("ID de paciente requerido");
                    }
                    // Busca al paciente en el DataHandler usando el ID proporcionado.
                    user = dataHandler.buscarPacientePorId(id);
                    break;

                default:
                    // Si el rol no es reconocido, lanza una excepción.
                    throw new IllegalArgumentException("Rol no reconocido: " + role);
            }

            // Devuelve un objeto AuthResult con el usuario autenticado.
            return new AuthResult<>((T) user);
        } catch (Exception e) {
            // Muestra un mensaje de error en caso de excepción durante la autenticación.
            System.err.println("Error en autenticación: " + e.getMessage());
            // Devuelve un objeto AuthResult con valor null indicando fallo en la autenticación.
            return new AuthResult<>(null);
        }
    }
}