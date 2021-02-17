package com.syudy.springhello.repository;

import com.syudy.springhello.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("이우길");

        //when
        memberRepository.save(member);

        //then
        Member result = memberRepository.findById(member.getId()).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void findById() {
        
        //given
        Member member = new Member();
        member.setName("이우길");

        //when
        memberRepository.save(member);

        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember.get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findByName() {

        //given
        Member member = new Member();
        member.setName("이우길");

        //when
        memberRepository.save(member);

        //then
        Optional<Member> findMember = memberRepository.findByName(member.getName());
        assertThat(findMember.get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findAll() {

        //given
        Member member = new Member();
        member.setName("이우길");

        Member member2 = new Member();
        member2.setName("이우길2");

        //when
        memberRepository.save(member);
        memberRepository.save(member2);

        //then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList.size()).isEqualTo(2);
    }
}