package com.jy.theplayandroid.playandroid.utils;

public class ServerException extends Exception{
    private int mCode;

    public ServerException(int code , String message){
        super(message);
        mCode = code;
    }
    public ServerException(int code , String message, Throwable throwable){
        super(message,throwable);
        mCode = code;
    }

    public int getCode() {
        return mCode;
    }

}
