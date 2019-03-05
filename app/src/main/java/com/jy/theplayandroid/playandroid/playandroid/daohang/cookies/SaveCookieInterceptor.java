package com.jy.theplayandroid.playandroid.playandroid.daohang.cookies;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.daohang.utils.AppConstant;
import com.jy.theplayandroid.playandroid.playandroid.daohang.utils.SharePreferenceUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
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
