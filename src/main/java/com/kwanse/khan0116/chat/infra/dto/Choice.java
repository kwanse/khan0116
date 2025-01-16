package com.kwanse.khan0116.chat.infra.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Choice {

    private Message message;
    private String finnishReason;
    private int index;

    public Choice(Message message, String finnishReason, int index) {
        this.message = message;
        this.finnishReason = finnishReason;
        this.index = index;
    }
}
