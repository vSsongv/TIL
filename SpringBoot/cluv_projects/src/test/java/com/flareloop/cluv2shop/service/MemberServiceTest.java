package com.flareloop.cluv2shop.service;

import com.flareloop.cluv2shop.dto.MemberFormDto;
import com.flareloop.cluv2shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        assertSame(member.getEmail(), savedMember.getEmail());
        assertSame(member.getName(), savedMember.getName());
        assertSame(member.getAddress(), savedMember.getAddress());
        assertSame(member.getPassword(), savedMember.getPassword());
        assertSame(member.getRole(), savedMember.getRole());

    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, ()->{
            memberService.saveMember(member2);
        });

        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }

    @Test
    @DisplayName("assertNotNull 테스트")
    public void asssertNotNullTest() {
        Member member = createMember();

        assertNotNull(member);
    }

}