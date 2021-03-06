package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.bean.Favruite;
import com.jy.theplayandroid.playandroid.bean.HttpResult;
import com.jy.theplayandroid.playandroid.bean.JsonBean;
import com.jy.theplayandroid.playandroid.bean.FeedArticleListData;
import com.jy.theplayandroid.playandroid.bean.WxAuthor;
import com.jy.theplayandroid.playandroid.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.bean.ChuangYongBean;
import com.jy.theplayandroid.playandroid.bean.OneBean;
import com.jy.theplayandroid.playandroid.bean.SearchBean;
import com.jy.theplayandroid.playandroid.bean.SearchHotkeyBean;
import com.jy.theplayandroid.playandroid.bean.TwoBEAN;

import io.reactivex.Observable;
import com.jy.theplayandroid.playandroid.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.bean.ArticleListBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface  ApiServer {
    //http://www.wanandroid.com/navi/json

    /**
     * 登录
     *http://www.wanandroid.com/user/login
     * */
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoadingBean>getLoading(@FieldMap Map<String,Object> requestBody);

    /**
     * 注册
     *http://www.wanandroid.com/user/register
     * */
    @POST("user/register")
    Observable<RegisterBean>getRegister(@Body RequestBody requestBody);

    /**
     * 导航列表
     *
     * */
    @GET
    Observable<JsonBean>getDaoHangList(@Url String url);

    /**
     * 收藏站外文章
     *http://www.wanandroid.com/lg/collect/add/json
     * */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<FavroiteAddBean>getFavruiteWebAdd(@FieldMap Map<String,Object> requestBody);

    /**
     * 取消站外收藏
     *http://www.wanandroid.com/lg/uncollect/2805/json
     * */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<HttpResult>getFavruiteWebDelete(@Path("id") int id, @Field("originId") int originId);

    /**
     * 收藏列表
     *http://www.wanandroid.com/lg/collect/list/0/json
     * */
    @GET("lg/collect/list/{page}/json")
    Observable<Favruite>getFavruite(@Path("page") int page);

    //项目分类
    @GET("project/tree/json")
    Observable<ProjectClassifyData> getProjectfenlei();

    //项目列表数据
    @GET("project/list/{page}/json")
    Observable<ProjectListBean> getProjectList(@Path("page") int page, @Query("cid") int cid);

    @GET("tree/json")
    Observable<OneBean> getZhishiOne();

    //公众号列表
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<FeedArticleListData> getWxSumData(@Path("id") int id, @Path("page") int page);
    //公众号搜索
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<FeedArticleListData> getWxSearchSumData(@Path("id") int id, @Path("page") int page, @QueryMap Map<String,Object> map);
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

    /*
     * 公众号
     * */
    @GET("wxarticle/chapters/json")
    Observable<WxAuthor> getWxAuthorListData();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid second page id
     * @return 知识体系feed文章数据
     */
    @GET("article/list/{curPage}/json")
    Observable<TwoBEAN> getZhishiTwo(@Path("curPage")String page, @Query("chapterId") String id);

    //常用网站列表
    @GET("friend/json")
    Observable<ChuangYongBean> getchangyong();

    //搜索热词
    @GET("hotkey/json")
    Observable<SearchHotkeyBean> getSearchHotkey();


    @POST("article/query/0/json")
    Observable<SearchBean> getsearch(@Query("k")String k);
}
