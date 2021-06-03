package com.liumq.thread.pool;

import java.util.concurrent.*;

/**
 * Executors 工具类三大线程池创建方法
 * <p>
 * 使用线程池来运行线程
 */
public class Demo1 {
    public static void main(String[] args) {
        //ExecutorService pool = Executors.newSingleThreadExecutor();//只有单个线程的线程池
        //ExecutorService pool = Executors.newFixedThreadPool(5);//线程池大小个固定为5个
        //ExecutorService pool = Executors.newCachedThreadPool();//可伸缩大小的线程池

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        try {
            //最大承载： queue.length+max
            //超过最大承载 启动拒绝策略
            for (int i = 1; i <=9; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "-> OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //程序结束后，关闭线程池
            pool.shutdown();
        }


    }
}
