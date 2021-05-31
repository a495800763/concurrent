package com.liumq.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 问题3 这两个线程执行完 A执行sendMessage B执行hello 谁先打印，增加了普通方法 hello
 * 答案： hello
 * <p>
 * 先调用A 锁住phone  但是A 要等4秒输出
 * 主线程等1s后调用B 没有加锁不需要等A结束直接输出 hello
 * 此时A还在4秒自身线程的sleep过程中 A 后输出
 *
 * 问题4 两个对象phone1 phone2 分别在AB 线程中执行Synchonized方法，谁先执行，谁先输出
 * 答案：打电话先输出，发短信先执行
 *
 * 虽然还是A 先调用 A 先获取 phone1 锁 ，但是B后获取的是另一把锁phone2 ，两者不是同一个锁不相关
 * 开始执行方法后 A 线程自身被方法延时4s 所以肯定是B方法先执行输出
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();


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

class Phone2 {
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

    public void hello() {
        System.out.println("hello ");
    }
}
