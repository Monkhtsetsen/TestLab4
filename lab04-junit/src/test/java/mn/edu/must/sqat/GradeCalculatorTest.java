package mn.edu.must.sqat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GradeCalculatorTest{
    @Test
    @DisplayName("90 onoo yag A dun baih yostoi")
    void ninetyIsExactlyA(){
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(90.0);
        assertEquals("A", grade);
    }
    @Test
    @DisplayName("89.99 onoo B dun baih yostoi")
    void boundaryBGrade() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(89.99);
        assertEquals("B", grade);
    }
    @Test
    @DisplayName("Buruu onoo oruulbal Exception shideh yostoi")
    void invalidScoreThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(100.1));
    }
    @Test
    @DisplayName("Niit onoo zuv tootsoolol (10, 40, 10, 10, 30 -> 100)")
    void validTotalScore() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total, 0.001);
    }
    @Test
    @DisplayName("Niit onoo surug utga oruulahad Exception shideh yostoi")
    void negativeTotalScoreInputThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-1, 40, 10, 10, 30));
    }
    @Test
    @DisplayName("Niit onoond hyazgaar hetersen utga oruulahad Exception shideh yostoi")
    void exceedingTotalScoreInputThrowsException() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 41, 10, 10, 30));
    }
    @Test
    @DislplayName("60 onoo yag D dun baih yostoi")
    void sixtyIsExactlyD(){
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(60.0);
        assertEquals("D", grade);   
        }
    @Test
    @DisplayName("59.99 onoo F dun baih yostoi")
    void belowSixtyIsF(){
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(59.99);
        assertEquals("F", grade);
    }
    @Test
    @DisplayName("100 onoo yag A baih yostoi")
    void hundredIsExactlyA(){
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(100.0);
        assertEquals("A", grade);
    }
    @ParameterizedTest
    @CsvSource({"95,A", "90,A", "89.99,B", "80,B", "70,C", "60,D", "59.99,F", "0,F"})
     @DisplayName("Olon onoonii usgen dung shalgah")
    void letterGradeBoundaries(double score, String expected) {
    assertEquals(expected, new GradeCalculator().letterGrade(score));
    }
    @ParameterizedTest
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "10, 30, 10, 10, 20, 80",
            "8, 32, 9, 9, 25, 83",
            "5, 20, 5, 5, 15, 50",
            "0, 0, 0, 0, 0, 0",
            "10, 40, 10, 10, 0, 70"
    })
    @DisplayName("Olon turliin niilber onoog shalgah")
    void totalScoreMultipleInputs( double att, double lab, double quiz1, double quiz2, double exam, double expected) {
    GradeCalculator calc = new GradeCalculator();
    double actual = calc.totalScore(att, lab, quiz1, quiz2, exam);
    assertEquals(expected, actual, 0.001);
    }
}