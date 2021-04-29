package com.carcassonne.gameserver.controller;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.service.CardService;
import com.carcassonne.gameserver.util.JwtTokenUtil;
import com.carcassonne.gameserver.util.RequestUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping(value = "/tt", produces = "application/json; charset=utf-8")
public class testLogbackController {


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
    }

}
