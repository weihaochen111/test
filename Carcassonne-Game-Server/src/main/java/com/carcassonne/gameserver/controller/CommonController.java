package com.carcassonne.gameserver.controller;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.configuration.StateCodeConfig;
import com.carcassonne.gameserver.service.RoomService;
import com.carcassonne.gameserver.service.UserService;
import com.carcassonne.gameserver.util.JwtTokenUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping(value = "/common", produces = "application/json; charset=utf-8")
public class CommonController {

    @Autowired
    public UserService userService;

    @Autowired
    public RoomService roomService;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/getChatInfo",method = RequestMethod.POST)
    public JSONObject getChatInfo(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String token = null;
        try {
            token = request.getHeader("token");
            String accountNum = JwtTokenUtil.getUsername(token);
            Integer roomNum = userService.getWaitStartPlayerByAccountNum(accountNum).getInteger("inRoomNum");
            result.put("roomChatInfo",roomService.getMsgListToJSONArray(roomNum));
            result.put("code",200);
            result.put("message","OK, request successfully");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message("null"));
            logger.error("/common/getChatInfo api end with 500 , unknown error ! token :" + token);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sendChatInfo",method = RequestMethod.POST)
    public JSONObject sendChatInfo(HttpServletRequest request,@RequestBody String JSONBody){
        JSONObject result = new JSONObject();
        JSONObject requestBody = new JSONObject();
        String token = null;
        String content = null;
        String type = null;
        Integer roomNum = null;
        String accountNum = null;
        try {
            token = request.getHeader("token");
            accountNum = JwtTokenUtil.getUsername(token);
            roomNum = userService.getWaitStartPlayerByAccountNum(accountNum).getInteger("inRoomNum");
            requestBody = JSONObject.parseObject(JSONBody);
            content = requestBody.getString("context");
            type = requestBody.getString("type");

        }catch (Exception e){
            e.printStackTrace();
            logger.error("request : "+JSONBody + " to [/common/sendChatInfo controller] with error Info ==> "+e.toString());
            result.put("code",400);
            result.put("message","Bad Request");
            logger.info("/common/sendChatInfo api end with 400 , Bad Request :" + JSONBody);
            return result;
        }

        try {
            roomService.addMsg(roomNum,accountNum,content);
            result.put("code",200);
            result.put("message","OK, request successfully");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message(JSONBody));
            logger.error("/common/getChatInfo api end with 500 , unknown error ! token :" + token);
            return result;
        }
    }


}
