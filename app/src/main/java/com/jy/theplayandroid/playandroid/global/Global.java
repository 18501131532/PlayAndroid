package com.jy.theplayandroid.playandroid.global;

import java.io.File;

/**
 * Created by 段傅华 on 2019/1/18.
 */

public class Global {
   /*
   * 存储常量
   * */
   public static String BASE_URL="http://www.wanandroid.com/";

   /**
    * Path
    */
   public static final String PATH_DATA = MyApp.getMyApp().getCacheDir().getAbsolutePath() + File.separator + "data";
}
