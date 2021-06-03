package com.liumq.thread.counter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch： 减法计数器
 * 等待线程全部执行完之后再执行后续代码
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        Resource r = new Resource();
        CountDownLatch countDownLatch  = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                try {
                    r.doSth();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("等待线程全部返回后再执行后续代码");
    }


}

class Resource {
    public void  doSth() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-->开始执行");
        //TimeUnit.SECONDS.sleep(3);
        Thread.sleep((long) (Math.random()*2000));
        System.out.println(Thread.currentThread().getName()+"-->执行结束");
    }
}
