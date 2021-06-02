package com.liumq.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 细节：
 * 1 有缓存
 * 2 结果可能需要等待 有阻塞
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> target = new FutureTask<>(new MyThread());
        new Thread(target).start();

        Integer integer = (Integer)target.get();

        System.out.println(integer);


    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("call");
        return 1024;
    }
}
