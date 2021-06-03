package com.liumq.thread.counter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用CyclicBarrier实现的跑步比赛
 */
public class SportingMettingDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier start = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                System.out.println("裁判员准备到位 开始跑步");
            }
        });

        CyclicBarrier end = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("全体运动员已跑到终点开始计算成绩");
            }
        });

        start.await();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--》跑到终点");
                try {
                    end.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
