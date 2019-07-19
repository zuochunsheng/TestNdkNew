# TestNdkNew
Android Studio 配置一键生成 JNI 头文件工具
windows
javah
-cp $Classpath$ -d $ModuleFileDir$\src\main\cpp\inc $FileClass$

linux
/usr/bin/javah

-v -jni         -d $ModuleFileDir$/src/main/cpp/inc $FileClass$