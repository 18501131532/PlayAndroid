package com.jy.theplayandroid.playandroid.playandroid.daohang.bean;

public class Bean {
    String title;
    String username;
    String password;
    boolean select;

    public Bean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
