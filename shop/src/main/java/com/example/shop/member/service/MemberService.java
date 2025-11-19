package com.example.shop.member.service;

import com.example.shop.member.entity.Member;
import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberService {
    @Transactional
    Long createMember(MemberCreateRequest request);

    @Transactional(readOnly = true)
    List<Member> getAllMembers();

    @Transactional(readOnly = true)
    Member getMemberById(Long id);

    @Transactional
    void updateMember(Long id, MemberUpdateRequest request);

    @Transactional
    void deleteMember(Long id);
}
