package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //@Transactional
    public Long createMember(MemberCreateRequest request){
        Member existingMember = memberRepository.findByLoginId(request.getLoginId());
        if (existingMember != null){
            throw new RuntimeException("이미 존재하는 로그인 아이디입니다: " + request.getLoginId());
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

    //@Transactional(readOnly = true)
    public List<Member> getAllMembers(){
        return memberRepository.findAll(); //모든멤버가져오기
    }

    //@Transactional(readOnly = true)
    public Member getMemberById(Long id){
        Member member = memberRepository.findById(id); // id를 통해서 레포지토리에서 레포의 멤버를 가져오는 코드?

        if (member == null){
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        return member;
    }

    //@Transactional
    public void updateMember(Long id, MemberUpdateRequest request){
        Member member = memberRepository.findById(id);

        if(member == null){
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        member.updateInfo(request.getPassword(), request.getPhoneNumber(), request.getAddress());
    }

    //@Transactional
    public void deleteMember(Long id){
        Memeber member = memberRepository.findById(id);

        if(member == null){
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        memberRepository.deleteById(id);
    }


}
