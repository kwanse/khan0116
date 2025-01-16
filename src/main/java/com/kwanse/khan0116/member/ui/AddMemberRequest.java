package com.kwanse.khan0116.member.ui;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AddMemberRequest(

        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,

        @NotBlank
        String name
) {
}
