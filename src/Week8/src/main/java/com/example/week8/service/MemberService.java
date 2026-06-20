package com.example.week10.service;

import com.example.week10.domain.Member;
import com.example.week10.domain.RoleType;
import com.example.week10.dto.LionCreateRequest;
import com.example.week10.dto.LionUpdateRequest;
import com.example.week10.dto.MemberResponse;
import com.example.week10.dto.StaffCreateRequest;
import com.example.week10.dto.StaffUpdateRequest;
import org.springframework.stereotype.Service;
import com.example.week10.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse createLion(LionCreateRequest request) {
        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration(),
                RoleType.LION,
                request.getStudentId(),
                null
        );

        Member savedMember = memberRepository.save(member);
        return MemberResponse.from(savedMember);
    }

    public MemberResponse createStaff(StaffCreateRequest request) {
        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration(),
                RoleType.STAFF,
                null,
                request.getPosition()
        );

        Member savedMember = memberRepository.save(member);
        return MemberResponse.from(savedMember);
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        return MemberResponse.from(member);
    }

    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        member.updateInfo(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration()
        );
        member.updateStudentId(request.getStudentId());

        Member savedMember = memberRepository.save(member);
        return MemberResponse.from(savedMember);
    }

    public MemberResponse updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        member.updateInfo(
                request.getName(),
                request.getMajor(),
                request.getPart(),
                request.getGeneration()
        );
        member.updatePosition(request.getPosition());

        Member savedMember = memberRepository.save(member);
        return MemberResponse.from(savedMember);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}