package com.kwanse.khan0116.chat.domain;

import com.kwanse.khan0116.thread.domain.Thread;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private Thread thread;

    private String question;
    private String answer;

    private Long timestamp;

    public Chat(
            final Thread thread,
            final String question,
            final String answer
    ) {
        this.thread = thread;
        this.question = question;
        this.answer = answer;
        this.timestamp = System.currentTimeMillis();
    }
}
