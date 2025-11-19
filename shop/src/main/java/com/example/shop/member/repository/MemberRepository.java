package com.example.shop.member.repository;

import com.example.shop.member.Member;

import java.util.List;

public interface MemberRepository {

    Member findById(long id);

    Member findById(Long id);

    List<Member> findAll();
    Member findByLoginId(String loginId);
    void save(Member member);
    void deleteById(long id);

    void deleteById(Long id);
}
