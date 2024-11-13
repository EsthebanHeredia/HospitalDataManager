import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String PACIENTE_CSV = "data.csv";

    private String id;
    private String nombre;
    private List<HistorialMedico> historialMedico;
    private List<CitaMedica> citasMedicas;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.historialMedico = new ArrayList<>();
        this.citasMedicas = new ArrayList<>();
    }

        public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<HistorialMedico> getHistorialMedico() { return historialMedico; }
    public void setHistorialMedico(List<HistorialMedico> historialMedico) { this.historialMedico = historialMedico; }
    public List<CitaMedica> getCitasMedicas() { return citasMedicas; }
    public void setCitasMedicas(List<CitaMedica> citasMedicas) { this.citasMedicas = citasMedicas; }

        public static List<HistorialMedico> parseHistorialMedico(String historialStr) {
        List<HistorialMedico> historial = new ArrayList<>();
        if (historialStr.isEmpty() || historialStr.equals("\"\"")) return historial;

        String[] registros = historialStr.replace("\"", "").split(";");
        for (String registro : registros) {
            if (!registro.isEmpty()) {
                String[] datos = registro.split("\\|");
                if (datos.length >= 6) {
                    try {
                        Date fecha = new Date(Long.parseLong(datos[0]));
                        historial.add(new HistorialMedico(
                            fecha, datos[1], datos[2], datos[3], datos[4], datos[5]
                        ));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear fecha: " + e.getMessage());
                    }
                }
            }
        }
        return historial;
    }

        public static List<CitaMedica> parseCitasMedicas(String citasStr) {
        List<CitaMedica> citas = new ArrayList<>();
        if (citasStr.isEmpty() || citasStr.equals("\"\"")) return citas;

        String[] registros = citasStr.replace("\"", "").split(";");
        for (String registro : registros) {
            if (!registro.isEmpty()) {
                String[] datos = registro.split("\\|");
                if (datos.length >= 2) {
                    try {
                        Date fecha = new Date(Long.parseLong(datos[0]));
                        citas.add(new CitaMedica(fecha, datos[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear fecha de cita: " + e.getMessage());
                    }
                }
            }
        }
        return citas;
    }

        public static String formatHistorialMedico(List<HistorialMedico> historialMedico) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (HistorialMedico hm : historialMedico) {
            sb.append(hm.getFecha().getTime()).append("|")
              .append(hm.getDoctor()).append("|")
              .append(hm.getSintomas()).append("|")
              .append(hm.getDiagnostico()).append("|")
              .append(hm.getReceta()).append("|")
              .append(hm.getClinica()).append(";");
        }
        sb.append("\"");
        return sb.toString();
    }

        public static String formatCitasMedicas(List<CitaMedica> citasMedicas) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        for (CitaMedica cm : citasMedicas) {
            sb.append(cm.getFecha().getTime()).append("|")
              .append(cm.getDescripcion()).append(";");
        }
        sb.append("\"");
        return sb.toString();
    }

        public static void iniciarSesion(Scanner scanner) {
        System.out.println("1. Soy un nuevo paciente");
        System.out.println("2. Ya tengo un ID de paciente");
        System.out.print("Seleccione una opción: ");
        try {
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    String id = generarIdPaciente();
                    System.out.println("Su ID de paciente es: " + id); // Imprimir la ID del nuevo paciente
                    esperarEnter(scanner); // Esperar a que el usuario presione Enter antes de continuar
                    Paciente nuevoPaciente = new Paciente(id, nombre);
                    nuevoPaciente.guardarPaciente();
                    nuevoPaciente.menuPaciente(scanner);
                    break;
                case 2:
                    System.out.print("Ingrese su ID de paciente: ");
                    String pacienteId = scanner.nextLine();
                    Paciente paciente = buscarPacientePorId(pacienteId);
                    if (paciente != null) {
                        paciente.menuPaciente(scanner);
                    } else {
                        System.out.println("Paciente no encontrado.");
                        esperarEnter(scanner);
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
                    esperarEnter(scanner);
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer del scanner
            esperarEnter(scanner);
        }
    }

     public void menuPaciente(Scanner scanner) {
        boolean running = true;
        while (running) {
            limpiarPantalla();
            System.out.println("Bienvenido, " + nombre);
            System.out.println("1. Ver historial médico");
            System.out.println("2. Agregar cita médica");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        mostrarHistorialMedico(scanner);
                        break;
                    case 2:
                        agregarCitaMedica(scanner);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                        esperarEnter(scanner);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                esperarEnter(scanner);
            }
        }
    }

        private void mostrarHistorialMedico(Scanner scanner) {
        System.out.println("Historial Médico de " + nombre + ":");
        for (HistorialMedico historial : historialMedico) {
            System.out.println(historial);
        }
        esperarEnter(scanner);
    }

        private void agregarCitaMedica(Scanner scanner) {
        System.out.print("Ingrese la fecha de la cita (formato: dd MM yyyy HH mm): ");
        String fechaStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH mm");
        try {
            Date fecha = sdf.parse(fechaStr);
            System.out.print("Ingrese la descripción de la cita: ");
            String descripcion = scanner.nextLine();
            CitaMedica cita = new CitaMedica(fecha, descripcion);
            citasMedicas.add(cita);
            guardarPaciente();
            System.out.println("Cita médica agregada.");
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
        }
        esperarEnter(scanner);
    }

        private static void esperarEnter(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
