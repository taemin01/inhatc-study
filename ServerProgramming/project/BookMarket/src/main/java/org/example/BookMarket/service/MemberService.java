package org.example.BookMarket.service;

import lombok.RequiredArgsConstructor;
import org.example.BookMarket.domain.Member;
import org.example.BookMarket.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    public Member saveMember(Member member) { // 회원 정보 저장하기
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    public Member getMemberById(String memberId) { Member member = memberRepository.findByMemberId(memberId);
        return member;
// 회원 정보 가져오기
    }
    public void deleteMember(String memberId) { Member member = memberRepository.findByMemberId(memberId);
        memberRepository.deleteById(member.getNum());
// 회원 삭제하기
    }
    private void validateDuplicateMember(Member member) { // 회원 id 중복 체크하기
        Member findMember = memberRepository.findByMemberId(member.getMemberId());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
// 인증 시 회원 정보 가져오기
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(id);
        if(member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + id);
        }
        
        String role = "ROLE_" + member.getRole().name();
        
        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .roles(role)  // ROLE_ 접두사가 포함된 역할 사용
                .build();
    }
}
