package com.jy.theplayandroid.playandroid.playandroid.daohang.cookies;

import android.content.SharedPreferences;

import com.jy.theplayandroid.playandroid.global.MyApp;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/*
 * created by taofu on 2019/1/14
 **/
public class SaveCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor config = MyApp.sMyApp.getSharedPreferences("config", MyApp.sMyApp.MODE_PRIVATE)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.commit();
        }

        return originalResponse;
    }
}
