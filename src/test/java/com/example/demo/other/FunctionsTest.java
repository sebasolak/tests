package com.example.demo.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FunctionsTest {
    private Functions underTest;

    @BeforeEach
    void setUp() {
        underTest = new Functions();
    }


    @ParameterizedTest
    @DisplayName("itShouldReturnFractional")
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
    })
    void itShouldReturnFractional(int number, int expected) {
        assertThat(underTest.fractional(number)).isEqualTo(expected);
    }

    @Test
    @DisplayName("itShouldThrowAnException")
    void itShouldThrowAnException() {
        assertThatThrownBy(() -> underTest.fractional(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Number must be bigger than -1");
    }

    @ParameterizedTest
    @DisplayName("itShouldAddTwoNumbers")
    @CsvSource({
            "3, 4, 7",
            "5, 5, 10",
            "10, 50, 60",
            "100, 99, 199",
            "1000, 2000, 3000",
    })
    void itShouldAddTwoNumbers(double a, double b, double expected) {
        assertThat(underTest.mathOperation(a, b, Double::sum)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("itShouldSubtractTwoNumbers")
    @CsvSource({
            "3, 4, -1",
            "5, 5, 0",
            "10, 50, -40",
            "100, 99, 1",
            "2000, 1000, 1000",
    })
    void itShouldSubtractTwoNumbers(double a, double b, double expected) {
        assertThat(underTest.mathOperation(a, b, (a1, b1) -> a1 - b1)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("itShouldMultiplyTwoNumbers")
    @CsvSource({
            "3, 4, 12",
            "5, 5, 25",
            "10, 50, 500",
            "100, 99, 9900",
            "2000, 1000, 2000000"
    })
    void itShouldMultiplyTwoNumbers(double a, double b, double expected) {
        assertThat(underTest.mathOperation(a, b, (a1, b1) -> a1 * b1)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("itShouldDivideTwoNumbers")
    @CsvSource({
            "3, 4, 0.75",
            "5, 5, 1",
            "10, 50, 0.2",
            "100, 99, 1.0101010101010102",
            "2000, 1000, 2"
    })
    void itShouldDivideTwoNumbers(double a, double b, double expected) {
        assertThat(underTest.mathOperation(a, b, (a1, b1) -> a1 / b1)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("itShouldReturnFibonacciOfGivenNumber")
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21",
            "9, 34",
    })
    void fibonacci(int num, int expected) {
        assertThat(underTest.fibonacci(num)).isEqualTo(expected);
    }

    @Test
    @DisplayName("fibonacciShouldThrowAnExceptionWhenNumberIsLessThen0")
    void fibonacciShouldThrowAnException() {
        assertThatThrownBy(() -> underTest.fibonacci(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Number must be at least 0");
    }


}