package com.jy.theplayandroid.playandroid.playandroid.daohang.utils;

import org.greenrobot.eventbus.EventBus;

/*
 * created by taofu on 2018/11/27
 **/
public class AppConstant {

    //public static final String BASE_URL =BuildConfig.ApiUrl;
    public static final String BASE_URL = "http://www.wanandroid.com/";


    public static final String DOWNLOAD_URL = "http://imtt.dd.qq.com/16891/89E1C87A75EB3E1221F2CDE47A60824A.apk?fsname=com.snda.wifilocating_4.2.62_3192.apk&csr=1bbd";
    public static final String SAVE_USER_LOGIN_KEY = "user/login";
    public static final String SAVE_USER_REGISTER_KEY = "user/register";
    public static final String SET_COOKIE_KEY = "set-cookie";

    public static final String COLLECTIONS_WEBSITE = "lg/collect";
    public static final String UNCOLLECTIONS_WEBSITE = "lg/uncollect";
    public static final String ARTICLE_WEBSITE = "article";
    public static final String TODO_WEBSITE = "lg/todo";

    public static final String COOKIE_NAME = "Cookie";

    public interface ParamsUser {
        String KEY_USER_NAME = "username";
        String KEY_USER_PASSWORD = "password";
        String KEY_USER_REPASSWORD = "repassword";

    }


}
