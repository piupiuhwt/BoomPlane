package com.hwt.websocket.processer;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.Session;

public class BoomMessageProcessor implements MessageProcessor{
    private static final String CODE = "code";

    @Override
    public void handleMessage(Session session, String message) {
        //1. 解出code
        JSONObject jsonObject = JSONObject.parseObject(message);
        Integer code = jsonObject.getInteger(CODE);
        //2. 根据code找到相应处理逻辑
        //3.
    }
}
