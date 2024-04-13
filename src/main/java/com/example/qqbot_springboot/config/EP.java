package com.example.qqbot_springboot.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ep")

public class EP {
    public EP() {
    }
    private String url;
    private String gmsg;
    private String pmsg;
    private String get_img;
    private String ocr;
}

