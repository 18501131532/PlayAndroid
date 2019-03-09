package com.jy.theplayandroid.playandroid.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DateBase {
    @Id(autoincrement = true)
    Long id;
    boolean mBoolean;
    String mId;

    @Generated(hash = 854053699)
    public DateBase(Long id, boolean mBoolean, String mId) {
        this.id = id;
        this.mBoolean = mBoolean;
        this.mId = mId;
    }

    @Generated(hash = 949644119)
    public DateBase() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getMBoolean() {
        return this.mBoolean;
    }

    public void setMBoolean(boolean mBoolean) {
        this.mBoolean = mBoolean;
    }

    public String getMId() {
        return this.mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }
}
