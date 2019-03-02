package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.basepresenter.Base_Presenter;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;

import java.util.ArrayList;

import okhttp3.FormBody;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public interface TalkClassify {
//    interface TalkClassifyView extends Base_View{
//        void showSuccess(TalkCalssifyBean talkCalssifyBean);
//    }
//    interface TalkClassifyIPresenter {
//        void getTalkClassify(FormBody formBody);
//    }
//    interface TalkClassCallBack extends HttpFinishCallBack{
//        void setSuccess(TalkCalssifyBean talkCalssifyBean);
//    }

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
}
