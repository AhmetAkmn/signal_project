package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

public class FileOutputStrategy implements OutputStrategy { // changed the class name to make it camelCase format

    private String baseDirectory; //Changed to camelCase format

    public final ConcurrentHashMap<String, String> FILE_MAP = new ConcurrentHashMap<>(); //Changed to UPPER_SNAKE_CASE format

    public FileOutputStrategy(String baseDirectory) {  // changed the class name to make it camelCase format  //Changed to camelCase format

        this.baseDirectory = baseDirectory; //Changed to camelCase format
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));//Changed to camelCase format
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        String FILE_PATH = FILE_MAP.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString()); //Changed to UPPER_SNAKE_CASE format and changed to camelCase format

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) { //Changed to UPPER_SNAKE_CASE format
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + FILE_PATH + ": " + e.getMessage()); //Changed to UPPER_SNAKE_CASE format
        }
    }
}
