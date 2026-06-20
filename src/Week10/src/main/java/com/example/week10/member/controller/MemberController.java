package com.example.week10.member.controller;

import com.example.week10.member.dto.LionCreateRequest;
import com.example.week10.member.dto.LionUpdateRequest;
import com.example.week10.member.dto.MemberResponse;
import com.example.week10.member.dto.StaffCreateRequest;
import com.example.week10.member.dto.StaffUpdateRequest;
import com.example.week10.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse createLion(@RequestBody LionCreateRequest request) {
        return memberService.createLion(request);
    }

    @PostMapping("/staffs")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse createStaff(@RequestBody StaffCreateRequest request) {
        return memberService.createStaff(request);
    }

    @GetMapping
    public List<MemberResponse> findAll(
            @RequestParam(required = false) String part) {

        if (part != null) {
            return memberService.findByPart(part);
        }

        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponse findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/lions/{id}")
    public MemberResponse updateLion(
            @PathVariable Long id,
            @RequestBody LionUpdateRequest request) {

        return memberService.updateLion(id, request);
    }

    @PutMapping("/staffs/{id}")
    public MemberResponse updateStaff(
            @PathVariable Long id,
            @RequestBody StaffUpdateRequest request) {

        return memberService.updateStaff(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}