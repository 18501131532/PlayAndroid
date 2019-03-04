package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.TwoBEAN;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface ApiServer {
    //http://www.wanandroid.com/navi/json
    @GET
    Observable<JsonBean>getDaoHangList(@Url String url);


    //项目分类
    @GET("project/tree/json")
    Observable<ProjectClassifyData> getProjectfenlei();

    @GET("project/list/{page}/json")
    Observable<ProjectListBean> getProjectList(@Path("page") int page, @Query("cid") int cid);

    @GET("tree/json")
    Observable<OneBean> getZhishiOne();

    @GET("article/list/{curPage}/json")
    Observable<TwoBEAN> getZhishiTwo(@Path("curPage")String page, @Query("cid") String id);

}
