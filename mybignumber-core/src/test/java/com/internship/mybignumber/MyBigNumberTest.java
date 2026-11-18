package com.internship.mybignumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive Unit Tests for MyBigNumber with high branch/line coverage.
 */
class MyBigNumberTest {

    private MyBigNumber myBigNumber;
    private List<String> capturedSteps;

    @BeforeEach
    void setUp() {
        capturedSteps = new ArrayList<>();
        myBigNumber = new MyBigNumber((stepNum, msg) -> capturedSteps.add("Bước " + stepNum + ": " + msg));
    }

    @Test
    @DisplayName("Test example case from requirement PDF: sum('1234', '897') = '2131'")
    void testExampleCaseFromRequirement() {
        String result = myBigNumber.sum("1234", "897");
        assertEquals("2131", result);
        assertFalse(capturedSteps.isEmpty());
        assertEquals(4, capturedSteps.size());
    }

    @ParameterizedTest(name = "Test sum({0}, {1}) = {2}")
    @CsvSource({
            "1, 2, 3",
            "9, 1, 10",
            "99, 1, 100",
            "99999, 1, 100000",
            "0, 0, 0",
            "0, 123, 123",
            "123, 0, 123",
            "555, 555, 1110",
            "897, 1234, 2131",
            "1000, 999, 1999"
    })
    void testCommonAndBoundaryCases(String num1, String num2, String expected) {
        assertEquals(expected, myBigNumber.sum(num1, num2));
    }

    @Test
    @DisplayName("Test with extremely large numbers exceeding standard primitive types")
    void testVeryLargeNumbers() {
        String num1 = "9999999999999999999999999999999999999999";
        String num2 = "1";
        String expected = "10000000000000000000000000000000000000000";
        assertEquals(expected, myBigNumber.sum(num1, num2));
    }

    @Test
    @DisplayName("Test uneven length numbers where first operand is much longer than second")
    void testUnevenLengthOperands() {
        String num1 = "10000000005";
        String num2 = "7";
        assertEquals("10000000012", myBigNumber.sum(num1, num2));
    }

    @Test
    @DisplayName("Test uneven length numbers where second operand is much longer than first")
    void testUnevenLengthOperandsReversed() {
        String num1 = "7";
        String num2 = "10000000005";
        assertEquals("10000000012", myBigNumber.sum(num1, num2));
    }

    @Test
    @DisplayName("Test null and empty string inputs for defensive robustness")
    void testDefensiveInputs() {
        assertEquals("0", myBigNumber.sum(null, null));
        assertEquals("0", myBigNumber.sum("", ""));
        assertEquals("123", myBigNumber.sum(null, "123"));
        assertEquals("456", myBigNumber.sum("456", null));
        assertEquals("789", myBigNumber.sum("   ", "789"));
        assertEquals("999", myBigNumber.sum("999", "   "));
    }

    @Test
    @DisplayName("Test default constructor without custom listener")
    void testDefaultConstructor() {
        MyBigNumber defaultInstance = new MyBigNumber();
        assertEquals("15", defaultInstance.sum("7", "8"));
    }

    @Test
    @DisplayName("Test null listener does not throw NullPointerException")
    void testNullListenerHandling() {
        MyBigNumber nullListenerInstance = new MyBigNumber(null);
        assertDoesNotThrow(() -> nullListenerInstance.sum("10", "20"));
        assertEquals("30", nullListenerInstance.sum("10", "20"));
    }
}
