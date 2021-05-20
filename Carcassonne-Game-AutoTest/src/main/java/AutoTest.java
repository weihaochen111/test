import HTTPUtil.HTTPUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class AutoTest {


    public static void main(String args[]) throws IOException, InterruptedException {
        String NULL = "null";
        String TOKEN = "token";
        JSONObject p1 = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.offline_userLogin,"{\"password\":\"111\",\"accountNum\":\"1072876025@qq.com\"}",NULL,NULL)) ;
        System.out.println(p1);;
        Thread.sleep(200);
        JSONObject p2 = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.offline_userLogin,"{\"password\":\"111\",\"accountNum\":\"1072876026@qq.com\"}",NULL,NULL)) ;
        System.out.println(p2);
        Thread.sleep(200);
        String token1 = p1.getString("token");
        String token2 = p2.getString("token");
        Thread.sleep(200);
        JSONObject userCreateRoom1 = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.wander_userCreateRoom,"{\"roomName\":\"myRoom\",\"roomPassword\":\"111\"}",TOKEN,token1)) ;
//        System.out.println(userCreateRoom1);
        Thread.sleep(2000);
        String roomNum = userCreateRoom1.getString("roomNum"); System.out.println(roomNum);
        Thread.sleep(200);
        JSONObject userJoinRoom = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.wander_userJoinRoom,"{\"addMode\":\"select\",\"roomNum\":\""+roomNum+"\"}",TOKEN,token2)) ;
//        System.out.println(userJoinRoom);
        Thread.sleep(200);
        JSONObject readyAndStartGame1 = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.waitStart_readyAndStartGame,NULL,TOKEN,token1)) ;
//        System.out.println(readyAndStartGame1);
        Thread.sleep(200);
        JSONObject readyAndStartGame2 = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.waitStart_readyAndStartGame,NULL,TOKEN,token2)) ;
//        System.out.println(readyAndStartGame2);
        Thread.sleep(200);
        JSONObject getFrameInfo1 = JSONObject.parseObject(HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.playing_getFrameInfo,NULL,TOKEN,token1)) ;
//        System.out.println(getFrameInfo1);
        Thread.sleep(200);

    }
}
