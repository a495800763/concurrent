package com.liumq.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象初步
 * 关于锁的八个问题
 *
 *
 * A 线程B线程谁先执行
 * 答案： A
 * 为什么： 因为 方法中的synchronized 关键字锁的是方法的调用者这个对象
 * A B 线程 都是 phone 这个对象，而A 线程在 Thread.sleep()方法前 ，
 * 绝对比B 先拿到phone锁
 * 所以就算B线程先的start（线程具体执行的顺序），也会等待A线程执行完毕释放phone对象锁
 */
public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();



        new Thread(() -> {
            //System.out.println("调用发短信");
            phone.sendMessage();
        }, "A").start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(() -> {
            //System.out.println("调用打电话");
            phone.call();
        }, "B").start();


    }
}

class Phone {
    public synchronized void sendMessage() {

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
