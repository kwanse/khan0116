package com.kwanse.khan0116.member.ui;

import com.kwanse.khan0116.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Long> add(
            @RequestBody final AddMemberRequest request
    ) {
        Long memberId = memberService.save(
                request.email(),
                request.password(),
                request.name()
        );
        return ResponseEntity.ok(memberId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody final LoginRequest request
    ) {
        String token = memberService.login(request.email(), request.password());
        return ResponseEntity.ok(token);
    }
}
