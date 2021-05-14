package com.carcassonne.gameserver.interceptor;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.carcassonne.gameserver.bean.User;
import com.carcassonne.gameserver.configuration.RedisConfig;
import com.carcassonne.gameserver.service.UserService;
import com.carcassonne.gameserver.util.JwtTokenUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class PostCheckHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;
        try {
            token = request.getHeader("token");
            if(JwtTokenUtil.checkJWT(token) != null && !JwtTokenUtil.isExpiration(token)  ){
//                logger.info(" preHandle method, account:" + JwtTokenUtil.getUsername(token)+" ==> token check is correct" );
                return true;
            }else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                JSONObject res = new JSONObject();
                res.put("code", 403);
                res.put("message", "Token verification failed, The cause could be an expired token or an invalid token");
//                logger.info("Token verification failed , with "+ token);
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            JSONObject res = new JSONObject();
            res.put("code", 500);
            res.put("message", "An error occurred during token validation , Please check the token or contact QQ:1072876025");
            out = response.getWriter();
            out.append(res.toString());
            logger.error("An error occurred during token validation : "+ e.toString() + "  [token]: "+ token);
            return false;
        }

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

