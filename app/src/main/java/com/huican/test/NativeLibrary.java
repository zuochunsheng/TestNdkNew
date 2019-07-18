package com.huican.test;

import com.huican.test.bean.DataBean;

/**
 * @author： zcs
 * @time：2019/7/18 on 13:12
 * @description：
 */
public class NativeLibrary {

    static {
        System.loadLibrary("native-lib");
    }
    // 将C结构体转为Java类
    public static native DataBean getDataFromNative();
    // 将Java类转为C结构体
    public static native void transferDataToNative(DataBean dataBean);

}
