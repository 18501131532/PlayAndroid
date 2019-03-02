package com.jy.theplayandroid.playandroid.playandroid.xiangmu.moudle;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.concat.ProjectListData;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.util.RxUtils;

import java.util.List;

public class IMProjectListData {
    public void getProjectListData(int page, int cid, final ProjectListData.ProjectListCallBack projectListCallBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getProjectList(page,cid)
                .compose(RxUtils.<ProjectListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectListBean>(projectListCallBack) {
                    @Override
                    public void onNext(ProjectListBean value) {
                        List<ProjectListBean.DataBean.DatasBean> datas = value.getData().getDatas();
                        projectListCallBack.setProjectList(datas);
                    }
                });
    }
}
