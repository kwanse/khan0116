package com.kwanse.khan0116.chat.ui;

import com.kwanse.khan0116.chat.application.ChatService;
import com.kwanse.khan0116.chat.infra.dto.GptRequest;
import feign.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final ChatService chatService;

    @Value("${jwt.secret_key}")
    private String secret;

    @PostMapping
    public ResponseEntity<String> sendQuestion(
            final GptRequest request,
            @RequestHeader("Authorization") final String header
    ) {
        Long memberId = findMemberId(header);
        String answer = chatService.send(request, memberId);
        return ResponseEntity.ok(answer);
    }

    private Long findMemberId(final String header) {
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("memberId", Long.class);
        }
        throw new IllegalArgumentException("유효하지 않습니다.");
    }
}
