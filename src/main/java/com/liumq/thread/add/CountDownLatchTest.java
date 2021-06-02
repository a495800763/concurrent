package com.liumq.thread.add;

import java.util.concurrent.CountDownLatch;

/**
 * 辅助类 CountDownLatch 测试
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //总数是6,必须要等任务全部执行完再执行后续代码时执行 减法计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"Go Out");
                countDownLatch.countDown();//数量减1
            },String.valueOf(i)).start();
        }

        //等待计数器归零，然后在向下执行
        countDownLatch.await();
        System.out.println("closed door");

    }
}
