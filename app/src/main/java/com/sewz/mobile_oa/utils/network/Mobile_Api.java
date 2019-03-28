package com.sewz.mobile_oa.utils.network;

import com.sewz.mobile_oa.utils.UrlUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018\11\19 0019.
 */

public class Mobile_Api {

    private static Mobile_Api instance;

    public static Mobile_Api getInstance() {
        if (instance == null)
            instance = new Mobile_Api();
        return instance;
    }


    /**
     * post 请求操作
     *
     * @param reqParams
     * @param b
     * @throws IOException
     */
    public void postData(String loginMethod, Map<String, String> reqParams, Callback b) throws IOException {
        String url = UrlUtils.SERVER_URL + loginMethod;
        post(url, reqParams, b);
    }


    /**
     * get  请求方式
     *
     * @param url 请求路径
     * @param b   回调方法
     * @throws IOException
     */
    public void get(String url, Callback b) throws IOException {
        Request request;
        request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        client.newCall(request).enqueue(b);
    }


    /**
     * post  请求
     *
     * @param url       请求地址
     * @param reqParams 参数
     * @param b         参数回调
     */
    public void post(String url, Map<String, String> reqParams, Callback b) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : reqParams.keySet()) {
            builder.add(key, reqParams.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        client.newCall(request).enqueue(b);
    }

}
