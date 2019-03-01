package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface ApiServer {


    //项目分类
    @GET("project/tree/json")
    Observable<ProjectClassifyData> getProjectfenlei();

    @GET("project/list/{page}/json")
    Observable<ProjectListBean> getProjectList(@Path("page") int page, @Query("cid") int cid);
}
