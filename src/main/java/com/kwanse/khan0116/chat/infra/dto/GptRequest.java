package com.kwanse.khan0116.chat.infra.dto;

import lombok.Getter;

@Getter
public class GptRequest {

    public static final Double TEMPERATURE = 0.7;

    private final String question;
    private final Boolean isStreaming;
    private final String model;
    private final Double temperature;

    public GptRequest(
            final String question,
            final Boolean isStreaming,
            final String model,
            final Double temperature
    ) {
        this.question = question;
        this.isStreaming = isStreaming;
        this.model = model;
        this.temperature = temperature;
    }

    public GptRequest(
            final String question,
            final Boolean isStreaming,
            final String model
    ) {
        this.question = question;
        this.isStreaming = isStreaming;
        this.model = model;
        this.temperature = TEMPERATURE;
    }
}
