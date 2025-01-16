package com.kwanse.khan0116.chat.ui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatResponse {
    private String answer;

    public ChatResponse(String answer) {
        this.answer = answer;
    }
}
