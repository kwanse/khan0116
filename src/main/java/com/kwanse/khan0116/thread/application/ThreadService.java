package com.kwanse.khan0116.thread.application;

import com.kwanse.khan0116.thread.domain.Thread;
import com.kwanse.khan0116.thread.domain.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ThreadService {

    private final ThreadRepository threadRepository;

    @Transactional
    public Long findOrCreate(final Long memberId) {

        // 30분 이내에 또 전송하면 기존에 저장, 아니면 생성

        Thread thread = new Thread(memberId);
        return threadRepository.save(thread).getId();
    }


}
