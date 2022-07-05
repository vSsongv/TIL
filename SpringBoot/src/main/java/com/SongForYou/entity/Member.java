package com.SongForYou.entity;

import com.SongForYou.constant.Role;
import com.SongForYou.dto.MemberFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
//    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFromDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setNickName(memberFromDto.getNickName());
        member.setEmail(memberFromDto.getEmail());
        String password = passwordEncoder.encode(memberFromDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

}
