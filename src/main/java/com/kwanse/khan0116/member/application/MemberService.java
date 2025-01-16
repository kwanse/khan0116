package com.kwanse.khan0116.member.application;

import com.kwanse.khan0116.member.domain.Member;
import com.kwanse.khan0116.member.domain.MemberRepository;
import com.kwanse.khan0116.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenPublisher tokenPublisher;

    @Transactional
    public Long save(
            final String email,
            final String password,
            final String name
    ) {
        Member member = new Member(name, email, password);
        return memberRepository.save(member).getId();
    }

    public String login(
            final String email,
            final String password
    ) {
        Member member = memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("이메일이나 비밀번호가 일치하지 않습니다."));

        return tokenPublisher.createToken(member.getId(), member.getRole());
    }
}
