package com.example.shop.member.service;

import com.example.shop.common.exception.BadRequestException;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.common.message.ErrorMessage;
import com.example.shop.member.entity.Member;
import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import com.example.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long createMember(MemberCreateRequest request){
        Member existingMember = memberRepository.findByLoginId(request.getLoginId());
        if (existingMember != null){
            throw new BadRequestException(ErrorMessage.MEMBER_ALREADY_EXISTS);
        }

        Member member = new Member(
                request.getLoginId(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getAddress()
        );

        memberRepository.save(member);

        return member.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Member> getAllMembers(){
        return memberRepository.findAll(); //모든멤버가져오기
    }

    @Transactional(readOnly = true)
    @Override
    public Member getMemberById(Long id){
        Member member = memberRepository.findById(id); // id를 통해서 레포지토리에서 레포의 멤버를 가져오는 코드?

        if (member == null){
            throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        return member;
    }

    @Transactional
    @Override
    public void updateMember(Long id, MemberUpdateRequest request){
        Member member = memberRepository.findById(id);

        if(member == null){
            throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        String password = request.getPassword() != null ? request.getPassword() : member.getPassword();
        String phoneNumber = request.getPhoneNumber() != null ? request.getPhoneNumber() : member.getPhoneNumber();
        String address = request.getAddress() != null ? request.getAddress() : member.getAddress();

        member.updateInfo(password, phoneNumber, address);
    }

    @Transactional
    @Override
    public void deleteMember(Long id){
        Member member = memberRepository.findById(id);

        if(member == null){
            throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        memberRepository.deleteById(id);
    }


}
