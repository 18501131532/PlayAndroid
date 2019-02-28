package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface ApiServer {


    @GET("tree/json")
    Observable<OneBean> getZhishiOne();

}
