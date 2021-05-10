package com.carcassonne.gameserver.configuration;

public class StateCodeConfig {
    public static String when_500_message(String request){
        return  "The server encountered an unknown BUG with request:["+request+"], please contact QQ:1072876025";
    }

}
