package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.bean.TalkCalssifyBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface ApiServer {
     /*
    * 热门分类标签
    * */
    @POST("tags/hot")
    //@Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<TalkCalssifyBean> getTalkClassifyBean(@Body RequestBody requestBody);
}
