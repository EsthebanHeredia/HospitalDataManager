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
}




