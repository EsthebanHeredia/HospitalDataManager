// Administrador.java
import java.io.*; // Importa las clases para manejo de entrada/salida y serialización.
import java.util.*; // Importa utilidades como Scanner y colecciones.

// Clase Administrador que implementa Serializable para permitir la serialización del objeto.
public class Administrador implements Serializable {
    // Versión de la clase para la serialización.
    private static final long serialVersionUID = 1L;

    // Atributos privados del administrador.
    private String id; // Identificador único del administrador.
    private String nombre; // Nombre del administrador.
    private String contrasena; // Contraseña del administrador.

    // Constructor de la clase Administrador.
    public Administrador(String id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    // Métodos getters para obtener los valores de los atributos.

    /**
     * Devuelve el ID del administrador.
     * @return id del administrador.
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el nombre del administrador.
     * @return nombre del administrador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la contraseña del administrador.
     * @return contraseña del administrador.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método estático para iniciar sesión como administrador.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     * @param dataHandler Objeto para manejar la persistencia de datos.
     */
    public static void iniciarSesion(Scanner scanner, DataHandler dataHandler) {
        // Solicita al usuario ingresar nombre y contraseña.
        System.out.print("Ingrese su usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        // Autentica al administrador usando el objeto Authenticator.
        AuthResult<Administrador> authResult = new Authenticator(dataHandler)
            .<Administrador>autenticar("administrador", nombre, contrasena, "");

        // Verifica si la autenticación fue exitosa.
        if (authResult.isAuthenticated()) {
            Administrador admin = authResult.getUser();
            // Si es exitoso, muestra el menú del administrador.
            admin.menuAdministrador(scanner, dataHandler);
        } else {
            // Si falla, muestra un mensaje de error y espera a que el usuario presione Enter.
            System.out.println("Credenciales inválidas");
            esperarEnter(scanner);
        }
    }

    /**
     * Muestra el menú principal del administrador y gestiona las opciones seleccionadas.
     * @param scanner Objeto Scanner para la entrada de datos del usuario.
     * @param dataHandler Objeto para manejar la persistencia de datos.
     */
    public void menuAdministrador(Scanner scanner, DataHandler dataHandler) {
        boolean running = true; // Variable para controlar el ciclo del menú.
        while (running) {
            limpiarPantalla(); // Limpia la pantalla antes de mostrar el menú.
            // Muestra las opciones del menú.
            System.out.println("=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Ver pacientes");
            System.out.println("2. Ver doctores");
            System.out.println("3. Ver clínicas");
            System.out.println("4. Agregar paciente");
            System.out.println("5. Agregar doctor");
            System.out.println("6. Agregar clínica");
            System.out.println("7. Agregar administrador");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                // Intenta leer la opción seleccionada por el usuario.
                int opcion = Integer.parseInt(scanner.nextLine());

                // Evalúa la opción ingresada usando un switch.
                switch (opcion) {
                    case 1:
                        // Ver pacientes.
                        dataHandler.verPacientes();
                        esperarEnter(scanner);
                        break;
                    case 2:
                        // Ver doctores.
                        dataHandler.verDoctores();
                        esperarEnter(scanner);
                        break;
                    case 3:
                        // Ver clínicas.
                        dataHandler.verClinicas();
                        esperarEnter(scanner);
                        break;
                    case 4:
                        // Agregar un nuevo paciente usando Scanner.
                        dataHandler.agregarPacienteConScanner(scanner);
                        break;
                    case 5:
                        // Agregar un nuevo doctor usando Scanner.
                        dataHandler.agregarDoctorConScanner(scanner);
                        break;
                    case 6:
                        // Agregar una nueva clínica usando Scanner.
                        dataHandler.agregarClinicaConScanner(scanner);
                        break;
                    case 7:
                        // Agregar un nuevo administrador usando Scanner.
                        dataHandler.agregarAdministradorConScanner(scanner);
                        break;
                    case 8:
                        // Finaliza el ciclo y sale del menú.
                        running = false;
                        break;
                    default:
                        // Maneja el caso de opción inválida.
                        System.out.println("Opción inválida");
                        esperarEnter(scanner);
                }
            } catch (NumberFormatException e) {
                // Maneja el caso de error al convertir la entrada a número.
                System.out.println("Por favor ingrese un número válido");
                esperarEnter(scanner);
            }
        }
    }

    /**
     * Espera a que el usuario presione Enter antes de continuar.
     * @param scanner Objeto Scanner para la entrada de datos.
     */
    private static void esperarEnter(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine(); // Espera a que el usuario presione Enter.
    }

    /**
     * Método para limpiar la pantalla en la terminal.
     * Intenta ejecutar el comando "cls" en Windows.
     */
    private void limpiarPantalla() {
        try {
            // Ejecuta el comando "cls" para limpiar la pantalla.
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            // Maneja cualquier error al intentar limpiar la pantalla.
            System.out.println("Error al limpiar pantalla.");
        }
    }
}