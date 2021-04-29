package com.carcassonne.gameserver.util;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestUtil {

    public static Logger logger = (Logger) LoggerFactory.getLogger(RequestUtil.class);

    public static JSONObject getRequestBodyJSONObject(HttpServletRequest request){
        BufferedReader br = null;
        JSONObject failJSONObject = new JSONObject();
        JSONObject resultJSONObject;
        failJSONObject.put("message","HttpServletRequest to JSONObject failed");
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("RequestUtil.getJSONObjectRequestBody BufferedRead Error");
            return failJSONObject;
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            logger.info("sb content + " + sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("RequestUtil.getJSONObjectRequestBody StringBuilder Error");
            return failJSONObject;
        }

        try {
            resultJSONObject = JSONObject.parseObject(sb.toString());
            logger.info("HttpServletRequest to JSONObject success ! content: " + resultJSONObject.toString());
            return  resultJSONObject;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("RequestUtil.getJSONObjectRequestBody JSONObject.parseObject Error " );
            return failJSONObject;
        }
    }
}
