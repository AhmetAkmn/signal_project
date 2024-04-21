package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * AlertGenerator class creates alerts based on some certain conditions
 * It resolves or triggers alerts for a specific patient
 */
public class AlertGenerator implements PatientDataGenerator {
    /**
     * This is a random generator used to generate probabilities for alerts.
     */
    public static final Random RANDOM_GENERATOR = new Random(); // I changed randomGenerator to RANDOM_GENERATOR because since it is a constant it should be in UPPER_SNAKE_CASE format
    /**
     * This one represents the state of alerts for each patient.
     * False means resolved, true means pressed.
     */
    private boolean[] alertStates; // false = resolved, true = pressed  // I changed the variable name according to camelCase format

    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1]; // I changed the variable name according to camelCase format
    }
    /**
     * Generates alerts for a given patient with using random probabilities.
     *
     * @param patientId this is the ID of the patient
     * @param outputStrategy this is the strategy used to output alerts
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {  // I changed the variable name according to camelCase format
                if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve // changed the constant name to make it UPPER-SNAKE_CASE format
                    alertStates[patientId] = false;     // I changed the variable name according to camelCase format
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency  // I changed the variable name according to camelCase format
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period  // I changed the variable name -Lambda to -lambda to make it camelCase format
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p; // changed the constant to the UPPER_SNAKE_CASE format

                if (alertTriggered) {
                    alertStates[patientId] = true;  // I changed the variable name according to camelCase format
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}