package com.likelion.springboot_week6;

import com.likelion.springboot_week6.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootWeek6Application {
	public static void main(String[] args) {
		ApplicationContext context =//스프링이 만든 모든 객체들 있는 컨테이너에서 context라는 변수에 담기
				SpringApplication.run(SpringbootWeek6Application.class, args);
		MemberService memberService =//context에서 MemberService라는 타입의 객체(Bean) 꺼내기
				context.getBean(MemberService.class);
		System.out.println("memberService = " + memberService);
	}
}
