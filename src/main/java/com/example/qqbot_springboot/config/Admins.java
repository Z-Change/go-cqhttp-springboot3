package com.example.qqbot_springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "admins")
public class Admins {
    private String botowner;
    private List<String> admins;
    public boolean IsAdmin(String user){
        return admins.contains(user);
    }
    public void AddAdmin(String user){
        admins.add(user);
    }

}
