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
@RequestMapping(value = "/playing", produces = "application/json; charset=utf-8")
public class Playingcontroller {

    @Autowired
    public UserService userService;

    @Autowired
    public RoomService roomService;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/getFrameInfo",method = RequestMethod.POST)
    public JSONObject getFrameInfo(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String token = null;
        try {
            token = request.getHeader("token");
            String accountNum = JwtTokenUtil.getUsername(token);
            Integer roomNum = userService.getWaitStartPlayerByAccountNum(accountNum).getInteger("inRoomNum");
            result = roomService.getFrameInfo(roomNum);
            result.put("code",200);
            result.put("message","OK, request successfully");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message("null"));
            logger.error("/playing/getFrameInfo api end with 500 , unknown error ! token :" + token);
            return result;
        }
    }
}
