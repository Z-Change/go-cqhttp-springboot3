package com.example.qqbot_springboot.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.qqbot_springboot.config.Admins;
import com.example.qqbot_springboot.config.EP;
import com.example.qqbot_springboot.service.MessageService;
import com.example.qqbot_springboot.utils.Utils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.*;

@Service
public class MessageServiceimpl implements MessageService {
    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private EP ep = new EP();
    @Autowired
    private Admins admins = new Admins();
    private Utils utils = new Utils();

    @Value("${on_command}")
    String on_command;

    private Map<String, String> cmd_map = Map.of("h", "帮助", "repeat", "你说啥", "add", "添加管理员");
    private boolean RepeatMode = false;

    @Override
    public void msg_respond(JSONObject json) {

        System.out.println(json);
        //判断是否有命令符
        //无命令符
        if (!json.getString("raw_message").startsWith(on_command)) {
            //TODO()
            send_message(admins.getBotowner(), json);
            if (RepeatMode) {
                send_message(json, json.getString("message"));
            } else {
                YYDZ(json);
                setu(json);
            }
        }
        //有命令符
        else {
            String user_id = json.getString("user_id");
            //判断命令发送者是否是主人
            //不是主人
            if (!admins.IsAdmin(user_id)) {
                permission_denied(json);
            }
            //是主人
            else {
                command(json);
            }
        }

    }

    private void command(JSONObject json) {
        String command = json.getString("raw_message").substring(on_command.length());
        switch (command) {
            case "h": {
                help(json);
                break;
            }
            case "repeat": {
                repeatmode_change(json);
                break;
            }
            case "add": {
                add_admin(json);
                break;
            }
            default: {
                unknown_command(json);
                break;
            }
        }
    }


    private void help(JSONObject json) {
        StringBuilder cmd_list = new StringBuilder();
        for (Map.Entry<String, String> entry : cmd_map.entrySet()) {
            cmd_list.append(on_command).append(entry.getKey()).append("  ").append(entry.getValue()).append("\n");
        }
        send_message(json, cmd_list.toString());
    }

    private void repeatmode_change(JSONObject json) {
        send_message(json, RepeatMode ? "复读关闭" : "复读开启");
        RepeatMode = !RepeatMode;
    }

    private void permission_denied(JSONObject json) {
        send_message(json, "[CQ:at,qq=%s]没有权限".formatted(json.getString("user_id")));
    }

    private void unknown_command(JSONObject json) {
        send_message(json, "当前指令不存在，输入\"" + on_command + "h\"获取帮助");
    }

    private void add_admin(JSONObject json) {
        //admins.setBotowner("321");
    }

    private void send_message(JSONObject json, String message) {
        JSONObject response = new JSONObject();
        response.put("message", message);
        if (json.getString("group_id") != null) {
            response.put("group_id", json.getString("group_id"));
            restTemplate.postForObject(ep.getGmsg(), response, String.class);
        } else {
            response.put("user_id", json.getString("user_id"));
            restTemplate.postForObject(ep.getPmsg(), response, String.class);
        }
    }

    private void send_message(String u_id, JSONObject json) {
        JSONObject response = new JSONObject();
        response.put("user_id", u_id);
        response.put("message", json.getString("message"));
        restTemplate.postForObject(ep.getPmsg(), response, String.class);
    }

    private void YYDZ(JSONObject json) {
        String message = json.getString("message");
        if (utils.getPinYinHeadChar(message).equals("YYDZ")) {
            utils.getpath();
            File file = new File(utils.getpath() + "/DJ");
            int max = file.listFiles().length;
            int count = new Random().nextInt(max);
            String imgurl = file.listFiles()[count % max].getAbsolutePath();
            if (imgurl.contains("\\")) imgurl = imgurl.replaceAll("\\\\", "/");
            System.out.println(imgurl);
            send_message(json, "[CQ:image,file=file:///%s]".formatted(imgurl));
        }
    }

    private void setu(JSONObject json) {
        String message = json.getString("message");
        if (message.contains("setu")) {
            Object s = null;
            try {
                s = restTemplate.getForObject("https://sex.nyan.xyz/api/v2/?r18=true", String.class);
            } catch (RestClientException e) {
                JSONObject response = JSON.parseObject(e.getMessage().substring(e.getMessage().indexOf("{"),e.getMessage().length()- 1));
                send_message(json,response.getString("message"));
            }
            if (s != null) {
                JSONObject response = JSON.parseObject(s.toString());
                JSONArray data = response.getJSONArray("data");
                String imgurl = data.getJSONObject(0).getString("url");
                send_message(json, "[CQ:image,file=%s]".formatted(imgurl));
                send_message(json, imgurl);
            }
        }
    }
}
