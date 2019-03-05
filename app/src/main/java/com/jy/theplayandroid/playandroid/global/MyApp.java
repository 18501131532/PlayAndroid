package com.jy.theplayandroid.playandroid.global;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.support.v7.widget.RecyclerView;

import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 段傅华 on 2018/12/21.
 */

public class MyApp extends Application {
    public static MyApp sMyApp;
    private List<Activity> activities = new ArrayList<Activity>();
    private static MyActivityLifecycle myActivityLifecycle;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;

    private Set<Activity> allActivities;
    public boolean bool=true;
    @Override
    public void onCreate() {
        super.onCreate();
        myActivityLifecycle = new MyActivityLifecycle();
        registerActivityLifecycleCallbacks(myActivityLifecycle);
        sMyApp=this;

        boolean runBackground = isRunBackground(sMyApp);
        if (runBackground){

        }else {
            Log.e("duanxq", "app: " + "shabi");
            mSharedPreferences = getSharedPreferences("loging", 0);
            mEdit = mSharedPreferences.edit();
            mEdit.putBoolean("loging", false);
            mEdit.commit();
        }
    }


    public void ScrollList(RecyclerView recy){
        recy.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0&&bool){//下滑隐藏
                    bool=false;
                    PlayFragment.bottomNavigationView.animate().translationY(PlayFragment.bottomNavigationView.getHeight());
                    PlayFragment.fab.animate().translationY(PlayFragment.fab.getHeight()+PlayFragment.bottomNavigationView.getHeight()+20);
                }else if(dy<0&&!bool){
                    bool=true;
                    PlayFragment.bottomNavigationView.animate().translationY(0);
                    PlayFragment.fab.animate().translationY(0);

                }

            }
        });
    }

    public static  synchronized MyApp getMyApp(){
        return sMyApp;
    }

    /** 判断程序是否在后台运行 */
    public static boolean isRunBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    // 表明程序在后台运行
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
