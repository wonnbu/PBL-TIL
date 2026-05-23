package domain;

public class Staff extends Role {
    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part, "Staff");
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}