package com.jy.theplayandroid.playandroid.global;

import android.app.Activity;
import android.app.Application;
import android.support.v7.widget.RecyclerView;

import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;

import java.util.Set;

/**
 * Created by 段傅华 on 2018/12/21.
 */

public class MyApp extends Application {
    public static MyApp sMyApp;
    private Set<Activity> allActivities;
    public boolean bool=true;
    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp=this;
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

}
