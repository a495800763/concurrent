package com.liumq.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 问题7 一个对象 一个静态同步 一个普通同步方法 谁先输出
 * 答案 ：打电话 虽然调用对象是一个 但是锁的是两个不同的东西执行过程中
 *  不需要等待对方释放锁。打电话在主线程延迟1s
 * 后就可以输出，发短信在A线程中sleep4s 后才输出
 *
 * 问题8 两个对象 一个静态同步 一个普通同步方法 谁先输出
 * 答案 ：打电话 。 不论调用的对象是几个 ，锁的对象根据是否static 区别为两个不相关的东西，执行过程中
 * 不需要等待对方释放锁。打电话在主线程延迟1s
 *   后就可以输出，发短信在A线程中sleep4s 后才输出
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(() -> {
            phone1.sendMessage();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();


    }
}

class Phone4 {
    /**
     * 静态同步方法， 锁的是类的class
     */
    public static synchronized void sendMessage() {

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    /**
     * 普通同步方法 锁的是调用的对象object
     */
    public synchronized void call() {
        System.out.println("打电话");
    }

}

