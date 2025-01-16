package com.kwanse.khan0116.thread.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(columnList = "member_id"))
@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long firstChatId;
    private Long lastChatId;

    private boolean isLatest;

    public Thread(final Long memberId) {
        this.memberId = memberId;
    }

    public Thread(final Long memberId, final Long firstChatId) {
        this.memberId = memberId;
        this.firstChatId = firstChatId;
    }

    public void setLastChatId(final Long lastChatId) {
        this.lastChatId = lastChatId;
    }
}
