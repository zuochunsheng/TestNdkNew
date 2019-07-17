/*动态注册*/
#include <jni.h>
#include <string>
#include "../inc/logUtils.h"


extern "C" {

jstring stringFromJNI(JNIEnv *env, jobject instance) {
    std::string hello = "Hello from C++ 动态注册";
    return env->NewStringUTF(hello.c_str());
}

jint add(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a + b;
}

jint RegisterNatives(JNIEnv *env) {
    jclass clazz = env->FindClass("com/huican/test/SampleActivity");
    if (clazz == NULL) {
        LOGE("con't find class: com/huican/test/SampleActivity");
        return JNI_ERR;
    }
    JNINativeMethod methods_SampleActivityy[] = {
            {"stringFromJNI", "()Ljava/lang/String;", (void *) stringFromJNI},
            {"add",           "(II)I",                (void *) add}
    };
    // int len = sizeof(methods_MainActivity) / sizeof(methods_MainActivity[0]);
    return env->RegisterNatives(clazz, methods_SampleActivityy,
                                sizeof(methods_SampleActivityy) / sizeof(methods_SampleActivityy[0]));
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    jint result = RegisterNatives(env);
    LOGD("RegisterNatives result: %d", result);
    return JNI_VERSION_1_6;
}

}
