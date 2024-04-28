package com.cardio_generator.outputs;

/**
 * This is an interface for outputting patient data.
 * This interface defines a contract for output strategies that process and output patient data.
 */
public interface OutputStrategy {

    /**
     * Outputs data for a specific patient.
     *This method handles sending the data based on the given parameters.
     *
     *
     * @param patientId The ID of the patient whose data is being output.
     * @see com.cardio_generator.HealthDataSimulator#initializePatientIds
     *
     * @param timestamp The time at which the data was generated or output.
     * @param label This is the label indicating the type of data.
     * @param data This is the data to be output.
     */
    void output(int patientId, long timestamp, String label, String data);
}
