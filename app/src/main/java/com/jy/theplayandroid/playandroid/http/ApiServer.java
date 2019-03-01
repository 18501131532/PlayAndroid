package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;

import io.reactivex.Observable;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface ApiServer {


    //项目分类
    @GET("project/tree/json")
    Observable<ProjectClassifyData> getProjectfenlei();

    @GET("project/list/{page}/json")
    Observable<ProjectListBean> getProjectList(@Path("page") int page, @Query("cid") int cid);

    @GET("tree/json")
    Observable<OneBean> getZhishiOne();

   /*
   * 首页文章列表
   * */
   @GET("article/list/{page}/json")
   Observable<ArticleListBean> getArticleList(@Path("page")int page);
   /*
   * 首页banner
   * */
   @GET("banner/json")
   Observable<ArticleBannerBean>getArticleBanner();
}
