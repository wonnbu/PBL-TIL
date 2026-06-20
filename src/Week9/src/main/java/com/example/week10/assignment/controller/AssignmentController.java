package com.example.week10.assignment.controller;

import com.example.week10.assignment.dto.AssignmentCreateRequest;
import com.example.week10.assignment.dto.AssignmentResponse;
import com.example.week10.assignment.dto.AssignmentUpdateRequest;
import com.example.week10.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<?> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request
    ) {
        AssignmentResponse response = assignmentService.createAssignment(memberId, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<?> getAssignmentsByMember(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByMember(memberId));
    }

    @GetMapping("/assignments/{assignmentId}")
    public ResponseEntity<?> getAssignment(
            @PathVariable Long assignmentId
    ) {
        AssignmentResponse response = assignmentService.getAssignment(assignmentId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/assignments/{assignmentId}")
    public ResponseEntity<?> updateAssignment(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentUpdateRequest request
    ) {
        AssignmentResponse response = assignmentService.updateAssignment(assignmentId, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/assignments/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(
            @PathVariable Long assignmentId
    ) {
        boolean result = assignmentService.deleteAssignment(assignmentId);

        if (!result) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}