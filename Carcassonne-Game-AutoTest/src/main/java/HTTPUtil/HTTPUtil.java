package HTTPUtil;

import okhttp3.*;

import java.io.IOException;

public class HTTPUtil {

    public static String BASE_ADDRESS = "http://47.119.130.124:90";
    public static String offline_userLogin = "/offline/userLogin";


    public static final MediaType JSON  = MediaType.get("application/json; charset=utf-8");
    public static String result = null;
    public static String post(final String url, final String json) throws IOException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=========> 请求发送：URL ---->" +url+"   json ---> " + json);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                try {
                    try (Response response = client.newCall(request).execute()) {
                        try {
                            result = response.body().string(); System.out.println(" ========> 接收："+result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int turn = 0;
        while ( turn < 20 ){
            Thread.sleep(500);
            if(result != null){
                return result;
            }

            else turn++;
        }

        return "网络请求异常(或超时)";
    }
}
