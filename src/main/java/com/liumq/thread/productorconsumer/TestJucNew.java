package com.liumq.thread.productorconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class TestJucNew {
    public static void main(String[] args) {

        Data3 data = new Data3();


        //A执行完再通知B 再执行完通知C
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

class Data3 {
    private Lock lock = new ReentrantLock();
    //同步监视器
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int number = 1;// 1A 2B 3C

    public void printA() {
        lock.lock();
        try {
            //业务代码 判断->执行->通知
            while (number != 1) {
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "：正在执行");
            //唤醒，唤醒指定的人，B
            //将number置成2 ，AC 卡在while循环中，
            //B被signal后再次判断while条件后出while条件开始执行
            number = 2;
            //指定唤醒2的监视器
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            //业务代码 判断->执行->通知
            while (number != 2) {
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "：正在执行");
            //唤醒，唤醒指定的人，C
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //业务代码 判断->执行->通知
            while (number != 3) {
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "：正在执行");
            //唤醒，唤醒指定的人，A
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
