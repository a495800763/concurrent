package com.liumq.thread.singleton;

/**
 * 饿汉式单例模式
 */
public class Hungry {

    /**
     * 饿汉式如果类中还开辟了其他数据，可能会浪费空间
     */
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private Hungry() {

    }

    /**
     * 程序一跑起来就加载
     */
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
