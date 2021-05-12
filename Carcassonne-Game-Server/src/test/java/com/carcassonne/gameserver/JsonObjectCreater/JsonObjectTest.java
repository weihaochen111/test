package com.carcassonne.gameserver.JsonObjectCreater;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class JsonObjectTest {

    @Test
    public void createJsonObject(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i=0;i<10;i++){
            JSONObject chat = new JSONObject();
            chat.put("某玩家","他的名字");
            chat.put("时间","发送的时间");
            chat.put("聊天内容","这是第"+i+"条消息");
            array.add(chat);
        }

        object.put("chat",array);
        System.out.println(object);
    }
}
