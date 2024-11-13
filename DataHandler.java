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
}




