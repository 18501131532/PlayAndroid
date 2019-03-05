package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.ChuangYongBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.SearchHotkeyBean;

import java.util.List;

public interface SearchHotkey {
    interface SearchHotkeyV extends Base_View {
        void showSearchHotkey(List<SearchHotkeyBean.DataBean> dataBeans);
    }

    interface SearchHotkeyP {
        void getSearchHotkey();
    }

    interface SearchHotkeyCallBack extends HttpFinishCallBack {
        void setSearchHotkey(List<SearchHotkeyBean.DataBean> dataBeans);
    }
}
