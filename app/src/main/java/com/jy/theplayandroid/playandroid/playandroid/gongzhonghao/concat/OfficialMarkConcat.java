package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.WxAuthor;

public interface OfficialMarkConcat {
    interface OfficialMarkView extends Base_View{
        void showOfficialMarkInfo(WxAuthor wxAuthor);
    }
    interface OfficialMarkPresenter{
        void setData();
    }
    interface OfficialMarkCallBack extends HttpFinishCallBack{
        void getOfficialMarkInfo(WxAuthor wxAuthor);
    }
}
