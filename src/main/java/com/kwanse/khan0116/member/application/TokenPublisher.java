package com.kwanse.khan0116.member.application;

import com.kwanse.khan0116.member.domain.Role;

public interface TokenPublisher {
    String createToken(Long memberId, Role role);
}
