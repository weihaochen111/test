package com.carcassonne.gameserver.interceptor;

import ch.qos.logback.classic.Logger;
import com.carcassonne.gameserver.util.JwtTokenUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCheckHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(" preHandle method");
        try {
//            String token = request.getHeader("token");
//            System.out.println(JwtTokenUtil.checkJWT(token));
            return true;
        }catch (Exception e){
            logger.error("interceptor undefine error ! ");
            return false;
        }

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info(" postHandle method");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info(" afterCompletion method");
    }
}

