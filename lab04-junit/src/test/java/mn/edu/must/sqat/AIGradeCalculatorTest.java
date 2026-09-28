package mn.edu.must.sqat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AIGradeCalculatorTest {

    private GradeCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new GradeCalculator();
    }

    // ================= 9 regular unit tests =================

    // 1
    @Test
    void letterGrade_returnsA_forScoreAtOrAbove90() {
        assertEquals("A", calc.letterGrade(95));
    }

    // 2
    @Test
    void letterGrade_returnsB_forScoreBetween80And89() {
        assertEquals("B", calc.letterGrade(85));
    }

    // 3
    @Test
    void letterGrade_returnsC_forScoreBetween70And79() {
        assertEquals("C", calc.letterGrade(75));
    }

    // 4
    @Test
    void letterGrade_returnsD_forScoreBetween60And69() {
        assertEquals("D", calc.letterGrade(65));
    }

    // 5
    @Test
    void letterGrade_returnsF_forScoreBelow60() {
        assertEquals("F", calc.letterGrade(30));
    }

    // 6
    @Test
    void letterGrade_throws_forNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    // 7
    @Test
    void letterGrade_throws_forScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(100.5));
    }

    // 8
    @Test
    void totalScore_returnsSumOfAllComponents() {
        assertEquals(85.0, calc.totalScore(9, 35, 8, 7, 26), 0.0001);
    }

    // 9
    @Test
    void totalScore_returnsMaximum100_whenAllComponentsAtMax() {
        assertEquals(100.0, calc.totalScore(10, 40, 10, 10, 30), 0.0001);
    }

    // ================= 2 parameterized tests =================

    // P1: letterGrade boundaries
    @ParameterizedTest(name = "letterGrade({0}) = {1}")
    @CsvSource({
            "100,   A",
            "90,    A",
            "89.99, B",
            "80,    B",
            "79.99, C",
            "70,    C",
            "69.99, D",
            "60,    D",
            "59.99, F",
            "0,     F"
    })
    void letterGrade_boundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }

    // P2: totalScore rejects any single out-of-range component
    static Stream<Arguments> invalidTotalScoreInputs() {
        return Stream.of(
                Arguments.of(-1,    20, 5, 5, 15),   // attendance < 0
                Arguments.of(10.01, 20, 5, 5, 15),   // attendance > 10
                Arguments.of(5, -1,    5, 5, 15),    // lab < 0
                Arguments.of(5, 40.01, 5, 5, 15),    // lab > 40
                Arguments.of(5, 20, -1,    5, 15),   // quiz1 < 0
                Arguments.of(5, 20, 10.01, 5, 15),   // quiz1 > 10
                Arguments.of(5, 20, 5, -1,    15),   // quiz2 < 0
                Arguments.of(5, 20, 5, 10.01, 15),   // quiz2 > 10
                Arguments.of(5, 20, 5, 5, -1),       // exam < 0
                Arguments.of(5, 20, 5, 5, 30.01)     // exam > 30
        );
    }

    @ParameterizedTest(name = "totalScore({0}, {1}, {2}, {3}, {4}) throws")
    @MethodSource("invalidTotalScoreInputs")
    void totalScore_throws_forOutOfRangeComponent(double att, double lab, double q1, double q2, double exam) {
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(att, lab, q1, q2, exam));
    }
}