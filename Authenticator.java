public class Authenticator {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public static boolean verifyCredentials(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}
