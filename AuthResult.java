public class AuthResult<T> {
    private T user;

    public AuthResult(T user) {
        this.user = user;
    }

    public T getUser() {
        return user;
    }

    public boolean isAuthenticated() {
        return user != null;
    }
}
