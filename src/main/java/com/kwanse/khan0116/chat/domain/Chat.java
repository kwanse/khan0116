package com.kwanse.khan0116.chat.domain;

public class Chat {

    private String question;
    private String answer;

    private Long timestamp;

    public Chat(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.timestamp = System.currentTimeMillis();
    }
}
