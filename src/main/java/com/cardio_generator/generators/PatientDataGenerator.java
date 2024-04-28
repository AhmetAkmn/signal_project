package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * An interface for generating patient data.
 * Classes implementing this interface should generate data for specific patients,
 * which can be output using various output strategies defined by the OutputStrategy interface.
 */
public interface PatientDataGenerator {
    /**
     * This method generates data for a specific patient and outputs it using the provided output strategy.
     * It is important to see taht the exact behavior of the output depends on the implementation of OutputStrategy.
     *
     * @param patientId The ID of the patient for whom data is generated.
     * @param outputStrategy The strategy used to handle the generated data. For more details please check OutputStrategy.
     * @see com.cardio_generator.outputs.OutputStrategy
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
