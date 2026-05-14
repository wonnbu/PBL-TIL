package com.likelion.springboot_week6.config;

import com.likelion.springboot_week6.repository.MemberRepository;
import com.likelion.springboot_week6.repository.MemoryMemberRepository;
import com.likelion.springboot_week6.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {
    @Bean//스프링한테 '이 객체 내가 만들었으니까 등록해줘'
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());//service가 repository의존하니까 괄호에 넣기, service가 필요한 객체들을 생성자 괄호에 넣는것
    }
}
