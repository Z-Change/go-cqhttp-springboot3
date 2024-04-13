package com.example.qqbot_springboot.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.qqbot_springboot.config.EP;
import com.example.qqbot_springboot.pojo.private_respond;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class CommandMapper {
    @Resource
    private RestTemplate restTemplate;
    @Value("${botowner}")
    String botowner;
    @Value("${on_command}")
    String on_command;
    @Autowired
    private EP ep =new EP();
    private MessageMapper messageMapper = new MessageMapper();


//    public void command(JSONObject json){
//        String sender_id = json.getJSONObject("sender").getString("user_id");
//        //判断命令发送者是否是主人
//        //不是主人
//        if(Objects.equals(sender_id, botowner)){
//            //如果是群消息
//            if(Objects.equals(json.getString("message_type"), "group")) {
//                String group_id = json.getString("group_id");
//                JSONObject respond = new JSONObject();
//                respond.put("group_id",group_id);
//                respond.put("message", "[CQ:at,qq=%s]没有权限".formatted(sender_id));
//                restTemplate.postForObject(ep.getPmsg(),respond,String.class);
//                //messageMapper.send_group_msg(group_id, "[CQ:at,qq=%s]没有权限".formatted(sender_id));
//
//            }
//            //如果是私聊消息
//            else if(Objects.equals(json.getString("message_type"), "private")){
//                JSONObject respond = new JSONObject();
//                respond.put("user_id",sender_id);
//                respond.put("message", "[CQ:at,qq=%s]没有权限".formatted(sender_id));
//                restTemplate.postForObject(ep.getPmsg(),respond,String.class);
//                //messageMapper.send_private_msg(sender_id, "[CQ:at,qq=%s]没有权限".formatted(sender_id));
//            }
//            else{
//                //TODO
//            }
//        }
//        //是主人
//        else{
//            //TODO()
//            String group_id = json.getString("group_id");
//            String re_message = json.getString("raw_message");
//
//        }
//    }
}
