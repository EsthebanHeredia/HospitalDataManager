import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static final String DELIMITER = ",";
    private static final String CLINICA_FILE = "clinicas.csv";
    private static final String DOCTOR_FILE = "doctores.csv";
    private static final String PACIENTE_FILE = "pacientes.csv";

    // Generic method to read from file
    private List<String[]> readFromFile(String fileName) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                records.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Generic method to write to file
    private void writeToFile(String fileName, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee y retorna una lista de clínicas desde el archivo CSV.
     *
     * @return Lista de objetos Clinica leída desde el archivo.
     */
    public List<Clinica> readClinicas() {
        List<Clinica> clinicas = new ArrayList<>();
        for (String[] record : readFromFile(CLINICA_FILE)) {
            if (record.length == 2) {
                clinicas.add(new Clinica(record[0], record[1]));
            }
        }
        return clinicas;
    }
    /**
     * Escribe una nueva clínica al archivo CSV.
     *
     * @param clinica Objeto Clinica a escribir en el archivo.
     */
    public void writeClinica(Clinica clinica) {
        writeToFile(CLINICA_FILE, clinica.toString());
    }

    /**
    * Lee y retorna una lista de pacientes desde el archivo CSV.
    *
    * @return Lista de objetos Paciente leída desde el archivo.
    */
    public List<Paciente> readPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        for (String[] record : readFromFile(PACIENTE_FILE)) {
            if (record.length == 4) {
                pacientes.add(new Paciente(record[0], record[1], record[2], record[3]));
            }
        }
        return pacientes;
    }
    /**
     * Escribe un nuevo paciente al archivo CSV.
     *
     * @param paciente Objeto Paciente a escribir en el archivo.
     */
    public void writePaciente(Paciente paciente) {
        writeToFile(PACIENTE_FILE, paciente.toString());
    }
    /**
     * Quitar y editar pacientes al archivo CSV.
     *
     * The method is a boolean, because if there is an error, this way in Main it can alert the user.
     * 
     * @param pacientes Objeto paciente a escribir en el archivo.
     */
    public boolean writePacientes(List<Paciente> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PACIENTE_FILE))) {
            for (Paciente paciente : pacientes) {
                String pacienteData = String.join(",", paciente.getId(), paciente.getNombre(), paciente.getClinica());
                writer.println(pacienteData);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } return false;
    }


    /**
     * Lee y retorna una lista de doctores desde el archivo CSV.
     *
     * @return Lista de objetos Doctor leída desde el archivo.
     */
    public List<Doctor> readDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        for (String[] record : readFromFile(DOCTOR_FILE)) {
            if (record.length == 3) {
                doctores.add(new Doctor(record[0], record[1], record[2]));
            }
        }
        return doctores;
    }
    /**
     * Escribe un nuevo doctor al archivo CSV.
     *
     * @param doctor Objeto Doctor a escribir en el archivo.
     */
    public void writeDoctor(Doctor doctor) {
        writeToFile(DOCTOR_FILE, doctor.toString());
    }


    /**
     * Escribe un nuevo doctor al archivo CSV.
     *
     * The method is a boolean, because if there is an error, this way in Main it can alert the user.
     * 
     * @param doctor Objeto Doctor a escribir en el archivo.
     */
    public Boolean writeDoctores(List<Doctor> doctores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DOCTOR_FILE))) {
            for (Doctor doctor : doctores) {
                // Manually write the fields of each doctor in a CSV format
                String doctorData = String.join(",", doctor.getId(), doctor.getNombre(), doctor.getClinica());
                writer.println(doctorData);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } return false;
    }
    
    


}
