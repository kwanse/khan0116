package com.kwanse.khan0116.chat.infra;

import com.kwanse.khan0116.chat.infra.dto.Choice;
import com.kwanse.khan0116.chat.infra.dto.GptRequest;
import com.kwanse.khan0116.chat.infra.dto.GptResponse;
import com.kwanse.khan0116.common.GptFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GptService {

    @Value("${gpt.key}")
    private String apiKey;
    private final GptFeignClient gptFeignClient;

    public String getAnswer(GptRequest request) {
        String authHeader = "Bearer " + apiKey;
        GptResponse gptResponse = gptFeignClient.createChatCompletion(authHeader, request);

        if (gptResponse.getChoices() != null && !gptResponse.getChoices().isEmpty()) {
            Choice firstChoice = gptResponse.getChoices().get(0);
            if (firstChoice.getMessage() != null) {
                return firstChoice.getMessage().getContent();
            }
        }
        return "";
    }
}
