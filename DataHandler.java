import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static final String DELIMITER = ",";
    private static final String CLINICA_FILE = "clinicas.csv";
    private static final String DOCTOR_FILE = "doctores.csv";
    private static final String PACIENTE_FILE = "pacientes.csv";

    /**
     * Lee datos de un archivo CSV y devuelve una lista de arreglos de cadenas,
     * donde cada arreglo representa una fila de datos.
     * 
     * @param fileName El nombre del archivo a leer.
     * @return Una lista de registros, cada uno como un arreglo de Strings.
     */

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


    /**
     * Escribe un nuevo registro en el archivo CSV especificado.
     * 
     * @param fileName El nombre del archivo donde escribir.
     * @param data     La cadena que será escrita en el archivo.
     */
    
    private void writeToFile(String fileName, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee los datos de clínicas del archivo CSV y devuelve una lista de objetos Clinica.
     * 
     * @return Una lista de objetos Clinica.
     */
    public List<Clinica> readClinicas() {
        List<Clinica> clinicas = new ArrayList<>();
        for (String[] record : readFromFile(CLINICA_FILE)) {
            if (record.length == 3) {
                clinicas.add(new Clinica(record[0], record[1], record[2]));
            }
        }
        return clinicas;
    }

    /**
     * Escribe los datos de una clínica en el archivo CSV, verificando que el ID no exista previamente.
     * 
     * @param clinica El objeto Clinica que se escribirá en el archivo.
     */
    
    public void writeClinica(Clinica clinica) {
        if (!idExists(CLINICA_FILE, clinica.getId())) {
            writeToFile(CLINICA_FILE, clinica.toString());
        } else {
            System.out.println("ID de clínica ya existe.");
        }
    }
    /**
     * Lee los datos de pacientes del archivo CSV y devuelve una lista de objetos Paciente.
     * 
     * @return Una lista de objetos Paciente.
     */

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

     /**
     * Escribe los datos de un paciente en el archivo CSV, verificando que el ID no exista previamente.
     * 
     * @param paciente El objeto Paciente que se escribirá en el archivo.
     */
        public void writePaciente(Paciente paciente) {
        if (!idExists(PACIENTE_FILE, paciente.getId())) {
            writeToFile(PACIENTE_FILE, paciente.toString());
        } else {
            System.out.println("ID de paciente ya existe.");
        }
    }

     /**
     * Escribe una lista de pacientes en el archivo CSV, sobrescribiendo los datos existentes.
     * 
     * @param pacientes La lista de objetos Paciente a escribir.
     * @return true si la escritura fue exitosa, false en caso de error.
     */
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

    /**
     * Lee los datos de doctores del archivo CSV y devuelve una lista de objetos Doctor.
     * 
     * @return Una lista de objetos Doctor.
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
     * Escribe los datos de un doctor en el archivo CSV, verificando que el ID no exista previamente.
     * 
     * @param doctor El objeto Doctor que se escribirá en el archivo.
     */
        public void writeDoctor(Doctor doctor) {
        if (!idExists(DOCTOR_FILE, doctor.getId())) {
            writeToFile(DOCTOR_FILE, doctor.toString());
        } else {
            System.out.println("ID de doctor ya existe.");
        }
    }

    /**
     * Escribe una lista de doctores en el archivo CSV, sobrescribiendo los datos existentes.
     * 
     * @param doctores La lista de objetos Doctor a escribir.
     * @return true si la escritura fue exitosa, false en caso de error.
     */
        public Boolean writeDoctores(List<Doctor> doctores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DOCTOR_FILE))) {
            for (Doctor doctor : doctores) {
                writer.println(doctor.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verifica si un ID ya existe en el archivo CSV especificado.
     * 
     * @param fileName El nombre del archivo a verificar.
     * @param id       El ID que se busca en el archivo.
     * @return true si el ID ya existe, false si no.
     */
        private boolean idExists(String fileName, String id) {
        List<String[]> records = readFromFile(fileName);
        for (String[] record : records) {
            if (record[0].equals(id)) {
                return true;
            }
        }
        return false;
    }


}
