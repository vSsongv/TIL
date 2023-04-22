package com.SongForYou.service;

import com.SongForYou.entity.Member;
import com.SongForYou.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);

    }

    private void validateDuplicateMember(Member member) {
        Member findEmail = memberRepository.findByEmail(member.getEmail());
        Member findNickName = memberRepository.findByNickName(member.getNickName());
        if (findEmail != null || findNickName != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Long findByEmail(String name){
        Long memberId = memberRepository.findByEmail(name).getId();
        return memberId;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles("ADMIN")
                .build();
    }
}