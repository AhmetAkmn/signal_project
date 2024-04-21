package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * AlertGenerator class creates alerts based on some certain conditions
 *
 * It resolves or triggers alerts for a specific patient
 */
public class AlertGenerator implements PatientDataGenerator {
    /**
     * This is a random generator used to generate probabilities for alerts.
     */
    public static final Random randomGenerator = new Random();
    /**
     * This one represents the state of alerts for each patient.
     * False means resolved, true means pressed.
     */
    private boolean[] AlertStates; // false = resolved, true = pressed

    public AlertGenerator(int patientCount) {
        AlertStates = new boolean[patientCount + 1];
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
            if (AlertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    AlertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double Lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-Lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    AlertStates[patientId] = true;
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
