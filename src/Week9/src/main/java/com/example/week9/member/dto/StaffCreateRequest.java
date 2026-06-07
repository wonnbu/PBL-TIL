package com.example.week9.member.dto;

public class StaffCreateRequest {
    private String name;
    private String major;
    private String part;
    private int generation;
    private String position;

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getPart() {
        return part;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPosition() {
        return position;
    }
}
