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

        private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

        private static String generarIdPaciente() {
        int maxId = 1000;
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTE_CSV))) {
            String line;
            br.readLine(); // Saltar el encabezado
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length > 0 && data[0].matches("\\d+")) {
                    int id = Integer.parseInt(data[0]);
                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo IDs: " + e.getMessage());
        }
        return String.valueOf(maxId + 1);
    }

    public void guardarPaciente() {
        File inputFile = new File(PACIENTE_CSV);
        File tempFile = new File("temp_paciente.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data[0].equals(id)) {
                    found = true;
                    // Actualizar los campos necesarios
                    // HistorialMedico
                    if (data.length > 2) {
                        data[2] = formatHistorialMedico(historialMedico);
                    } else {
                        data = Arrays.copyOf(data, 3);
                        data[2] = formatHistorialMedico(historialMedico);
                    }

                    // CitasMedicas
                    if (data.length > 3) {
                        data[3] = formatCitasMedicas(citasMedicas);
                    } else {
                        data = Arrays.copyOf(data, 4);
                        data[3] = formatCitasMedicas(citasMedicas);
                    }

                    // UltimaCita
                    String ultimaCita = citasMedicas.isEmpty() ? "" :
                        "\"" + citasMedicas.get(citasMedicas.size() - 1).getFecha().getTime() + "|"
                        + citasMedicas.get(citasMedicas.size() - 1).getDescripcion() + "\"";

                    if (data.length >= 5) {
                        data[4] = ultimaCita;
                    } else {
                        data = Arrays.copyOf(data, 5);
                        data[4] = ultimaCita;
                    }

                    // Reconstruir la línea del CSV
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < data.length; i++) {
                        sb.append(data[i]);
                        if (i < data.length - 1) {
                            sb.append(",");
                        }
                    }
                    pw.println(sb.toString());
                } else {
                    pw.println(line);
                }
            }
            if (!found) {
                // Si el paciente es nuevo, agregar al archivo
                String newRecord = id + "," + nombre + "," + formatHistorialMedico(historialMedico) + ","
                        + formatCitasMedicas(citasMedicas) + ","
                        + (citasMedicas.isEmpty() ? "" : "\"" + citasMedicas.get(citasMedicas.size() - 1).getFecha().getTime()
                        + "|" + citasMedicas.get(citasMedicas.size() - 1).getDescripcion() + "\"");
                pw.println(newRecord);
            }
        } catch (IOException e) {
            System.err.println("Error guardando paciente: " + e.getMessage());
        }

        // Reemplazar el archivo original por el actualizado
        if (!inputFile.delete()) {
            System.err.println("No se pudo eliminar el archivo original");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("No se pudo renombrar el archivo temporal");
        }

            public static Paciente buscarPacientePorId(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTE_CSV))) {
            String line;
            br.readLine(); // Saltar el encabezado
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data[0].equals(id)) {
                    Paciente paciente = new Paciente(data[0], data[1]);

                    // Verificar que el índice exista antes de acceder
                    if (data.length > 2) {
                        paciente.setHistorialMedico(parseHistorialMedico(data[2]));
                    } else {
                        paciente.setHistorialMedico(new ArrayList<>());
                    }

                    if (data.length > 3) {
                        paciente.setCitasMedicas(parseCitasMedicas(data[3]));
                    } else {
                        paciente.setCitasMedicas(new ArrayList<>());
                    }

                    // 'UltimaCita' es opcional y no se utiliza directamente en este método
                    return paciente;
                }
            }
        } catch (IOException e) {
            System.err.println("Error buscando paciente: " + e.getMessage());
        }
        return null;
    }
    }
}
