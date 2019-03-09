package com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.SearchHotkey;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.moudle.IMSearchHotkey;
import com.jy.theplayandroid.playandroid.bean.SearchHotkeyBean;

import java.util.List;

public class IPSearchHotkey<V extends SearchHotkey.SearchHotkeyV> extends BasePresenter<V> implements SearchHotkey.SearchHotkeyP ,SearchHotkey.SearchHotkeyCallBack {
    private IMSearchHotkey mIMSearchHotkey=new IMSearchHotkey();
    @Override
    public void getSearchHotkey() {
        if (mView!=null){
            mIMSearchHotkey.getSearchHotkey(this);
        }
    }

    @Override
    public void setSearchHotkey(List<SearchHotkeyBean.DataBean> dataBeans) {
        if (mView!=null){
            mView.showSearchHotkey(dataBeans);
        }
    }

    @Override
    public void setShowLoading() {

    }

    @Override
    public void setHideLoading() {

    }

    @Override
    public void setShowError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }
}
