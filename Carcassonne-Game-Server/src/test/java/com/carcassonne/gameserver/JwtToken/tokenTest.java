package com.carcassonne.gameserver.JwtToken;

import com.carcassonne.gameserver.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;

public class tokenTest {
    @Test
    public void tt() throws InterruptedException {

        System.out.println(JwtTokenUtil.createToken("cc","user"));
//        try {
//            String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoidXNlciIsImV4cCI6MTYyMDQwMTA2MywiaWF0IjoxNjIwMzc5NDYzLCJ1c2VybmFtZSI6IjEwNzI4NzYwMjVAcXEuY29tIn0.5ESRDWIyQzM2qOUIHe9JhHbqTVscYnJ9OtIOk3MUaYE";
//
//            System.out.println(JwtTokenUtil.checkJWT(token));
//            System.out.println(JwtTokenUtil.isExpiration(token));
//        }catch (Exception e){
//            System.out.println("error");
//            e.printStackTrace();
//        }


    }
}
