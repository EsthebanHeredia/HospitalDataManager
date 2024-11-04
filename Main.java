import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final DataHandler dataHandler = new DataHandler();

    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            limpiarPantalla();
            System.out.println("Bienvenido al Sistema de Gestión de Clínica");
            System.out.println("1. Ingresar como Paciente");
            System.out.println("2. Ingresar como Doctor");
            System.out.println("3. Ingresar como Administrador");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ingresarComoPaciente();
                    break;
                case 2:
                    ingresarComoDoctor();
                    break;
                case 3:
                    ingresarComoAdministrador();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void ingresarComoPaciente() {
        limpiarPantalla();
        System.out.println("1. Soy un nuevo paciente");
        System.out.println("2. Ya tengo un ID de paciente");
        System.out.print("Seleccione una opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                Paciente nuevoPaciente = new Paciente(dataHandler.generarIdPaciente(), obtenerNombrePaciente());
                dataHandler.writePaciente(nuevoPaciente);
                System.out.println("Paciente registrado con éxito. Su ID es: " + nuevoPaciente.getId());
                break;
            case 2:
                System.out.print("Ingrese su ID de paciente: ");
                String idPaciente = scanner.nextLine();
                Paciente paciente = dataHandler.buscarPacientePorId(idPaciente);
                if (paciente != null) {
                    paciente.agregarCitaMedica("Nueva cita");
                    dataHandler.writePaciente(paciente);
                    System.out.println("Cita agregada con éxito.");
                } else {
                    System.out.println("ID no encontrado, intente otra vez.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void ingresarComoDoctor() {
        limpiarPantalla();
        System.out.print("Ingrese su usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Doctor doctor = dataHandler.buscarDoctorPorCredenciales(usuario, contrasena);
        if (doctor != null) {
            doctorMenu(doctor);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private void ingresarComoAdministrador() {
        limpiarPantalla();
        System.out.print("Ingrese su usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        if (dataHandler.esAdministrador(usuario, contrasena)) {
            administradorMenu();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private void doctorMenu(Doctor doctor) {
        boolean running = true;
        while (running) {
            limpiarPantalla();
            System.out.println("Bienvenido Dr. " + doctor.getNombre());
            System.out.println("1. Ver Historial de Paciente");
            System.out.println("2. Agregar Información a Paciente");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    verHistorialPaciente();
                    break;
                case 2:
                    agregarInformacionAPaciente();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void administradorMenu() {
        boolean running = true;
        while (running) {
            limpiarPantalla();
            System.out.println("Bienvenido Administrador");
            System.out.println("1. Agregar Clínica");
            System.out.println("2. Agregar Doctor");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarClinica();
                    break;
                case 2:
                    agregarDoctor();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void verHistorialPaciente() {
        System.out.print("Ingrese el ID del paciente: ");
        String idPaciente = scanner.nextLine();
        Paciente paciente = dataHandler.buscarPacientePorId(idPaciente);
        if (paciente != null) {
            System.out.println("Historial Médico: " + paciente.getHistorialMedico());
        } else {
            System.out.println("ID no encontrado, intente nuevamente.");
        }
    }

    private void agregarInformacionAPaciente() {
        System.out.print("Ingrese el ID del paciente: ");
        String idPaciente = scanner.nextLine();
        Paciente paciente = dataHandler.buscarPacientePorId(idPaciente);
        if (paciente != null) {
            System.out.print("Ingrese síntomas o receta: ");
            String info = scanner.nextLine();
            paciente.agregarHistorialMedico(info);
            dataHandler.writePaciente(paciente);
            System.out.println("Información agregada al historial.");
        } else {
            System.out.println("ID no encontrado, intente nuevamente.");
        }
    }

    private void agregarClinica() {
        System.out.print("Ingrese nombre de la clínica: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese dirección de la clínica: ");
        String direccion = scanner.nextLine();
        Clinica clinica = new Clinica(dataHandler.generarIdClinica(), nombre, direccion);
        dataHandler.writeClinica(clinica);
        System.out.println("Clínica agregada con éxito.");
    }

    private void agregarDoctor() {
        System.out.print("Ingrese nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese especialidad del doctor: ");
        String especialidad = scanner.nextLine();
        Doctor doctor = new Doctor(dataHandler.generarIdDoctor(), nombre, especialidad);
        dataHandler.writeDoctor(doctor);
        System.out.println("Doctor agregado con éxito.");
    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String obtenerNombrePaciente() {
        System.out.print("Ingrese nombre del paciente: ");
        return scanner.nextLine();
    }
}

