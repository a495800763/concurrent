package com.liumq.thread.productorconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试JUC 包中的condition接口 替代Synchronized关键字和wait notify模式
 * <p>
 * 但是这种模式 仍然有一个问题，不能直接精准的唤醒指定线程
 * 需要进一步对condition进行操作
 * 详见类  TestJucNew
 */
public class TestJuc {
    public static void main(String[] args) {
        DataB data = new DataB();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}


/**
 * 数字 资源类
 */
class DataB {
    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    //condition.await();
    //condition.signalAll();


    /**
     * +1 操作
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //try块中写业务代码
            // if 判断wait 存在虚假唤醒问题  多个消费者时 应使用while 判断 唤醒后需要再次判断条件
            while (num != 0) {
                // 等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //通知其他线程，本线程+1 操作完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * -1 操作
     */
    public void decrement() throws InterruptedException {

        lock.lock();
        try {
            //try块中写业务代码
            // if 判断wait 存在虚假唤醒问题 ：多个消费者时 应使用while 判断 唤醒后需要再次判断条件
            while (num == 0) {
                //等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //通知其他线程，本线程-1 操作完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


}
