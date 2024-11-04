import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que contiene el menú y las funcionalidades para gestionar pacientes, doctores y clínicas.
 */
public class Main {
    public final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "admin";
    private final DataHandler data = new DataHandler();

    /**
     * Método principal que inicia el programa.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }

    /**
     * Muestra el menú principal y gestiona la selección de opciones.
     */
    private void mainMenu()
    private void accesoAdministrador() {
    System.out.print("Ingrese usuario: ");
    System.out.print("Ingrese contraseña: ");
    if (usuario.equals("admin") && password.equals("admin")) { 
        mostrarMenuAdmin();
    }
}
    private void iniciarSesionDoctor() {
    System.out.print("Ingrese ID de doctor: ");
    String id = scanner.nextLine();
    System.out.print("Ingrese contraseña: ");
    String password = scanner.nextLine();
    if (Authenticator.validateDoctor(id, password)) {
        mostrarMenuDoctor();
    } else {
        System.out.println("Credenciales incorrectas.");
    }
}

    }
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
    
    {
        boolean running = true;
        while (running) {
            System.out.println("1. Gestionar Pacientes");
            System.out.println("2. Gestionar Doctores");
            System.out.println("3. Gestionar Clínicas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        showMenuPaciente();
                        break;
                    case 2:
                        showMenuDoctor();
                        break;
                    case 3:
                        showMenuClinica();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
                private void iniciarSesionDoctor() {
    System.out.print("Ingrese ID del doctor: ");
    if (doctorExiste(id)) { 
        mostrarMenuDoctor();
    }
}
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
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

    /**
     * Muestra el menú de opciones para gestionar pacientes y procesa las selecciones.
     */
    private void showMenuPaciente() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Buscar Paciente");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
private void agregarClinica() {
    System.out.print("Ingrese nombre de la clínica: ");
    Clinica clinica = new Clinica(nombre);
    data.writeClinica(clinica);
}
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        agregarPaciente();
                        break;
                    case 2:
                        buscarPaciente();
                        break;
                    case 3:
                        eliminarPaciente();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

    /**
     * Muestra el menú de opciones para gestionar doctores y procesa las selecciones.
     */
    private void showMenuDoctor() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Buscar Doctor");
            System.out.println("3. Eliminar Doctor");
            System.out.println("4. Ver Historial Médico de Paciente");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
private void agregarSintomasYReceta(Paciente paciente) {
    paciente.agregarSintomas("Descripción");
    paciente.agregarReceta("Receta detallada");
    data.writePacientes(pacientes);
}
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        agregarDoctor();
                        break;
                    case 2:
                        buscarDoctor();
                        break;
                    case 3:
                        eliminarDoctor();
                        break;
                    case 4:
                        verHistorialMedico();
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }
private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
    /**
     * Muestra el menú de opciones para gestionar clínicas y procesa las selecciones.
     */
    private void showMenuClinica() {
        boolean running = true;
        while (running) {
            System.out.println("1. Agregar Clínica");
            System.out.println("2. Ver Información de Clínicas");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        agregarClinica();
                        break;
                    case 2:
                        infoClinica();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

    /**
     * Busca un paciente por su ID.
     * @return true si el paciente fue encontrado, false de lo contrario.
     */
    private boolean buscarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = data.readPacientes();
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                System.out.println("Paciente encontrado: " + paciente);
                return true;
            }
        }
        System.out.println("Paciente no encontrado.");
        return false;
    }

    /**
     * Agrega un nuevo paciente con los datos proporcionados por el usuario.
     */
    private void agregarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del doctor a cargo: ");
        String doctorId = scanner.nextLine();
        System.out.print("Ingrese ID de la clínica: ");
        String clinicaId = scanner.nextLine();
        System.out.print("Ingrese historial médico del paciente: ");
        String historialMedico = scanner.nextLine();
        System.out.print("Ingrese enfermedades del paciente: ");
        String enfermedades = scanner.nextLine();
        System.out.print("Ingrese cita médica del paciente: ");
        String citaMedica = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre, doctorId, clinicaId);
        paciente.agregarHistorialMedico(historialMedico);
        paciente.agregarEnfermedad(enfermedades);
        paciente.agregarCitaMedica(citaMedica);

        data.writePaciente(paciente);
        System.out.println("Paciente agregado con éxito.");
    }

     /**
     * Elimina un paciente existente de la lista de pacientes.
     */
    private void eliminarPaciente() {
        System.out.print("Ingrese ID del paciente a eliminar: ");
        String id = scanner.nextLine();
        List<Paciente> pacientes = data.readPacientes();
        boolean eliminado = false;
        List<Paciente> updatedPacientes = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (!paciente.getId().equals(id)) {
                updatedPacientes.add(paciente);
            } else {
                eliminado = true;
            }
        }
        boolean success = data.writePacientes(updatedPacientes);
        if (eliminado && success) {
            System.out.println("Paciente eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el paciente.");
        }
    }

    /**
     * Busca un doctor por su ID.
     * @return true si el doctor fue encontrado, false de lo contrario.
     */
    private boolean buscarDoctor() {
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine();
        List<Doctor> doctores = data.readDoctores();
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                System.out.println("Doctor encontrado: " + doctor);
                return true;
            }
        }
        System.out.println("Doctor no encontrado.");
        return false;
    }

    /**
     * Agrega un nuevo doctor con los datos proporcionados por el usuario.
     */
    private void agregarDoctor() {
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID de la clínica: ");
        String clinicaId = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, clinicaId);
        data.writeDoctor(doctor);
        System.out.println("Doctor agregado con éxito.");
    }

    /**
     * Elimina un doctor existente de la lista de doctores.
     */
    private void eliminarDoctor() {
        System.out.print("Ingrese ID del doctor a eliminar: ");
        String id = scanner.nextLine();
        List<Doctor> doctores = data.readDoctores();
        boolean eliminado = false;
        List<Doctor> updatedDoctores = new ArrayList<>();
        for (Doctor doctor : doctores) {
            if (!doctor.getId().equals(id)) {
                updatedDoctores.add(doctor);
            } else {
                eliminado = true;
            }
        }
        boolean success = data.writeDoctores(updatedDoctores);
        if (eliminado && success) {
            System.out.println("Doctor eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el doctor.");
        }
    }

    /**
     * Muestra el historial médico de un paciente a cargo de un doctor específico.
     */
    private void verHistorialMedico() {
        System.out.print("Ingrese ID del doctor: ");
        String doctorId = scanner.nextLine();
        List<Doctor> doctores = data.readDoctores();
        Doctor doctor = null;
        for (Doctor doc : doctores) {
            if (doc.getId().equals(doctorId)) {
                doctor = doc;
                break;
            }
        }
        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        System.out.print("Ingrese ID del paciente: ");
        String pacienteId = scanner.nextLine();
        List<Paciente> pacientes = data.readPacientes();
        Paciente paciente = null;
        for (Paciente pac : pacientes) {
            if (pac.getId().equals(pacienteId)) {
                paciente = pac;
                break;
            }
        }
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        doctor.verHistorialMedico(paciente);
    }

    /**
     * Muestra información de todas las clínicas registradas.
     */
    private void infoClinica() {
        List<Clinica> clinicas = data.readClinicas();
        if (clinicas.isEmpty()) {
            System.out.println("No hay clínicas registradas.");
            return;
        }

        System.out.println("Información de Clínicas:");
        for (Clinica clinica : clinicas) {
            System.out.println(clinica);
        }
    }

    /**
     * Agrega una nueva clínica con los datos proporcionados por el usuario.
     */
    private void agregarClinica() {
        System.out.print("Ingrese ID de la clínica: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre de la clínica: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese dirección de la clínica: ");
        String direccion = scanner.nextLine();

        Clinica clinica = new Clinica(id, nombre, direccion);
        data.writeClinica(clinica);
        System.out.println("Clínica agregada con éxito.");
    }
}
