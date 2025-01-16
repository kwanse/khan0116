package com.kwanse.khan0116.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Email email;
    @Embedded
    private Password password;

    private Role role;
    private Long timestamp;

    public Member(
            final String name,
            final String email,
            final String password
    ) {
        this.name = name;
        this.email = new Email(email);
        this.password = new Password(password);

        this.role = Role.MEMBER;
        this.timestamp = System.currentTimeMillis();
    }
}
