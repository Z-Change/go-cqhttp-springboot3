package com.example.qqbot_springboot.pojo;

import lombok.Data;

@Data
public class private_respond {
    private String user_id;
    private String message;

    public private_respond(String user_id, String message) {
        this.user_id = user_id;
        this.message = message;
    }
}
