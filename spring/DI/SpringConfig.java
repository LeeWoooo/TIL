package com.syudy.springhello;

import com.syudy.springhello.repository.MemberRepository;
import com.syudy.springhello.repository.MemoryMemberRepository;
import com.syudy.springhello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
