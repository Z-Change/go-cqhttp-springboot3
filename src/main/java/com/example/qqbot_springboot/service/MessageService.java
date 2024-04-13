package com.example.qqbot_springboot.service;

import com.alibaba.fastjson.JSONObject;


public interface MessageService {

    void msg_respond(JSONObject json);
}
