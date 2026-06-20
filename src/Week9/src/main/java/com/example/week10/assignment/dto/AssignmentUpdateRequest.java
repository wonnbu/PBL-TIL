package com.example.week9.assignment.dto;

public class AssignmentUpdateRequest {

    private String title;
    private String description;

    protected AssignmentUpdateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}