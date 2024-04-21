package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    public static final Random RANDOM_GENERATOR = new Random(); // Ichanged randomGenerator to RANDOM_GENERATOR because since it is a constant it should be in UPPER_SNAKE_CASE format
    private boolean[] alertStates; // false = resolved, true = pressed  // I changed the variable name according to camelCase format

    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1]; // I changed the variable name according to camelCase format
    }

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
