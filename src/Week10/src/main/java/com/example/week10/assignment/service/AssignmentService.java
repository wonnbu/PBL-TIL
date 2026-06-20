package com.example.week10.assignment.service;

import com.example.week10.assignment.domain.Assignment;
import com.example.week10.assignment.dto.AssignmentCreateRequest;
import com.example.week10.assignment.dto.AssignmentResponse;
import com.example.week10.assignment.dto.AssignmentUpdateRequest;
import com.example.week10.assignment.repository.AssignmentRepository;
import com.example.week10.global.exception.AssignmentNotFoundException;
import com.example.week10.global.exception.MemberNotFoundException;
import com.example.week10.member.domain.Member;
import com.example.week10.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public AssignmentResponse createAssignment(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다."));

        Assignment assignment = new Assignment(
                request.getTitle(),
                request.getDescription(),
                member
        );

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return AssignmentResponse.from(savedAssignment);
    }

    public List<AssignmentResponse> getAssignmentsByMember(Long memberId) {
        return assignmentRepository.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }
    public List<AssignmentResponse> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    public List<AssignmentResponse> searchAssignments(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    public AssignmentResponse getAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다."));

        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public AssignmentResponse updateAssignment(Long assignmentId, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다."));

        assignment.updateInfo(
                request.getTitle(),
                request.getDescription()
        );

        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public void deleteAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다."));

        assignmentRepository.delete(assignment);
    }
}