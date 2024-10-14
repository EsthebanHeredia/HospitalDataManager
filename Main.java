import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "admin";
    private final DataHandler data = new DataHandler();

    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }
}
