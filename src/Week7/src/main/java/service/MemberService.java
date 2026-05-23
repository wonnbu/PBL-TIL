package service;

import domain.Lion;
import domain.Role;
import domain.Staff;
import dto.*;
import org.springframework.stereotype.Service;
import repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Lion 생성
    public Lion createLion(LionCreateRequest request) {

        if (memberRepository.existsByName(request.getName())) {
            return null;
        }

        Lion lion = new Lion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );

        memberRepository.save(lion);

        return lion;
    }

    // Staff 생성
    public Staff createStaff(StaffCreateRequest request) {

        if (memberRepository.existsByName(request.getName())) {
            return null;
        }

        Staff staff = new Staff(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );

        memberRepository.save(staff);

        return staff;
    }

    // 단일 조회
    public Role findMember(String name) {
        return memberRepository.findByName(name);
    }

    // Lion 수정
    public Lion updateLion(String name, LionUpdateRequest request) {

        Role found = memberRepository.findByName(name);

        if (found == null) {
            return null;
        }

        Lion updatedLion = new Lion(
                found.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );

        memberRepository.updateByName(name, updatedLion);

        return updatedLion;
    }

    // Staff 수정
    public Staff updateStaff(String name, StaffUpdateRequest request) {

        Role found = memberRepository.findByName(name);

        if (found == null) {
            return null;
        }

        Staff updatedStaff = new Staff(
                found.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );

        memberRepository.updateByName(name, updatedStaff);

        return updatedStaff;
    }

    // 삭제
    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }
}