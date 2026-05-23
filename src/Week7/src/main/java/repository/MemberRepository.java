package repository;

import domain.Role;

import java.util.List;

public interface MemberRepository {

    void save(Role member);

    List<Role> findAll();

    Role findByName(String name);

    void updateByName(String name, Role member);//기존 멤버를 새 객체로 교체

    boolean deleteByName(String name);//삭제 성공 여부 반환

    boolean existsByName(String name);//이름 중복 검사
}