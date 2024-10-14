import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static final String DELIMITER = ",";
    private static final String CLINICA_FILE = "clinicas.csv";
    private static final String DOCTOR_FILE = "doctores.csv";
    private static final String PACIENTE_FILE = "pacientes.csv";

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

    private void writeToFile(String fileName, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Clinica> readClinicas() {
        List<Clinica> clinicas = new ArrayList<>();
        for (String[] record : readFromFile(CLINICA_FILE)) {
            if (record.length == 3) {
                clinicas.add(new Clinica(record[0], record[1], record[2]));
            }
        }
        return clinicas;
    }

    
    public void writeClinica(Clinica clinica) {
        if (!idExists(CLINICA_FILE, clinica.getId())) {
            writeToFile(CLINICA_FILE, clinica.toString());
        } else {
            System.out.println("ID de clínica ya existe.");
        }
    }

    public List<Paciente> readPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        for (String[] record : readFromFile(PACIENTE_FILE)) {
            if (record.length == 7) {
                Paciente paciente = new Paciente(record[0], record[1], record[2], record[3]);
                paciente.agregarHistorialMedico(record[4]);
                paciente.agregarEnfermedad(record[5]);
                paciente.agregarCitaMedica(record[6]);
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

        public void writePaciente(Paciente paciente) {
        if (!idExists(PACIENTE_FILE, paciente.getId())) {
            writeToFile(PACIENTE_FILE, paciente.toString());
        } else {
            System.out.println("ID de paciente ya existe.");
        }
    }

        public boolean writePacientes(List<Paciente> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PACIENTE_FILE))) {
            for (Paciente paciente : pacientes) {
                writer.println(paciente.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
