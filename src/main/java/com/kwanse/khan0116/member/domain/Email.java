package com.kwanse.khan0116.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$\n";

    @Column(name = "email")
    private String value;

    public Email(String value) {
        if (value.matches(EMAIL_REGEX)) {
            this.value = value;
        }
        throw new IllegalArgumentException("이메일 형식이 잘못되었습니다.");
    }
}
