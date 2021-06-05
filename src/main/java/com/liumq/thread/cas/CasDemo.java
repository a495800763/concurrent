package com.liumq.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 操作demo：
 * Compare And Swap: 比较并交换
 */
public class CasDemo {


    //CAS
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        /**
         * 期望、更新
         *  public final boolean compareAndSet(int expect, int update)
         *  期望的值满足 ，则更新
         *  否则 不更新
         *  CAS 是 CPU 的并发原语!!
         *  自旋锁
         *
         *  底层 比较并交换
         *
         */
        System.out.println(atomicInteger.compareAndSet(2020, 2021));

        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2021, 2020));

        System.out.println(atomicInteger.get());
    }
}
