package com.jy.theplayandroid.playandroid.playandroid.daohang.bean;

public class Bean {
    String title;
    boolean select;

    public Bean() {
    }

    public Bean(String title, boolean select) {
        this.title = title;
        this.select = select;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
