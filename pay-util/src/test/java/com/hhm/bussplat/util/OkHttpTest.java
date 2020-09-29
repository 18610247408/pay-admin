package com.hhm.bussplat.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author q
 * @time 2020/5/19 5:50 下午
 */
public class OkHttpTest {

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();
        Request req = new Request.Builder()
                .url("http://card.ihaomai88.com/bussplat-business/example/hello")
                .get().build();
        Call call = okHttpClient.newCall(req);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        //异步处理
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure 请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse 请求成功,responseBody="+response.body().string());
            }
        });*/
    }
}
