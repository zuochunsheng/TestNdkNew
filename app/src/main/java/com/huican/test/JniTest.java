package com.huican.test;


/**
 * anther: created by zuochunsheng on 2019/7/19 21 : 38
 * description :
 */
public class JniTest {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String transNative();
}
