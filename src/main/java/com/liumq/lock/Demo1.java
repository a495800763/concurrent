package com.liumq.lock;

/**
 * 可重入锁测试
 * synchronized
 *
 */
public class Demo1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sms();
        },"A").start();

        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone {
    public synchronized  void sms(){
        System.out.println(Thread.currentThread().getName()+"发短信");
        call();
    }

    public synchronized  void call(){
        System.out.println(Thread.currentThread().getName()+"打电话");
    }
}
