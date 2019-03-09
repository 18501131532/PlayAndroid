package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.TwoBEAN;

public interface ZhishiTwo {

    interface twoView extends Base_View{
        void show(TwoBEAN twoBEAN);
    }

    interface twoMoudel extends HttpFinishCallBack{
        void settwo(TwoBEAN twoBEAN);
    }

    interface twoPresenter{
        void gettwo(String page,String id);
    }

}
