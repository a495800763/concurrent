package com.liumq.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步调用 CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //发起一个请求
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务执行");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务执行结束");
            }
        });

        while (!completableFuture.isDone())
        {
            Void aVoid = completableFuture.get();
        }
    }
}
