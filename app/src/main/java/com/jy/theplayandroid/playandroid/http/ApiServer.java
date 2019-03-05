package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteWebDeleteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.HttpResult;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.User;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.TwoBEAN;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface ApiServer {
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
     * 导航收藏
     *http://www.wanandroid.com/lg/collect/add/json
     * */
    @POST("lg/collect/add/json")
    Observable<JsonBean>getDaoHangLike(@Body RequestBody requestBody);

    /**
     * 收藏站外文章
     *http://www.wanandroid.com/lg/collect/add/json
     * */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<FavroiteAddBean>getFavruiteWebAdd(@FieldMap Map<String,Object> requestBody);

    /**
     * 收藏列表
     *http://www.wanandroid.com/lg/collect/list/0/json
     * */
    @POST("lg/collect/list/0/json")
    Observable<Favruite>getFavruite(@Body RequestBody requestBody);

    //项目分类
    @GET("project/tree/json")
    Observable<ProjectClassifyData> getProjectfenlei();

    @GET("project/list/{page}/json")
    Observable<ProjectListBean> getProjectList(@Path("page") int page, @Query("cid") int cid);

    @GET("tree/json")
    Observable<OneBean> getZhishiOne();

    @GET("article/list/{curPage}/json")
    Observable<TwoBEAN> getZhishiTwo(@Path("curPage")String page, @Query("chapterId") String id);

}
