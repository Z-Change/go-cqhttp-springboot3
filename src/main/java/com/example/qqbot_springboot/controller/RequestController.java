package com.example.qqbot_springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qqbot_springboot.service.MessageService;
import com.example.qqbot_springboot.service.MetaEventService;
import com.example.qqbot_springboot.service.NoticeService;
import com.example.qqbot_springboot.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class RequestController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MetaEventService metaEventService;

    private NoticeService noticeService;

    private RequestService requestService;

    @PostMapping("/")
    public void handlePostRequest(@RequestBody String requestBody) {

        JSONObject json = JSON.parseObject(requestBody);
        switch (json.getString("post_type")){
            case "meta_event":{
                metaEventService.metarvent(json);
                break;
            }
            case "message":{
                messageService.msg_respond(json);
                break;
            }
            case "request":{

                break;
            }
            case "notice":{

                break;
            }
        }
    }
}
