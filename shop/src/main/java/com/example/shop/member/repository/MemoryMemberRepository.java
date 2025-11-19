package com.example.shop.member.repository;

import com.example.shop.member.entity.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member findByLoginId(String loginId) {
        return store.values().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Member member) {
        if (member.getId() == null) {
            sequence++;
            member.setId(sequence);
        }
        store.put(member.getId(), member);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}

