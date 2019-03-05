package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;

public interface FeedArticleListConcat {
    interface FeedArticleView extends Base_View {
        void showFeedArticleInfo(FeedArticleListData feedArticleListData);
    }
    interface FeedArticlePresenter{
        void setData(int id, int name);
    }
    interface FeedArticleCallBack extends HttpFinishCallBack {
        void getFeedArticleInfo(FeedArticleListData feedArticleListData);
    }
}
