package com.carcassonne.gameserver.controller;


import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.configuration.StateCodeConfig;
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
@RequestMapping(value = "/wander", produces = "application/json; charset=utf-8")
public class WanderController {

    @Autowired
    private UserService userService;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public JSONObject getUserInfo(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String token = null;
        try {
            token = request.getHeader("token");
            String accountNum = JwtTokenUtil.getUsername(token);
            User user = null;
            user = userService.getWonderUserByAccountNum(accountNum);
            user.setPassword("null");
            result.put("code",200);
            result.put("message","OK, request successfully");
            result.put("userInfo",user.toJSONObject());
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message("null"));
            logger.error("/wander/getUserInfo api end with 500 , unknown error ! token :" + token);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public JSONObject updateUserInfo(HttpServletRequest request,@RequestBody String JSONBody){
        JSONObject result = new JSONObject();
        JSONObject requestBody = null;
        String token = null;
        User user = null;
        try {
            token = request.getHeader("token");
            String accountNum = JwtTokenUtil.getUsername(token);
            user = userService.getWonderUserByAccountNum(accountNum);
            user.setPassword("null");

            requestBody = JSONObject.parseObject(JSONBody);
            if(requestBody.containsKey("nickname")){
                user.setNickname((String) requestBody.get("nickname"));
            }
            if(requestBody.containsKey("sex")){
                user.setSex((String) requestBody.get("sex"));
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("request : "+JSONBody + " to [/wander/getUserInfo controller] with error Info ==> "+e.toString());
            result.put("code",400);
            result.put("message","Bad Request");
            logger.info("/wander/updateUserInfo api end with 400 , Bad Request ");
            return result;
        }

        try {
            userService.updateUser(user);
            userService.insertWonderUser(user);
            result.put("code",200);
            result.put("message","OK, Information update successfully");
            result.put("userInfo",user.toJSONObject());
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message",StateCodeConfig.when_500_message("token"+token));
            logger.error("/wander/updateUserInfo api end with 500 , unknown error ! "+ e.toString() +" ==> token :" + token);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/userExit",method = RequestMethod.POST)
    public JSONObject userExit(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String token = null;
        try {
            token = request.getHeader("token");
            String accountNum = JwtTokenUtil.getUsername(token);
            userService.deleteWanderUser(accountNum);
            result.put("code",200);
            result.put("message","OK, User{accountNum:" + accountNum + "} exit successfully");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message("null"));
            logger.error("/wander/userExit api end with 500 , unknown error ! "+e.toString()+"token :" + token);
            return result;
        }
    }
}
