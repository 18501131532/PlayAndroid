package com.jy.theplayandroid.playandroid.playandroid.xiangmu.moudle;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.concat.ProjectClassify;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.utils.RxUtils;

import java.util.List;

public class IMProjectClassify {
    public void getProjectClassify(final ProjectClassify.ProjectClassifyCallBack projectClassifyCallBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getProjectfenlei()
                .compose(RxUtils.<ProjectClassifyData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectClassifyData>(projectClassifyCallBack) {
                    @Override
                    public void onNext(ProjectClassifyData value) {
                        List<ProjectClassifyData.DataBean> data = value.getData();
                        projectClassifyCallBack.setProjectClassify(data);
                    }
                });
    }
}
