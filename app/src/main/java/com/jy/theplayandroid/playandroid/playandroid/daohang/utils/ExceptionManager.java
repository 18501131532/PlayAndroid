package com.jy.theplayandroid.playandroid.playandroid.daohang.utils;

import android.content.Context;


public class ExceptionManager {

    //构建一个 serverException
    public static Throwable buildServerErrorMessage(int code,String msg){
        return new ServerException(code, msg);
    }


    //从 throwable 里面提出出 错入信息
    public static String getMsgFromThrowable(Context context, Throwable throwable){
        if(throwable instanceof  ServerException){
            return throwable.getMessage();
        }else{
            return "网络异常，请检查网络";
        }
    }




}
