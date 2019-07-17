/*静态注册*/


#include <jni.h>
#include <string>
//{sdk-path}/ndk-bundle/sysroot/usr/include/android
#include <android/log.h>
#include <android/bitmap.h>

/*导入日志 -相对路径*/
/*#include "Log.hpp"*/
#include "../inc/LogUtils.h"

//定义输出的TAG
const char * LOG_TGA = "LOG_TGA";




extern "C" {


JNIEXPORT jstring JNICALL
Java_com_huican_test_SampleActivity_stringFromJNI(
        JNIEnv* env, jobject instance) {
    std::string hello = "Hello from C++ 静态注册";
    //输出debug级别的日志信息  ANDROID_LOG_ERROR
    __android_log_print(ANDROID_LOG_DEBUG, LOG_TGA, "hello native log");

    return env->NewStringUTF(hello.c_str());
}


/*
 * Class:     com_huican_test_SampleActivity
 * Method:    test
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_com_huican_test_SampleActivity_test
        (JNIEnv *env, jobject obj) {
    //输出日志信息
    LOGE("hello world");
    LOGE("%d", 10);
    LOGE("%s : %d", "num", 20);
    LOGE();

}
/*
 * Class:     com_huican_test_SampleActivity
 * Method:    passBitmap
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_com_huican_test_SampleActivity_passBitmap
        (JNIEnv *env, jobject instance, jobject bitmap){

    if (NULL == bitmap) {
        LOGE("bitmap is null!");
        return;
    }
    AndroidBitmapInfo info; // create a AndroidBitmapInfo
    int result;
    // 获取图片信息
    result = AndroidBitmap_getInfo(env, bitmap, &info);
    if (result != ANDROID_BITMAP_RESULT_SUCCESS) {
        LOGE("AndroidBitmap_getInfo failed, result: %d", result);
        return;
    }
    LOGD("bitmap width: %d, height: %d, format: %d, stride: %d", info.width, info.height,
         info.format, info.stride);
    // 获取像素信息
    unsigned char *addrPtr;
    result = AndroidBitmap_lockPixels(env, bitmap, reinterpret_cast<void **>(&addrPtr));
    if (result != ANDROID_BITMAP_RESULT_SUCCESS) {
        LOGE("AndroidBitmap_lockPixels failed, result: %d", result);
        return;
    }
    // 执行图片操作的逻辑
    int length = info.stride * info.height;
    for (int i = 0; i < length; ++i) {
        LOGD("value: %x", addrPtr[i]);
    }
    // 像素信息不再使用后需要解除锁定
    result = AndroidBitmap_unlockPixels(env, bitmap);
    if (result != ANDROID_BITMAP_RESULT_SUCCESS) {
        LOGE("AndroidBitmap_unlockPixels failed, result: %d", result);
    }


}


}


