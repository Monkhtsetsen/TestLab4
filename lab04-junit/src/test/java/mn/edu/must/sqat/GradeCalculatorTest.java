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
}