package com.jy.theplayandroid.playandroid.http;

import com.jy.theplayandroid.playandroid.dao.DaoMaster;
import com.jy.theplayandroid.playandroid.dao.DaoSession;
import com.jy.theplayandroid.playandroid.dao.ImageListDao;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ImageList;

import java.util.List;

public class HttpGreendao {
    private static HttpGreendao httpGreendao;
    private final ImageListDao mImageListDao;

    public HttpGreendao() {
        //初始化数据库
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.sMyApp, "playandroid.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //数据库表管理
        DaoSession daoSession = daoMaster.newSession();
        //获取当前实体类的操作对象
        mImageListDao = daoSession.getImageListDao();
    }

    public static HttpGreendao getInstance(){
        if (httpGreendao==null){
            synchronized (HttpGreendao.class){
                if (httpGreendao==null){
                    httpGreendao=new HttpGreendao();
                }
            }
        }
        return httpGreendao;
    }

    //插入
    public void insert(ImageList list) {
        mImageListDao.insertInTx(list);
    }

    //删除
    public void delete(ImageList list) {
        mImageListDao.delete(list);
    }

    //查询
    public List<ImageList> selectImg() {
        return mImageListDao.queryBuilder().list();
    }


    //修改
    public void updata(ImageList list) {
        mImageListDao.update(list);
    }

}
