package com.liumq.thread.blockingqueue;

import java.sql.Time;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列 队列容量为1 的阻塞队列
 */
public class SynchonousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<Object> queue = new SynchronousQueue<>();

        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName()+"put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName()+"put 3");
                queue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{

            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"->get:"+queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"->get:"+queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"->get:"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"T2").start();
    }
}
