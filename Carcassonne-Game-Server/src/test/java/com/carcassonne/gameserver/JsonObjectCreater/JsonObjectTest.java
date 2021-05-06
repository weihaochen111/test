package com.carcassonne.gameserver.JsonObjectCreater;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class JsonObjectTest {

    @Test
    public void createJsonObject(){
        JSONObject object = new JSONObject();
        object.put("accountNum","1072876025@qq.com");
        object.put("password","111");
        object.put("nickname","cmy");
        object.put("sex","男");
        object.put("headPictureURL","xxx.xxx");
        System.out.println(object.toJSONString());
    }
}
