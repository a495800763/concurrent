package com.liumq.thread.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不保证原子性的测试
 */
public class VolatileDemo {
    private static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //AtomicInteger的自增方法,底层原理CAS
        //使用Unsafe类直接操作底层，与操作系统挂钩，在内存中直接修改值
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        /*二十个线程各执行add 方法1000次
         * 理论上结果应该是20000*/
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }, Thread.currentThread().getName()).start();
        }
        while (Thread.activeCount() > 2) {
            //当当前活跃线程数大于2个（main  gc ）
            //说明还有其他add 线程没执行完
            //main线程礼让（yield）
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "--->" + num);
    }
}
