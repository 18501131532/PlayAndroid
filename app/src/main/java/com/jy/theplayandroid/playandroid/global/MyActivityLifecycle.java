package com.jy.theplayandroid.playandroid.global;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class MyActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private int startCount;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.e("==============","======>onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.e("==============","======>onActivityStarted");
        startCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.e("==============","======>onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.e("==============","======>onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.e("==============","======>onActivityStopped");
        startCount--;
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e("==============","======>onActivityDestroyed");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.e("==============","======>onActivitySaveInstanceState");
    }

    public int getStartCount(){
        return startCount;
    }
}

