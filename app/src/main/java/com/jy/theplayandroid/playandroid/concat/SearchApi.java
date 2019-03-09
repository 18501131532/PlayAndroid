package com.jy.theplayandroid.playandroid.concat;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;
import com.jy.theplayandroid.playandroid.bean.SearchBean;

public interface SearchApi {
     interface SearchV extends Base_View{
        void showSearch(SearchBean searchBean);
    }
     interface SearchM extends HttpFinishCallBack{
        void setSearch(SearchBean search);
    }
     interface SearchP{
        void getSearch(String k);
    }
}
