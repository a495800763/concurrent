package com.liumq.thread.productorconsumer;

/**
 * 测试生产者消费者问题
 * <p>
 * 线程之间的通信问题
 * 线程交替执行  A B 线程操作同一个变量
 * 等待唤醒，通知唤醒  wait notify
 * <p>
 * 生产者消费者八字规律
 * 判断等待，业务，通知
 */
public class Test {
    public static void main(String[] args) {
        Data data = new Data();


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
class Data {
    private int num = 0;

    /**
     * +1 操作
     */
    public synchronized void increment() throws InterruptedException {
        // if 判断wait 存在虚假唤醒问题  多个消费者时 应使用while 判断 唤醒后需要再次判断条件
        while (num != 0) {
            // 等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        //通知其他线程，本线程+1 操作完毕
        this.notifyAll();
    }

    /**
     * -1 操作
     */
    public synchronized void decrement() throws InterruptedException {
        // if 判断wait 存在虚假唤醒问题 ：多个消费者时 应使用while 判断 唤醒后需要再次判断条件
        while (num == 0) {
            //等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        //通知其他线程，本线程-1 操作完毕
        this.notifyAll();
    }


}
