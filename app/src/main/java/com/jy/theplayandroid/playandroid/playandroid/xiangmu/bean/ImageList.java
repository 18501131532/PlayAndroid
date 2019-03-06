package com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ImageList {
    @Id(autoincrement = true)
    private Long id;
    private boolean one;
    private boolean two;
    @Generated(hash = 105672183)
    public ImageList(Long id, boolean one, boolean two) {
        this.id = id;
        this.one = one;
        this.two = two;
    }
    @Generated(hash = 201951210)
    public ImageList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getOne() {
        return this.one;
    }
    public void setOne(boolean one) {
        this.one = one;
    }
    public boolean getTwo() {
        return this.two;
    }
    public void setTwo(boolean two) {
        this.two = two;
    }
}
