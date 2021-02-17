package com.syudy.springhello.repository;

import com.syudy.springhello.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private Map<Long,Member> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = store.get(id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        Member member = null;
        for(Long id : store.keySet()){
            if(store.get(id).getName().equals(name)){
                member = store.get(id);
            }
        }
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}
