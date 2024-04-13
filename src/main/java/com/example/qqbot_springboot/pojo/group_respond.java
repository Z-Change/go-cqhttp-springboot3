package com.example.qqbot_springboot.pojo;

import lombok.Data;

@Data
public class group_respond {
    private String group_id;
    private String message;

    public group_respond(String group_id, String message) {
        this.group_id = group_id;
        this.message = message;
    }
}
