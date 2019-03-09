package com.jy.theplayandroid.playandroid.playandroid.daohang.manager;

import com.jy.theplayandroid.playandroid.dao.DaoMaster;
import com.jy.theplayandroid.playandroid.dao.DaoSession;
import com.jy.theplayandroid.playandroid.dao.DateBaseDao;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.bean.DateBase;

import java.util.List;

public class LikeDataBaseMannger {
    private static LikeDataBaseMannger mDataBaseMannger;
    private final DateBaseDao mDateBaseDao;

    public static LikeDataBaseMannger getInstrance() {
        if (mDataBaseMannger == null) {
            synchronized (LikeDataBaseMannger.class) {
                if (mDataBaseMannger == null) {
                    mDataBaseMannger = new LikeDataBaseMannger();
                }
            }
        }
        return mDataBaseMannger;
    }

    LikeDataBaseMannger() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(), "person.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mDateBaseDao = daoSession.getDateBaseDao();
    }

    public void insert(List<DateBase> list) {
        mDateBaseDao.insertInTx(list);
    }

    public void delete(DateBase person) {
        mDateBaseDao.delete(person);
    }

    public void deleteAll() {
        mDateBaseDao.deleteAll();
    }

    public void update(DateBase person) {
        mDateBaseDao.update(person);
    }

    public List<DateBase> selectAll() {
        return mDateBaseDao.queryBuilder().list();
    }

    public List<DateBase> selectId(String id) {
        return mDateBaseDao.queryBuilder().where(DateBaseDao.Properties.MId.eq(id)).list();
    }

    public List<DateBase> selectPage(int page, int count) {
        return mDateBaseDao.queryBuilder().offset(page * count).limit(count).list();
    }
}
