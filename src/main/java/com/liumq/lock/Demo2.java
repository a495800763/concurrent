package com.liumq.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {
    public static void main(String[] args) {
        Phone3 phone = new Phone3();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone3 {

    Lock lock = new ReentrantLock();


    public void sms() {
        lock.lock();  //细节问题，两把锁
        try {
            System.out.println(Thread.currentThread().getName() + "发短信");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();//细节问题，两把锁
        try {
            System.out.println(Thread.currentThread().getName() + "打电话");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
