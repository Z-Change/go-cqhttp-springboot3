package com.example.qqbot_springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//终结点
@Data
@Component
@ConfigurationProperties(prefix = "ep")

public class EP {
    private String url;
    private String gmsg;
    private String pmsg;
    private String get_img;
    private String ocr;

    public String getUrl() {
        return url;
    }

    public String getGmsg() {
        return url+gmsg;
    }

    public String getPmsg() {
        return url+pmsg;
    }

    public String getGet_img() {
        return url+get_img;
    }

    public String getOcr() {
        return url+ocr;
    }
}

