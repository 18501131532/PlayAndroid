package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.WxAuthor;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.ChuangYongBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.SearchHotkeyBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.TwoBEAN;

import io.reactivex.Observable;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Part;
import retrofit2.http.Path;

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
   //公众号
    @GET("wxarticle/chapters/json")
    Observable<WxAuthor> getWxAuthorListData();
   /*
   * 收藏站内文章
   * */


    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid second page id
     * @return 知识体系feed文章数据
     */

    @GET("article/list/{curPage}/json")
    Observable<TwoBEAN> getZhishiTwo(@Path("curPage")String page, @Query("cid") String id);

    //常用网站列表
    @GET("friend/json")
    Observable<ChuangYongBean> getchangyong();

    //搜索热词
    @GET("hotkey/json")
    Observable<SearchHotkeyBean> getSearchHotkey();
}
