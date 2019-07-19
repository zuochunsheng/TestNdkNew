//
// Created by bukes on 2019/7/19.
//

#include <jni.h>
#include <string>
#include <android/log.h>

/*#include "../inc/LogUtils.h"*/

//定义输出的TAG
const char * LOG_TEST = "LOG_TEST";

extern "C" {


JNIEXPORT jstring JNICALL
Java_com_huican_test_SampleActivity_stringFromJNITest(
        JNIEnv *env, jobject instance) {
    std::string hello = "native-test:Hello from C++ 静态注册";
    //输出debug级别的日志信息  ANDROID_LOG_ERROR
    __android_log_print(ANDROID_LOG_DEBUG, LOG_TEST, "native-test hello native log");

    //LOGE("%s : %s", LOG_TEST,"native-test:hello native log");
    return env->NewStringUTF(hello.c_str());
}
}
