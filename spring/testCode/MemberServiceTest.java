package com.syudy.springhello.service;

import com.syudy.springhello.domain.Member;
import com.syudy.springhello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    private MemberService memberService;
    private MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("이우길");

        Member member2 = new Member();
        member2.setName("이우길");

        //when
        memberService.join(member);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            //then
            assertThat(e.getMessage()).isEqualTo("해당 이름은 이미 존재합니다.");
        }
    }

    @Test
    void findAll() {
        //given
        Member member = new Member();
        member.setName("이우길");

        Member member2 = new Member();
        member2.setName("이우길2");

        //when
        memberService.join(member);
        memberService.join(member2);
        List<Member> memberList = memberService.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        //given
        Member member = new Member();
        member.setName("이우길");
        
        //when
        memberService.join(member);
        Optional<Member> findMember = memberService.findOne(member.getId());

        //then
        assertThat(findMember.get().getName()).isEqualTo(member.getName());
    }
    
}