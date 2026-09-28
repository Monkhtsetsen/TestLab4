package mn.edu.must.sqat;

public class GradeCalculator {

    // 90+ -> A, 80-89 -> B, 70-79 -> C, 60-69 -> D, <60 -> F
    // score нь 0-100 хязгаараас гарвал IllegalArgumentException шиднэ
    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Onoo 0->100 iin hoorond baih yostoi!");
        }
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Ирц(10), лаб+бие даалт(40), сорил1(10), сорил2(10), шалгалт(30)-ийн
    // оноонуудаас нийлбэр оноог тооцно. Аль нэг нь СӨРӨГ эсвэл дээд хязгаараасаа 
    // хэтэрсэн бол IllegalArgumentException шиднэ.
    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        if (att < 0 || att > 10) {
            throw new IllegalArgumentException("Irtsiin onoo 0-10 iin hoorond baih yostoi!");
        }
        if (lab < 0 || lab > 40) {
            throw new IllegalArgumentException("Labiin onoo 0-40 iin hoorond baih yostoi!");
        }
        if (quiz1 < 0 || quiz1 > 10) {
            throw new IllegalArgumentException("Soril 1-iin onoo 0-10 iin hoorond baih yostoi!");
        }
        if (quiz2 < 0 || quiz2 > 10) {
            throw new IllegalArgumentException("Soril 2-iin onoo 0-10 iin hoorond baih yostoi!");
        }
        if (exam < 0 || exam > 30) {
            throw new IllegalArgumentException("Shalgaltiin onoo 0-30 iin hoorond baih yostoi!");
        }

        return att + lab + quiz1 + quiz2 + exam;
    }
}