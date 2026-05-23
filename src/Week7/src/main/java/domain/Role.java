package domain;

public abstract class Role {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;

    public Role(String name, String major, int generation, String part, String roleName) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    public String getRoleName() {
        return roleName;
    }
}