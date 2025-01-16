package com.kwanse.khan0116.common;

import com.kwanse.khan0116.chat.infra.dto.GptRequest;
import com.kwanse.khan0116.chat.infra.dto.GptResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gptFeignClient", url = "${gpt.url}")
public interface GptFeignClient {

    @PostMapping
    GptResponse createChatCompletion(
            @RequestHeader("Authorization") String authorization,
            @RequestBody GptRequest request
    );
}
