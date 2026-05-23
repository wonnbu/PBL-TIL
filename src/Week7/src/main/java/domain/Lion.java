package domain;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part, "Lion");
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}