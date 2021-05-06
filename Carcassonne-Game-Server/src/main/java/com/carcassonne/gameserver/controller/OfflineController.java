package com.carcassonne.gameserver.controller;


import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.User;
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
            logger.error(e.getMessage());
            result.put("code",400);
            result.put("message","Bad Request");
            logger.info("/offline/userLogin api end with 400 , Bad Request ");
            return result;
        }

        try{
            user = userService.selectByAccountNum(accountNum);
            if(user.getAccountNum() != null){
                if(user.getPassword().equals(password)){
                    //TODO redis加入token,redis加入在线用户
                    String token = JwtTokenUtil.createToken(user.getAccountNum(),"user");
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("token",token);
                    userInfo.put("")


                }
            }
            else {

            }

            result.put("code",200);
            result.put("message","OK , registered successfully !");
            logger.info("/offline/userRegister api end with 200 , " + user.toString() + " registered successfully ");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("code",500);
            result.put("message","The server encountered an unknown BUG, please contact QQ:1072876025");
            return result;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public JSONObject userRegister(@RequestBody String JSONBody) throws InterruptedException {
        logger.info("start /offline/userRegister controller  ======> requestBody:" + JSONBody);
        JSONObject result = new JSONObject();
        User user = new User();
        try{
            JSONObject requestBody = JSONObject.parseObject(JSONBody);
            String accountNum = (String) requestBody.get("accountNum");
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
            logger.error(e.getMessage());
            result.put("code",400);
            result.put("message","Bad Request");
            logger.info("/offline/userRegister api end with 400 , Bad Request ");
            return result;
        }


        try{
            userService.insertUser(user);
            result.put("code",200);
            result.put("message","OK , registered successfully !");
            logger.info("/offline/userRegister api end with 200 , " + user.toString() + " registered successfully ");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("code",500);
            result.put("message","The server encountered an unknown BUG, please contact QQ:1072876025");
            return result;
        }
    }


}
