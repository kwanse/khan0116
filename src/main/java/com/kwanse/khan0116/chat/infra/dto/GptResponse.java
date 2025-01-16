package com.kwanse.khan0116.chat.infra.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GptResponse {

    private List<Choice> choices;

    public GptResponse(List<Choice> choices) {
        this.choices = choices;
    }
}
