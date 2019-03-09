package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.ChuangYongBean;

public interface ChangYong {
    interface ChangYongV extends Base_View{
        void showChangYong(ChuangYongBean dataBeans);
    }

    interface ChangYongP {
        void getChangYongP();
    }

    interface ChangYongCallBack extends HttpFinishCallBack {
        void setChangYong(ChuangYongBean dataBeans);
    }
}
