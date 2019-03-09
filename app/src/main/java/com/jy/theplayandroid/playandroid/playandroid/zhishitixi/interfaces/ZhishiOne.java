package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.OneBean;

public interface ZhishiOne {

    interface oneView extends Base_View{
        void show(OneBean oneBean);
    }

    interface oneMoudel extends HttpFinishCallBack{
        void setOne(OneBean oneBean);
    }

    interface onePresenter{
        void getone();
    }

}
