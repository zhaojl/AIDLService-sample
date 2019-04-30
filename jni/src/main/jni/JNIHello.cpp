#include "com_java_jni_JNIUtils.h"
JNIEXPORT jstring JNICALL Java_com_java_jni_JNIUtils_sayHelloFromJNI
        (JNIEnv *env, jclass jclass){
return env->NewStringUTF("Hello World From JNI!!!!!");
}