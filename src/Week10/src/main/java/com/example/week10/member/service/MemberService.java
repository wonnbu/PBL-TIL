package com.example.week10.member.service;

import com.example.week10.global.exception.DuplicateMemberException;
import com.example.week10.global.exception.MemberNotFoundException;
import com.example.week10.member.domain.Member;
import com.example.week10.member.domain.RoleType;
import com.example.week10.member.dto.LionCreateRequest;
import com.example.week10.member.dto.LionUpdateRequest;
import com.example.week10.member.dto.MemberResponse;
import com.example.week10.member.dto.StaffCreateRequest;
import com.example.week10.member.dto.StaffUpdateRequest;
import com.example.week10.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
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
        if (memberRepository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 멤버입니다.");
        }

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
        if (memberRepository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 멤버입니다.");
        }

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

    public List<MemberResponse> findByPart(String part) {
        return memberRepository.findByPart(part)
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다."));

        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다."));

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
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다."));

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
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("멤버를 찾을 수 없습니다.");
        }

        memberRepository.deleteById(id);
    }
}