package com.liumq.thread;

/**
 * 基本的买票多线程例子
 * <p>
 * 在公司的开发中，为了减少耦合度
 * 线程就是一个单独的资源类，没有任何附属操作，（不实现接口）
 * 1 属性2 方法
 */
public class SaleTicket {

    public static void main(String[] args) {
        //并发：多线程操作同一个资源类,把资源类丢入线程

        Ticket ticket = new Ticket();
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
 */
class Ticket {
    //属性方法
    private int number = 50;

    //卖票的方式
    //synchronized本质：排队 锁
    public synchronized  void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出来第：" + (number--) + "票,剩余:" + number);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
