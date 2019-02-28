package com.jy.theplayandroid.playandroid.global;

import android.app.Activity;
import android.app.Application;

import java.util.Set;

/**
 * Created by 段傅华 on 2018/12/21.
 */

public class MyApp extends Application {
    public static MyApp sMyApp;
    private Set<Activity> allActivities;
    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp=this;
    }
    public static  synchronized MyApp getMyApp(){
        return sMyApp;
    }

}
