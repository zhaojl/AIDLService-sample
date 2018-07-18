package com.senior.client;

/**
 * 单例的7种实现方式
 *
 * https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
 *
 */

import android.content.Context;

public class SettingsDbHelper {
    private Context mContext;

    public SettingsDbHelper() {
    }

    public SettingsDbHelper(Context context) {
        this.mContext = context;
    }

    //第一种（懒汉，线程不安全）
    private static SettingsDbHelper instance = null;
    public static SettingsDbHelper getInstance() {
        if(instance == null) {
            instance = new SettingsDbHelper();
        }
        return instance;
    }

    //第二种（懒汉，线程安全）
    private static SettingsDbHelper instance2 = null;
    public static synchronized SettingsDbHelper getInstance2() {
        if(instance2 == null) {
            instance2 = new SettingsDbHelper();
        }
        return instance2;
    }

    //第三种（饿汉）
    /**
     * 说明：当类第一次加载时就会初始化静态变量
     * 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例，不管你用不用，先创建出来，然后每次调用的时候，就不需要再判断，节省了运行时间
     */
    private static SettingsDbHelper instance3 = new SettingsDbHelper();
    public static SettingsDbHelper getInstance3() {
        return instance3;
    }

    //第四种（饿汉，变种）
    private static SettingsDbHelper instance4 = null;
    static {
        instance4 = new SettingsDbHelper();
    }
    public static SettingsDbHelper getInstance4() {
        return instance4;
    }

    private static SettingsDbHelper instance5 = null;
    private static SettingsDbHelper getInsstance() {
        if(instance5 == null) {
            synchronized (SettingsDbHelper.class) {
                instance5 = new SettingsDbHelper();
            }
        }
        return instance5;
    }


    /**
     * 能够延迟初始化（lazy initialization），并且多线程安全，还能保证高性能
     * 延迟初始化，这里是利用了Java的语言特性，内部类只有在使用的时候，才会去加载，从而初始化内部静态变量。
     * 关于线程安全，这是Java运行环境自动给你保证的，在加载的时候，会自动隐形的同步。在访问对象的时候，不需要同步Java虚拟机又会自动给你取消同步，所以效率非常高。
     */
    //第五种（静态内部类）
    private static class HelperHolder {
        private static final SettingsDbHelper HELPER = new SettingsDbHelper();
    }
    public static final SettingsDbHelper getHelper() {
        return HelperHolder.HELPER;
    }


    //第六种（枚举）
    public enum Singleton{
        instance;
        public void singletonOperation(){

        }
    }


    //第七种 双重校验锁——第二种方式的升级版 ——Double-checked locking
    private static volatile SettingsDbHelper sInst = null;
    public static SettingsDbHelper getInstance(Context context) {
        SettingsDbHelper inst = sInst;
        if(inst == null) {
            synchronized (SettingsDbHelper.class){
                inst = sInst;
                if(inst == null) {
                    inst = new SettingsDbHelper(context);
                    sInst = inst;
                }
            }
        }
        return inst;
    }
}
