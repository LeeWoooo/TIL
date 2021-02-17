package com.syudy.springhello.service;

import com.syudy.springhello.domain.Member;
import com.syudy.springhello.repository.MemberRepository;
import com.syudy.springhello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join (Member member){
        memberValidation(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

    public void memberValidation(Member member){
        Optional<Member> findMember = memberRepository.findByName(member.getName());

        if(findMember.isPresent()){
            Member existMember = findMember.get();
            if(existMember.getName().equals(member.getName())){
                throw new IllegalStateException("해당 이름은 이미 존재합니다.");
            }//end if
        }
    }
}
