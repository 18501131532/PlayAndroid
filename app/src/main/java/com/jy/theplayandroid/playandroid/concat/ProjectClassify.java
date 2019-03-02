package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;

import java.util.List;

public interface ProjectClassify {
    interface ProjectClassifyV extends Base_View{
        void showProjectClassify(List<ProjectClassifyData.DataBean> dataBeans);
    }

    interface ProjectClassifyP {
        void getProjectClassify();
    }

    interface ProjectClassifyCallBack extends HttpFinishCallBack {
        void setProjectClassify(List<ProjectClassifyData.DataBean> dataBeans);
    }
}
