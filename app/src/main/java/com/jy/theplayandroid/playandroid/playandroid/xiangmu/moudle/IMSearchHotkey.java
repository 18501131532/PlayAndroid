package com.jy.theplayandroid.playandroid.playandroid.xiangmu.moudle;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.concat.SearchHotkey;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.bean.SearchHotkeyBean;
import com.jy.theplayandroid.playandroid.utils.RxUtils;

import java.util.List;

public class IMSearchHotkey {
    public void getSearchHotkey(final SearchHotkey.SearchHotkeyCallBack searchHotkeyCallBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getSearchHotkey()
                .compose(RxUtils.<SearchHotkeyBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SearchHotkeyBean>(searchHotkeyCallBack) {
                    @Override
                    public void onNext(SearchHotkeyBean value) {
                        List<SearchHotkeyBean.DataBean> data = value.getData();
                        searchHotkeyCallBack.setSearchHotkey(data);
                    }
                });
    }
}
