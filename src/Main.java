import java.util.*;


private Map<String, Student> studentRecords = new HashMap<>();

public void addStudent(String name, int score) {
    if (studentRecords.containsKey(name)) {
        throw new StudentAlreadyExistsException();
    }
    studentRecords.put(name, new Student(name, score));
}

public void updateScore(String name, int newScore) {
    Student student = studentRecords.get(name);
    if (student == null) {
        throw new NoElementFoundException();
    }
    student.setScore(newScore);
}

public void removeStudent(String name) {
    if (studentRecords.remove(name) == null) {
        throw new NoElementFoundException();
    }
    studentRecords.remove(name);
}

public void displayStudents() {
    if (studentRecords.isEmpty()) {
        System.out.println("No students to display.");
        return;
    }
    for (Student student : studentRecords.values()) {
        System.out.println(student);
    }
}

public double classAverage() {
    if (studentRecords.isEmpty()) {
        return 0.0;
    }
    return studentRecords.values().stream()
            .mapToInt(Student::getScore)
            .average()
            .orElse(0.0);
}

public List<Student> findTopStudents() {
    if (studentRecords.isEmpty()) {
        return Collections.emptyList();
    }
    int maxScore = studentRecords.values().stream()
            .mapToInt(Student::getScore)
            .max()
            .orElse(0);
    List<Student> topStudents = new ArrayList<>();
    for (Student student : studentRecords.values()) {
        if (student.getScore() == maxScore) {
            topStudents.add(student);
        }
    }
    return topStudents;
}

public List<Student> filterByGrade(String grade) {
    List<Student> filteredStudents = new ArrayList<>();
    for (Student student : studentRecords.values()) {
        if (student.getGrade().equalsIgnoreCase(grade)) {
            filteredStudents.add(student);
        }
    }
    return filteredStudents;
}

void main() {
    try {
        addStudent("Shayan", 95);
        addStudent("Ali", 85);
        addStudent("MohammadHasan", 75);

        updateScore("Shayan", 90);

        displayStudents();
        System.out.println("Class Average: " + classAverage());
        System.out.println("Top Students: " + findTopStudents());
        System.out.println("Students with Grade A: " + filterByGrade("A"));

        removeStudent("Ali");
        displayStudents();
    } catch (InvalidScoreException e) {
        System.out.println(e.getMessage());
    }
}
