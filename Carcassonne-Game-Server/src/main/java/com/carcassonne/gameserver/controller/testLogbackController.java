package com.carcassonne.gameserver.controller;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.Card;
import com.carcassonne.gameserver.bean.Edge;
import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.service.CardService;
import com.carcassonne.gameserver.service.EdgeService;
import com.carcassonne.gameserver.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping(value = "/tt", produces = "application/json; charset=utf-8")
public class testLogbackController {

    @Autowired
    public UserService userService;

    @Autowired
    public EdgeService edgeService;

    @Autowired
    private CardService cardService;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/testLogbackController",method = RequestMethod.POST)
    public void login(@RequestBody String JSONBody) throws InterruptedException {
//        String token = JwtTokenUtil.createToken("cmy","student");

        System.out.println("========>" + JSONBody);
        logger.info("start controller");
//        JSONObject requestBodyJSONObject = RequestUtil.getRequestBodyJSONObject(request);
//        logger.info(requestBodyJSONObject.toString());

//        //日志级别从低到高为 TRACE < DEBUG < INFO < WARN < ERROR，如果设置为WARN，则低于WARN的信息都不会输出。
//        logger.debug("日志输出"+token);
//        logger.info("日志输出"+token);
//        logger.warn("日志输出 warn");
//        logger.error("日志输出 error");
//
//
//
//
//        System.out.println(token);
//        System.out.println(JwtTokenUtil.getUsername(token));
//        System.out.println(JwtTokenUtil.getUserRole(token));
//        System.out.println(JwtTokenUtil.checkJWT(token));
//        System.out.println(JwtTokenUtil.isExpiration(token));

        User user = new User("123","123","1","123","123","123");
        userService.insertUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/cardEdgeTest",method = RequestMethod.POST)
    public JSONObject cardEdgeTest() throws InterruptedException {
        JSONObject res = new JSONObject();
        ArrayList<Card> cardArrayList = new ArrayList<>(cardService.selectAllCard());
        ArrayList<Edge> edgeArrayList = new ArrayList<>(edgeService.selectAllEdge());
        for (int i = 0 ; i < cardArrayList.size() ; i++){
            cardArrayList.get(i).formatPictureURL();
            for (int j = 0 ; j < edgeArrayList.size() ; j++){
                if(Integer.parseInt(cardArrayList.get(i).getTopEdgeId() ) == edgeArrayList.get(j).getId()){
                    cardArrayList.get(i).setTop(edgeArrayList.get(j));
                }
                if(Integer.parseInt(cardArrayList.get(i).getRigEdgeId() ) == edgeArrayList.get(j).getId()){
                    cardArrayList.get(i).setRig(edgeArrayList.get(j));
                }
                if(Integer.parseInt(cardArrayList.get(i).getBotEdgeId() ) == edgeArrayList.get(j).getId()){
                    cardArrayList.get(i).setBot(edgeArrayList.get(j));
                }
                if(Integer.parseInt(cardArrayList.get(i).getLefEdgeId()) == edgeArrayList.get(j).getId()){
                    cardArrayList.get(i).setLef(edgeArrayList.get(j));
                }
            }
        }

        res.put("card",cardArrayList);

        return res;

    }


}
