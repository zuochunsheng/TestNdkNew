package com.huican.test;

import android.support.multidex.MultiDexApplication;


/**
 * @author： zcs
 * @time：2019/7/9 on 14:25
 * @description：
 */
public class KC_application extends MultiDexApplication {



    private static KC_application instance;
    public static synchronized KC_application getInstance() {
        if (instance == null) {
            instance = new KC_application();
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
       // HttpManager.setHTTP_URL(Config.APP_URL);



    }
}
