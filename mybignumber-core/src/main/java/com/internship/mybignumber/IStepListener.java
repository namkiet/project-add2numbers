package com.internship.mybignumber;

/**
 * Listener interface for receiving detailed step-by-step progress
 * of the column addition algorithm.
 */
@FunctionalInterface
public interface IStepListener {
    /**
     * Called whenever an addition step is performed.
     *
     * @param stepNumber The 1-based index of the step.
     * @param message    The human-readable description of the step.
     */
    void onStep(int stepNumber, String message);
}
