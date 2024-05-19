package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * BloodSaturationDataGenerator simulates blood saturation data for patients.
 * It is responsible for generating random fluctuations in blood saturation levels within a realistic range.
 */
public class BloodSaturationDataGenerator implements PatientDataGenerator {
    /**
     * Random number generator used to simulate data variations.
     */
    private static final Random random = new Random();
    /**
     * Array to store the last known blood saturation values for each patient.
     */
    private int[] lastSaturationValues;

    /**
     *  Constructs a BloodSaturationDataGenerator for a given number of patients.
     *
     * @param patientCount the number of patients to generate data for
     */
    public BloodSaturationDataGenerator(int patientCount) {
        lastSaturationValues = new int[patientCount + 1];

        // Initialize with baseline saturation values for each patient
        for (int i = 1; i <= patientCount; i++) {
            lastSaturationValues[i] = 95 + random.nextInt(6); // Initializes with a value between 95 and 100
        }
    }
    /**
     *  Generates blood saturation data for a given patient and outputs it using the provided strategy.
     *
     * @param patientId the ID of the patient
     * @param outputStrategy the strategy used to output the generated data
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulate blood saturation values
            int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
            int newSaturationValue = lastSaturationValues[patientId] + variation;

            // Ensure the saturation stays within a realistic and healthy range
            newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
            lastSaturationValues[patientId] = newSaturationValue;
            outputStrategy.output(patientId, System.currentTimeMillis(), "Saturation",
                    Double.toString(newSaturationValue) + "%");
        } catch (Exception e) {
            System.err.println("An error occurred while generating blood saturation data for patient " + patientId);
            e.printStackTrace(); // This will print the stack trace to help identify where the error occurred.
        }
    }
}
