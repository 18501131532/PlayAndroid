package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.bean.ArticleListBean;

/**
 * Created by 段傅华 on 2019/2/28.
 */

public interface AtricleList {
    interface AtricleListView extends Base_View{
        void showSuccess(ArticleListBean listBean);
        void shoeSuccess(ArticleBannerBean bannerBean);
    }
    interface AtricleListIPresenter{
        void getAtricList(int page);
        void getAtricBanner();
    }
    interface AtricleListCallBack extends HttpFinishCallBack{
        void showSuccess(ArticleListBean listBean);
        void showSuccess(ArticleBannerBean bannerBean);
    }
}
