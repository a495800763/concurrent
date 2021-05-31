package com.liumq.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5：增加两个静态同步方法,调用对象只有一个 谁先输出
 * 6：两个静态同步方法,调用对象有两个 谁先输出
 *
 * 5 6答案一样 ：
 * 锁静态方法时 ，锁的是调用的对象的类class本身，在JVM 加载时初始化且全局唯一
 * 因此一定是同一把锁, A 先获取锁先执行B等A 执行完释放锁才执行，因此一定是A 先输出
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

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

class Phone3 {
    public static synchronized void sendMessage() {

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }

}
