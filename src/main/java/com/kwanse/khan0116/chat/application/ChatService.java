package com.kwanse.khan0116.chat.application;

import com.kwanse.khan0116.chat.infra.dto.GptRequest;
import com.kwanse.khan0116.chat.domain.Chat;
import com.kwanse.khan0116.chat.domain.ChatRepository;
import com.kwanse.khan0116.chat.infra.GptService;
import com.kwanse.khan0116.thread.domain.Thread;
import com.kwanse.khan0116.thread.domain.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final ThreadRepository threadRepository;

    private final GptService gptService;

    private final Map<Long, LocalDateTime> timeStore = new ConcurrentHashMap<>();

    @Transactional
    public String send(
            final GptRequest request,
            final Long memberId
    ) {
        String answer = gptService.getAnswer(request);

        Thread thread = findOrCreateThread(memberId);
        Chat chat = new Chat(thread, request.getQuestion(), answer);
        chatRepository.save(chat);

        timeStore.put(memberId, LocalDateTime.now());
        return answer;
    }


    private Thread findOrCreateThread(final Long memberId) {
        if (needNewThread(memberId)) {
            return new Thread(memberId);
        }
        List<Thread> threads = threadRepository.findAllByMemberId(memberId);
        int size = threads.size();
        return threads.get(size - 1);
    }

    private boolean needNewThread(final Long memberId) {
        if (timeStore.containsKey(memberId)) {
            LocalDateTime lastTime = timeStore.get(memberId);
            LocalDateTime criteriaTime = LocalDateTime.now()
                    .minus(30, TimeUnit.MINUTES.toChronoUnit());

            return lastTime.isBefore(criteriaTime);
        }
        return true;
    }

}
