package com.liumq.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基本的买票多线程例子
 * <p>
 * 在公司的开发中，为了减少耦合度
 * 线程就是一个单独的资源类，没有任何附属操作，（不实现接口）
 * 1 属性2 方法
 */
public class SaleTicketLock {

    public static void main(String[] args) {
        //并发：多线程操作同一个资源类,把资源类丢入线程

        Ticket2 ticket = new Ticket2();
        // Runnable: @FunctionalInterface 函数式接口
        //其实是实现了 runnable接口，接口的run 方法中调用资源类的操作方法
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}


/**
 * 资源类  OOP 编程
 * Lock三部曲
 * 1 new ReentrantLock();
 * 2 lock.lock();
 * 3 lock.unlock();
 */
class Ticket2 {
    //属性方法
    private int number = 50;

    //默认非公平锁
    //公平锁：先来后到
    //非公平锁: 可以插队
    Lock lock = new ReentrantLock();

    //卖票的方式
    public void sale() {
        //加锁
        lock.lock();

        //尝试获取锁
        //lock.tryLock();

        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出来第：" + (number--) + "票,剩余:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
