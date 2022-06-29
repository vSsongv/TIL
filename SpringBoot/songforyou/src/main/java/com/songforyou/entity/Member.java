package com.songforyou.entity;

import com.songforyou.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickName;

    @Column(unique = true)
    private String email;
    private String password;

    public static Member createMember(MemberFormDto memberFromDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setNickName(memberFromDto.getNickName());
        member.setEmail(memberFromDto.getEmail());
        String password = passwordEncoder.encode(memberFromDto.getPassword());
        member.setPassword(password);
        return member;
    }
}

