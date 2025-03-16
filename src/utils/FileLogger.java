package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger {

    public void writeToFile(String stringToWrite) {
        // Use Try-with resources to establish a connection
        // Create an instance of buffered writer to writhe the string to the file in append mode
        try (BufferedWriter br = new BufferedWriter(new FileWriter("library_log.txt", true));) {
            br.write(stringToWrite);
            br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV(String dataToWrite, String filePath) {
        // Use Try-with resources to establish a connection
        // Create an instance of buffered writer to writhe the string to the file in append mode
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(dataToWrite);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
