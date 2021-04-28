package com.carcassonne.gameserver.JwtToken;

import com.carcassonne.gameserver.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;

public class tokenTest {
    @Test
    public void tt() throws InterruptedException {
        String token = JwtTokenUtil.createToken("cmy","student");
        System.out.println(token);
        System.out.println(JwtTokenUtil.getUsername(token));
        System.out.println(JwtTokenUtil.getUserRole(token));
        System.out.println(JwtTokenUtil.checkJWT(token));
        System.out.println(JwtTokenUtil.isExpiration(token));
        Thread.sleep(1000);
        System.out.println(JwtTokenUtil.isExpiration(token));
        Thread.sleep(1000);
        System.out.println(JwtTokenUtil.isExpiration(token));
        Thread.sleep(1000);
        System.out.println(JwtTokenUtil.isExpiration(token));
        Thread.sleep(1000);
        System.out.println(JwtTokenUtil.isExpiration(token));
        Thread.sleep(1000);
        System.out.println(JwtTokenUtil.isExpiration(token));
        Thread.sleep(1000);
        System.out.println(JwtTokenUtil.isExpiration(token));
    }
}
