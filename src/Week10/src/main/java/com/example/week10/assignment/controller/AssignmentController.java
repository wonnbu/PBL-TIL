package com.example.week10.assignment.controller;

import com.example.week10.assignment.dto.AssignmentCreateRequest;
import com.example.week10.assignment.dto.AssignmentResponse;
import com.example.week10.assignment.dto.AssignmentUpdateRequest;
import com.example.week10.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public AssignmentResponse createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request
    ) {
        return assignmentService.createAssignment(memberId, request);
    }

    @GetMapping("/members/{memberId}/assignments")
    public List<AssignmentResponse> getAssignmentsByMember(
            @PathVariable Long memberId
    ) {
        return assignmentService.getAssignmentsByMember(memberId);
    }

    @GetMapping("/assignments")
    public List<AssignmentResponse> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/assignments/search")
    public List<AssignmentResponse> searchAssignments(
            @RequestParam String keyword
    ) {
        return assignmentService.searchAssignments(keyword);
    }

    @GetMapping("/assignments/{assignmentId}")
    public AssignmentResponse getAssignment(
            @PathVariable Long assignmentId
    ) {
        return assignmentService.getAssignment(assignmentId);
    }

    @PutMapping("/assignments/{assignmentId}")
    public AssignmentResponse updateAssignment(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentUpdateRequest request
    ) {
        return assignmentService.updateAssignment(assignmentId, request);
    }

    @DeleteMapping("/assignments/{assignmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssignment(
            @PathVariable Long assignmentId
    ) {
        assignmentService.deleteAssignment(assignmentId);
    }
}