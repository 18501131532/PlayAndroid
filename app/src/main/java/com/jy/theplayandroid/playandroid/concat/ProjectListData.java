package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.ProjectListBean;

import java.util.List;

public interface ProjectListData {
    interface ProjectListV extends Base_View {
        void showProjectList(List<ProjectListBean.DataBean.DatasBean> dataBeans);
    }

    interface ProjectListP {
        void getProjectList(int page,int cid);
    }

    interface ProjectListCallBack extends HttpFinishCallBack {
        void setProjectList(List<ProjectListBean.DataBean.DatasBean> dataBeans);
    }
}
