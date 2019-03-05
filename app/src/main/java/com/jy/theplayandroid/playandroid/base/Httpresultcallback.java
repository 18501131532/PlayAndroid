package com.jy.theplayandroid.playandroid.base;

public interface Httpresultcallback<T> {
    void requestSucce(T date);
    void requestError(Throwable error);
}
