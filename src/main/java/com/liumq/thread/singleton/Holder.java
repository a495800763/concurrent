package com.liumq.thread.singleton;

/**
 * 静态内部类实现单例模式
 *
 * 炫技
 *
 * 不是线程安全的
 *
 */
public class Holder {

    private Holder() {

    }

    public static Holder getInstance() {
        return InnerClass.INSTANCE;
    }

    public static class InnerClass {
        private static final Holder INSTANCE = new Holder();
    }
}
