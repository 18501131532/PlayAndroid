package com.jy.theplayandroid.playandroid.playandroid.main.moudle;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.concat.AtricleList;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.jy.theplayandroid.playandroid.util.RxUtils;

/**
 * Created by 段傅华 on 2019/2/28.
 */

public class AtricleListMoudle {
    public void setAtricleList(int page, final AtricleList.AtricleListCallBack callBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getArticleList(page)
                .compose(RxUtils.<ArticleListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ArticleListBean>(callBack) {
                    @Override
                    public void onNext(ArticleListBean value) {
                        callBack.showSuccess(value);
                    }
                });
    }
    public void setAtricBanner(final AtricleList.AtricleListCallBack callBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getArticleBanner()
                .compose(RxUtils.<ArticleBannerBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ArticleBannerBean>(callBack) {
                    @Override
                    public void onNext(ArticleBannerBean value) {
                        callBack.showSuccess(value);
                    }
                });
    }
}
