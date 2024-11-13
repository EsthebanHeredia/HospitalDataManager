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
}




