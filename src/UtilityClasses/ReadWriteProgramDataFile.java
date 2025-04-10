package UtilityClasses;

import java.io.*;

public class ReadWriteProgramDataFile {

    private static final String FILE_NAME = "./Configuration/ProgramDataFile.txt";

    public void main(String[] args) {
        // Write variable values to a text file
        writeVariableToFile("CurrentAccountRowIndex", 41);
        writeVariableToFile("CurrentContactRowIndex", 41);

        // Read variable values from the text file
        int currentAccountRowIndex = readVariableFromFile("CurrentAccountRowIndex");
        int currentContactRowIndex = readVariableFromFile("CurrentContactRowIndex");

        System.out.println("CurrentAccountRowIndex: " + currentAccountRowIndex);
        System.out.println("CurrentContactRowIndex: " + currentContactRowIndex);
    }

    public void writeVariableToFile(String variableName, int value) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            boolean variableFound = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && parts[0].equals(variableName)) {
                    fileContent.append(variableName).append("=").append(value).append("\n");
                    variableFound = true;
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            if (!variableFound) {
                fileContent.append(variableName).append("=").append(value).append("\n");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(fileContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readVariableFromFile(String variableName) {
        int value = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && parts[0].equals(variableName)) {
                    value = Integer.parseInt(parts[1]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
