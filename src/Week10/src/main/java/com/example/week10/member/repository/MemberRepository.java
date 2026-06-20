package com.example.week10.member.repository;

import com.example.week10.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

    boolean existsByName(String name);

    List<Member> findByPart(String part);
}