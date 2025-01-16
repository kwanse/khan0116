package com.kwanse.khan0116.member.application;

import com.kwanse.khan0116.member.domain.Role;
import org.springframework.stereotype.Component;

@Component
public interface TokenPublisher {
    String createToken(Long memberId, Role role);
}
