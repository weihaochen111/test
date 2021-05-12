package com.carcassonne.gameserver.controller;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.User;
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
@RequestMapping(value = "/waitStart", produces = "application/json; charset=utf-8")
public class WaitStartController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/readyAndStartGame",method = RequestMethod.POST)
    public JSONObject readyAndStartGame(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String token = null;
        try {
            token = request.getHeader("token");
            String accountNum = JwtTokenUtil.getUsername(token);
            String state = roomService.readyAndStartGame(accountNum);
            result.put("code",200);
            result.put("message","OK, request successfully");
            result.put("roomState", state);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message("null"));
            logger.error("/waitStart/readyAndStartGame api end with 500 , unknown error ! token :" + token);
            return result;
        }
    }

}
