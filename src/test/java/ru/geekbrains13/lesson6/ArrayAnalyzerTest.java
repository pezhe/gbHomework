package ru.geekbrains13.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArrayAnalyzerTest {

    @ParameterizedTest
    @MethodSource("arraysWithFoursIn")
    void shouldReturnArrayAfterFour_whenFoursArePresent(int[] input, int[] expectedOutput) {
        Assertions.assertArrayEquals(expectedOutput, ArrayAnalyzer.getTailAfterFour(input));
    }

    private static Stream<Arguments> arraysWithFoursIn() {
        return Stream.of(
                Arguments.of(new int[] {1, 4, 2, 3}, new int[] {2, 3}),
                Arguments.of(new int[] {1, 2, 3, 4}, new int[0]),
                Arguments.of(new int[] {4, 3, 2, 1}, new int[] {3, 2, 1}),
                Arguments.of(new int[] {4, 3, 4, 1}, new int[] {1})
        );
    }

    @ParameterizedTest
    @MethodSource("arraysWithNoFoursIn")
    void shouldThrowRuntimeException_whenFoursAreAbsent(int[] input) {
        Assertions.assertThrows(RuntimeException.class,
                () -> ArrayAnalyzer.getTailAfterFour(input));
    }

    private static Stream<Arguments> arraysWithNoFoursIn() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 5}),
                Arguments.of(new int[0])
        );
    }

    @Test
    void shouldReturnTrue_whenContentIsCorrect() {
        Assertions.assertTrue(ArrayAnalyzer.consistsOfFoursAndOnes(new int[] {1, 4, 4, 1}));
    }

    @ParameterizedTest
    @MethodSource("arraysWithIncorrectContent")
    void shouldReturnFalse_whenContentIsIncorrect(int[] input) {
        Assertions.assertFalse(ArrayAnalyzer.consistsOfFoursAndOnes(input));
    }

    private static Stream<Arguments> arraysWithIncorrectContent() {
        return Stream.of(
                Arguments.of(new int[] {1, 4, 3, 4}),
                Arguments.of(new int[] {1, 1, 1, 1}),
                Arguments.of(new int[] {4, 4, 4, 4}),
                Arguments.of(new int[0])
        );
    }

}
