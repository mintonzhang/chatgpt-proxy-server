package cn.minsin.gpt.service;

import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2023/3/3
 */
@RequiredArgsConstructor
public class ChatGPTService {

    private final static String OPENAI_CHAT = "https://api.openai.com/v1/chat/completions";

    private final static RestTemplate restTemplate = new RestTemplate();


    public static String getMessage(String ask, String apiKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(apiKey);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", "gpt-3.5-turbo");
        jsonObject.put("messages", Message.createSingleMessage(ask));

        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(jsonObject, httpHeaders);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(OPENAI_CHAT, method, httpEntity, JSONObject.class);
        return Optional.ofNullable(exchange.getBody())
                .map(e -> e.getJSONArray("choices"))
                .map(w -> w.getJSONObject(0))
                .map(r -> r.getJSONObject("message"))
                .map(r -> r.getString("content"))
                .map(w -> w.replaceFirst("\n\n", ""))
                .map(w -> w.replaceFirst("\n", ""))
                .orElse(null);
    }

    public static ResponseEntity<JSONObject> getMessage(JSONObject jsonObject, String apiKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(apiKey);


        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(jsonObject, httpHeaders);
        return restTemplate.exchange(OPENAI_CHAT, method, httpEntity, JSONObject.class);

    }
}
