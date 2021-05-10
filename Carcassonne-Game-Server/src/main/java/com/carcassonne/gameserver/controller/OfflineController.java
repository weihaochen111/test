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

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping(value = "/offline", produces = "application/json; charset=utf-8")
public class OfflineController {

    @Autowired
    public UserService userService;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public JSONObject userLogin(@RequestBody String JSONBody) throws InterruptedException {
        logger.info("/offline/userLogin api start  ======> requestBody:" + JSONBody);
        JSONObject result = new JSONObject();
        User user = new User();
        String accountNum = null;
        String password = null;
        try{
            JSONObject requestBody = JSONObject.parseObject(JSONBody);
            accountNum = (String) requestBody.get("accountNum");
            password = (String) requestBody.get("password");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("request : "+JSONBody + " to [/offline/userLogin controller] with error Info ==> "+e.toString());
            result.put("code",400);
            result.put("message","Bad Request");
            logger.info("/offline/userLogin api end with 400 , Bad Request :" + JSONBody);
            return result;
        }

        try{
            user = userService.selectByAccountNum(accountNum);
            if(user != null){
                if(user.getPassword().equals(password)){
                    String token = JwtTokenUtil.createToken(user.getAccountNum(),"user");
                    user.setToken(token);
                    JSONObject userInfo = user.toJSONObject();
                    userService.insertWonderUser(user);
                    logger.info("redis ===> new record :"+ userService.getWonderUserByAccountNum(accountNum).toString());
                    userInfo.put("state","wander");
                    result.put("code",200);
                    result.put("message","OK , Login successfully !");
                    result.put("token",token);
                    result.put("userInfo",userInfo);
                    logger.info("/offline/userLogin api end with 200 , " + user.toString() + " Login successfully ");
                    return result;
                }
                else{
                    result.put("code",210);
                    result.put("message","Wrong password, Login failure!");
                    logger.info("/offline/userLogin api end with 200 ,  Wrong password , Login failure :" + JSONBody);
                    return result;
                }
            }
            else {
                result.put("code",211);
                result.put("message","The user does not exist, Login failure!");
                logger.info("/offline/userLogin api end with 200 ,  The user does not exist, Login failure : " + JSONBody);
                return result;
            }


        }catch (Exception e){
            e.printStackTrace();
            logger.error("request : "+JSONBody + " to [/offline/userLogin controller] with error Info ==> "+e.toString());
            result.put("code",500);
            result.put("message", StateCodeConfig.when_500_message(JSONBody));
            return result;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public JSONObject userRegister(@RequestBody String JSONBody) throws InterruptedException {
        logger.info("start /offline/userRegister controller  ======> requestBody:" + JSONBody);
        JSONObject result = new JSONObject();
        User user = new User();
        String accountNum = null;
        try{
            JSONObject requestBody = JSONObject.parseObject(JSONBody);
            accountNum = (String) requestBody.get("accountNum");
            String password = (String) requestBody.get("password");
            String nickname = (String) requestBody.get("nickname");
            String sex = (String) requestBody.get("sex");
            String headPictureURL = (String) requestBody.get("headPictureURL");
            user.setAccountNum(accountNum);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setSex(sex);
            user.setHeadPictureURL(headPictureURL);
            user.setLevel("0");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("request : "+JSONBody + " to [/offline/userRegister controller] with error Info ==> "+e.toString());
            result.put("code",400);
            result.put("message","Bad Request");
            logger.info("/offline/userRegister api end with 400 , Bad Request ");
            return result;
        }

        try{
            User temp = userService.selectByAccountNum(accountNum);
            if( temp != null && temp.getAccountNum().equals(accountNum)){
                result.put("code",212);
                result.put("message","OK ,The account already exists, registration failed!");
                logger.info("/offline/userRegister api end with 200 ,The account already exists, registration failed!");
                return result;
            }else {
                userService.insertUser(user);
                result.put("code",200);
                result.put("message","OK , registered successfully !");
                logger.info("/offline/userRegister api end with 200 , " + user.toString() + " registered successfully ");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("request : "+JSONBody + " to [/offline/userRegister controller] with error Info ==> "+e.toString());
            result.put("code",500);
            result.put("message",StateCodeConfig.when_500_message(JSONBody));
            return result;
        }
    }


}
