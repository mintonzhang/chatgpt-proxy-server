package cn.minsin.gpt.service;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2023/3/3
 */
@Getter
@Setter
public class Message {

    private final String content;
    private String role = "user";

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public Message(String content) {
        this.content = content;
    }


    public static List<Message> createSingleMessage(String content) {
        Message message = new Message("system", "你的名字叫小晶,是晶澳集团的一款智能对话机器人");
        return List.of(message, new Message(content));
    }
}
