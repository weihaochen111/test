package com.carcassonne.gameserver.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class testLogbackController {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/testLogbackController",method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        //日志级别从低到高为 TRACE < DEBUG < INFO < WARN < ERROR，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
    }

}
