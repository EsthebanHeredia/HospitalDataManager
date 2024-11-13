import java.io.*;
import java.util.*;

public class DataHandler {
    private static final String PACIENTE_CSV = "data.csv";
    private static final String DOCTOR_CSV = "doctores.csv";
    private static final String ADMIN_CSV = "administradores.csv";
    private static final String CLINICA_CSV = "clinicas.csv";

        public void verPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTE_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0 && !data[0].equalsIgnoreCase("ID")) {
                    System.out.println("ID: " + data[0] + ", Nombre: " + data[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo pacientes: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en formato de archivo de pacientes: " + e.getMessage());
        }
    }

        public void verDoctores() {
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0 && !data[0].equalsIgnoreCase("ID")) {
                    System.out.println("ID: " + data[0] + ", Nombre: " + data[1] + 
                                     ", Especialidad: " + data[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo doctores: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en formato de archivo de doctores: " + e.getMessage());
        }
    }

        public void verClinicas() {
        try (BufferedReader br = new BufferedReader(new FileReader(CLINICA_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0 && !data[0].equalsIgnoreCase("ID")) {
                    System.out.println("ID: " + data[0] + ", Nombre: " + data[1] + 
                                     ", Dirección: " + data[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo clínicas: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en formato de archivo de clínicas: " + e.getMessage());
        }
    }
        public Paciente buscarPacientePorId(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTE_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0 && data[0].equals(id)) {
                    Paciente paciente = new Paciente(data[0], data[1]);
                    if (data.length > 2) {
                        paciente.setHistorialMedico(Paciente.parseHistorialMedico(data[2]));
                    }
                    if (data.length > 3) {
                        paciente.setCitasMedicas(Paciente.parseCitasMedicas(data[3]));
                    }
                    return paciente;
                }
            }
        } catch (IOException e) {
            System.err.println("Error buscando paciente: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en formato de archivo de pacientes: " + e.getMessage());
        }
        return null;
    }

        public Doctor buscarDoctorPorCredenciales(String nombre, String contrasena) {
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0 && data[1].equals(nombre) && data[3].equals(contrasena)) {
                    return new Doctor(data[0], data[1], data[2], data[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error buscando doctor: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en formato de archivo de doctores: " + e.getMessage());
        }
        return null;
    }

        public Administrador buscarAdministradorPorCredenciales(String nombre, String contrasena) {
        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0 && data[1].equals(nombre) && data[2].equals(contrasena)) {
                    return new Administrador(data[0], data[1], data[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error buscando administrador: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en formato de archivo de administradores: " + e.getMessage());
        }
        return null;
    }

        public void agregarPaciente(Paciente paciente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PACIENTE_CSV, true))) {
            String historialStr = Paciente.formatHistorialMedico(paciente.getHistorialMedico());
            String citasStr = Paciente.formatCitasMedicas(paciente.getCitasMedicas());
            String record = String.format("%s,%s,\"%s\",\"%s\",\"%s\"",
                    paciente.getId(), 
                    paciente.getNombre(),
                    historialStr,
                    citasStr,
                    ""); // UltimaCita inicialmente vacía
            bw.write(record);
            bw.newLine();
            System.out.println("Paciente agregado exitosamente. ID asignado: " + paciente.getId());
            esperarEnter();
        } catch (IOException e) {
            System.err.println("Error guardando paciente: " + e.getMessage());
        }
    }

        public void guardarPaciente(Paciente paciente) {
        File inputFile = new File(PACIENTE_CSV);
        File tempFile = new File("temp_paciente.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);

                if (data.length > 0 && data[0].equals(paciente.getId())) {
                    found = true;
                    // Actualizar los campos necesarios
                    String historialStr = Paciente.formatHistorialMedico(paciente.getHistorialMedico());
                    String citasStr = Paciente.formatCitasMedicas(paciente.getCitasMedicas());
                    String ultimaCita = paciente.getCitasMedicas().isEmpty() ? "" :
                        paciente.getCitasMedicas().get(paciente.getCitasMedicas().size() - 1).toString();

                    String updatedRecord = String.format("%s,%s,\"%s\",\"%s\",\"%s\"",
                            paciente.getId(),
                            paciente.getNombre(),
                            historialStr,
                            citasStr,
                            ultimaCita);
                    pw.println(updatedRecord);
                } else {
                    pw.println(line);
                }
            }
            if (!found) {
                // Si el paciente es nuevo, agregar al archivo
                String historialStr = Paciente.formatHistorialMedico(paciente.getHistorialMedico());
                String citasStr = Paciente.formatCitasMedicas(paciente.getCitasMedicas());
                String ultimaCita = paciente.getCitasMedicas().isEmpty() ? "" :
                        paciente.getCitasMedicas().get(paciente.getCitasMedicas().size() - 1).toString();
                String newRecord = String.format("%s,%s,\"%s\",\"%s\",\"%s\"",
                        paciente.getId(),
                        paciente.getNombre(),
                        historialStr,
                        citasStr,
                        ultimaCita);
                pw.println(newRecord);
            }
        } catch (IOException e) {
            System.err.println("Error guardando paciente: " + e.getMessage());
            return;
        }

        // Reemplazar el archivo original por el actualizado
        if (!inputFile.delete()) {
            System.err.println("No se pudo eliminar el archivo original");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("No se pudo renombrar el archivo temporal");
        }
    }

        public void agregarDoctor(Doctor doctor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DOCTOR_CSV, true))) {
            String record = String.format("%s,%s,%s,\"%s\"",
                    doctor.getId(),
                    doctor.getNombre(),
                    doctor.getEspecialidad(),
                    doctor.getContrasena());
            bw.write(record);
            bw.newLine();
            System.out.println("Doctor agregado exitosamente. ID asignado: " + doctor.getId());
            esperarEnter();
        } catch (IOException e) {
            System.err.println("Error guardando doctor: " + e.getMessage());
        }
    }

        public void agregarClinica(Clinica clinica) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLINICA_CSV, true))) {
            String record = String.format("%s,%s,%s",
                    clinica.getId(),
                    clinica.getNombre(),
                    clinica.getDireccion());
            bw.write(record);
            bw.newLine();
            System.out.println("Clínica agregada exitosamente. ID asignado: " + clinica.getId());
            esperarEnter();
        } catch (IOException e) {
            System.err.println("Error guardando clínica: " + e.getMessage());
        }
    }

        public void agregarAdministrador(Administrador admin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_CSV, true))) {
            String record = String.format("%s,%s,\"%s\"",
                    admin.getId(),
                    admin.getNombre(),
                    admin.getContrasena());
            bw.write(record);
            bw.newLine();
            System.out.println("Administrador agregado exitosamente. ID asignado: " + admin.getId());
            esperarEnter();
        } catch (IOException e) {
            System.err.println("Error guardando administrador: " + e.getMessage());
        }
    }

        // Métodos para obtener todas las entidades
    public List<Doctor> obtenerTodosDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_CSV))) {
            String line;
            br.readLine(); // Saltar encabezado
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length >= 4) {
                    doctores.add(new Doctor(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo doctores: " + e.getMessage());
        }
        return doctores;
    }

        public List<Clinica> obtenerTodasClinicas() {
        List<Clinica> clinicas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CLINICA_CSV))) {
            String line;
            br.readLine(); // Saltar encabezado
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length >= 3) {
                    clinicas.add(new Clinica(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo clínicas: " + e.getMessage());
        }
        return clinicas;
    }

        public List<Paciente> obtenerTodosPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTE_CSV))) {
            String line;
            br.readLine(); // Saltar encabezado
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length >= 2) {
                    Paciente paciente = new Paciente(data[0], data[1]);
                    if (data.length > 2) {
                        paciente.setHistorialMedico(Paciente.parseHistorialMedico(data[2]));
                    }
                    if (data.length > 3) {
                        paciente.setCitasMedicas(Paciente.parseCitasMedicas(data[3]));
                    }
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo pacientes: " + e.getMessage());
        }
        return pacientes;
    }

        public List<Administrador> obtenerTodosAdministradores() {
        List<Administrador> administradores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_CSV))) {
            String line;
            br.readLine(); // Saltar encabezado
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length >= 3) {
                    administradores.add(new Administrador(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo administradores: " + e.getMessage());
        }
        return administradores;
    }

        // Métodos auxiliares para agregar entidades con Scanner y asignación automática de IDs
    public void agregarPacienteConScanner(Scanner scanner) {
        try {
            String id = generarNextId(PACIENTE_CSV);
            System.out.println("ID asignado al nuevo paciente: " + id);

            System.out.print("Ingrese nombre del paciente: ");
            String nombre = scanner.nextLine().trim();

            Paciente paciente = new Paciente(id, nombre);
            agregarPaciente(paciente);
        } catch (Exception e) {
            System.err.println("Error agregando paciente: " + e.getMessage());
        }
    }

        public void agregarDoctorConScanner(Scanner scanner) {
        try {
            String id = generarNextId(DOCTOR_CSV);
            System.out.println("ID asignado al nuevo doctor: " + id);

            System.out.print("Ingrese nombre del doctor: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Ingrese especialidad: ");
            String especialidad = scanner.nextLine().trim();

            System.out.print("Ingrese contraseña: ");
            String contrasena = scanner.nextLine().trim();

            Doctor doctor = new Doctor(id, nombre, especialidad, contrasena);
            agregarDoctor(doctor);
        } catch (Exception e) {
            System.err.println("Error agregando doctor: " + e.getMessage());
        }
    }

        public void agregarClinicaConScanner(Scanner scanner) {
        try {
            String id = generarNextId(CLINICA_CSV);
            System.out.println("ID asignado a la nueva clínica: " + id);

            System.out.print("Ingrese nombre de la clínica: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Ingrese dirección: ");
            String direccion = scanner.nextLine().trim();

            Clinica clinica = new Clinica(id, nombre, direccion);
            agregarClinica(clinica);
        } catch (Exception e) {
            System.err.println("Error agregando clínica: " + e.getMessage());
        }
    }

    
    public void agregarAdministradorConScanner(Scanner scanner) {
        try {
            String id = generarNextId(ADMIN_CSV);
            System.out.println("ID asignado al nuevo administrador: " + id);

            System.out.print("Ingrese nombre del administrador: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Ingrese contraseña: ");
            String contrasena = scanner.nextLine().trim();

            Administrador admin = new Administrador(id, nombre, contrasena);
            agregarAdministrador(admin);
        } catch (Exception e) {
            System.err.println("Error agregando administrador: " + e.getMessage());
        }
    }

        // Método para generar el próximo ID único
    private String generarNextId(String csvFile) {
        long nextId = 1000;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data.length > 0) {
                    try {
                        long id = Long.parseLong(data[0]);
                        if (id >= nextId) {
                            nextId = id + 1;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar IDs que no sean numéricos
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error generando ID para " + csvFile + ": " + e.getMessage());
        }

        return String.valueOf(nextId);
    }

        // Método para esperar que el usuario presione Enter
    private void esperarEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

        // Método para parsear una línea CSV teniendo en cuenta comillas
    private String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim());

        return tokens.toArray(new String[0]);
    }

        // Método auxiliar para limpiar la consola (funciona en Windows y Unix)
    private void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            // Ignorar errores
        }
    }
}




