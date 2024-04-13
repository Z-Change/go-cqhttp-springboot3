package com.example.qqbot_springboot.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.qqbot_springboot.config.EP;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



public class MessageMapper {


//    public void send_private_msg(String user_id, String message){
//        JSONObject respond = new JSONObject();
//        respond.put("user_id",user_id);
//        respond.put("message", message);
//        restTemplate.postForObject(ep.getPmsg(),respond,String.class);
//    }
//    public void send_group_msg(String group_id, String message){
//        JSONObject respond = new JSONObject();
//        respond.put("group_id",group_id);
//        respond.put("message", message);
//        restTemplate.postForObject(ep.getGmsg(),respond,String.class);
//    }
}
