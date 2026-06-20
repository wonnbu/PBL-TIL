package com.example.week10.member.service;

import com.example.week10.member.domain.Member;
import com.example.week10.member.domain.RoleType;
import com.example.week10.member.dto.LionCreateRequest;
import com.example.week10.member.dto.LionUpdateRequest;
import com.example.week10.member.dto.MemberResponse;
import com.example.week10.member.dto.StaffCreateRequest;
import com.example.week10.member.dto.StaffUpdateRequest;
import org.springframework.stereotype.Service;
import com.example.week10.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}