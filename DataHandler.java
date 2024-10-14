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

            // Generic method to write to file
    private void writeToFile(String fileName, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}