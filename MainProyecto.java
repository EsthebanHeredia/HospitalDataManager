import java.io.*;
import java.util.*;

public class Main {

    // Nombre del archivo CSV para almacenar todos los datos
    private static final String FILE_NAME = "Proyecto.csv";
    private static final String DELIMITER = ",";  // Delimitador para separar campos en el CSV

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Para leer la entrada del usuario
        boolean running = true;  // Controla el bucle del menú
