package cn.minsin.gpt.service;

import cn.minsin.gpt.request.ChatRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2023/3/3
 */
@RestController
@RequestMapping("/apis")
public class IndexController {

    @PostMapping("/chat")
    public String getSingle(@RequestBody @Valid ChatRequest chatRequest) {
        return ChatGPTService.getMessage(chatRequest.getContent(), chatRequest.getApiKey());
    }
}
