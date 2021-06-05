package com.liumq.thread.singleton;

/**
 * 枚举的值就是一个单例对象
 * 而且当类型是枚举时
 * 使用反射破坏其私有构造函数的过程
 * 底层代码会报错
 */
public enum Singleton {
    INSTANCE;


    private Singleton(){

    }

    public void doSomething(){
        System.out.println("instance call");
    }

    public static void main(String[] args) {
        Singleton.INSTANCE.doSomething();
    }
}
