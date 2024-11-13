/**
 * Clase genérica AuthResult para encapsular el resultado de una autenticación.
 * Utiliza un parámetro genérico <T> que representa el tipo de usuario autenticado
 * (puede ser Administrador, Doctor o Paciente).
 *
 * @param <T> Tipo del usuario autenticado.
 */
public class AuthResult<T> {

    // Variable que almacena al usuario autenticado.
    private T user;

    /**
     * Constructor de AuthResult.
     * Inicializa el resultado de autenticación con el usuario proporcionado.
     *
     * @param user El usuario autenticado o null si la autenticación falló.
     */
    public AuthResult(T user) {
        this.user = user;
    }

    /**
     * Obtiene el usuario autenticado.
     *
     * @return El usuario autenticado de tipo <T>, o null si no fue autenticado.
     */
    public T getUser() {
        return user;
    }

    /**
     * Verifica si la autenticación fue exitosa.
     *
     * @return true si el usuario fue autenticado correctamente (user no es null),
     *         false si la autenticación falló (user es null).
     */
    public boolean isAuthenticated() {
        return user != null;
    }
}