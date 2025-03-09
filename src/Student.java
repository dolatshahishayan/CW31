public class Student {

    private String name;
    private int score;

    public Student(String name, int score) throws InvalidScoreException {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException();
        }
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) throws InvalidScoreException {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException();
        }
        this.score = score;
    }

    public String getGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Score: " + score + ", Grade: " + getGrade();
    }
}