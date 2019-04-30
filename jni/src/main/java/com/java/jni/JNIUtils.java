package com.java.jni;

public class JNIUtils {
    static {
        //名字注意，需要跟你的build.gradle ndk节点下面的名字一样
        System.loadLibrary("JNIHello");
    }

    public static native String sayHelloFromJNI();

}
