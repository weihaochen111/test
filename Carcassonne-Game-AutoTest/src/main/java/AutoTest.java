import HTTPUtil.HTTPUtil;

import java.io.IOException;

public class AutoTest {


    public static void main(String args[]) throws IOException, InterruptedException {
        HTTPUtil.post(HTTPUtil.BASE_ADDRESS + HTTPUtil.offline_userLogin,"{\"password\":\"111\",\"accountNum\":\"1072876025@qq.com\"}");
        System.out.println(HTTPUtil.result);


    }
}
