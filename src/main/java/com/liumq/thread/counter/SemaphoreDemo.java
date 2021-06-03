package com.liumq.thread.counter;

import java.util.concurrent.Semaphore;

/**
 * 信号量demo
 * 用信号量实现批量执行
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //每次允许三个，超过三个会阻塞，等待执行完之后再提交
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ":开始执行");
                    //Thread.sleep((long) (Math.random() * 1000));
                    System.out.println(Thread.currentThread().getName() + ":结束");
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }, String.valueOf(i)).start();
        }

    }
}
