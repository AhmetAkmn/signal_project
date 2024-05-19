package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;


/**
 * FileOutputStrategy is responsible for outputting data to files.
 * It manages file creation and writing operations for different data labels.
 */


public class FileOutputStrategy implements OutputStrategy { // changed the class name to make it UpperCamelCase format

    /**
     * Base directory where the files will be stored.
     */

    private String baseDirectory; //Changed to camelCase format

    /**
     * A ConcurrentHashMap to store the file paths associated with each label.
     */

    public final ConcurrentHashMap<String, String> FILE_MAP = new ConcurrentHashMap<>(); //Changed to UPPER_SNAKE_CASE format

    /**
     * Constructs a FileOutputStrategy with a specified base directory.
     *
     * @param baseDirectory the base directory where files will be stored
     */
    public FileOutputStrategy(String baseDirectory) {  // changed the class name to make it UpperCamelCase format  //Changed to camelCase format

        this.baseDirectory = baseDirectory; //Changed to camelCase format
    }

    /**
     * Outputs data to a file. The file is determined by the label and stored in the base directory.
     * If the directory does not exist, it will be created. Each entry of data is then appended to the respective file.
     *
     * @param patientId the ID of the patient. This identifies which patient's data is being recorded.
     * @param timestamp the timestamp of the data.  This represents the time at which the data was generated or recorded.
     * @param label     the label of the data
     * @param data      the data to be written. This is the actual content that will be written to the file.
     */

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
