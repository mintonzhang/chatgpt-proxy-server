package cn.minsin.gpt.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2023/3/3
 */
@Getter
@Setter
public class ChatRequest {

    @NotEmpty
    private  String content;
    @NotEmpty
    private  String apiKey;
}
