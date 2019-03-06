package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.HttpResult;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface TalkClassify {

    /**
     * 导航数据
     *
     * */
    interface DaoHangView extends Base_View{
        void showList(ArrayList<JsonBean.DataBean> arrayList);
    }

    interface DaoHangPresenter {
        void getDaoHang(String json);
    }

    interface DaoHangCallBack extends HttpFinishCallBack{
        void setDaoHangList(ArrayList<JsonBean.DataBean> arrayList);
    }

    /**
     * 登录
     *
     * */
    interface LoadingView extends Base_View{
        void showLoading(LoadingBean loadingBean);
    }

    interface LoadingPresenter {
        void getLoading(Map<String,Object> map);
    }

    interface LoadingCallBack extends HttpFinishCallBack{
        void setLoading(LoadingBean loadingBean);
    }

    /**
     * 注册
     *
     * */
    interface RegisterView extends Base_View{
        void showRegister(RegisterBean registerBean);
    }

    interface RegisterPresenter {
        void getRegister(RequestBody map);
    }

    interface RegisterCallBack extends HttpFinishCallBack{
        void setRegister(RegisterBean registerBean);
    }

    /**
     * 收藏网址
     *
     * */
    interface FavruiteWebView extends Base_View{
        void showFavruiteWeb(FavroiteAddBean favruiteBean);
        void showFavruite(Favruite favruiteBean);
        void showFavruiteWebDelete(HttpResult favruiteBean);
    }

    interface FavruiteWebPresenter {
        void getFavruiteWeb(Map<String,Object> formBody);
        void getFavruite(int page);
        void getFavruiteWebDelete(Map<String,Object> formBody);
    }

    interface FavruiteWebCallBack extends HttpFinishCallBack{
        void setFavruiteWeb(FavroiteAddBean favruiteWeb);
        void setFavruite(Favruite favruiteWeb);
        void setFavruiteWebDelete(HttpResult favruiteWeb);
    }
}
