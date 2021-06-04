package com.liumq.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 使用forkjoin 进行求和计算的任务
 * <p>
 * 如何使用forkjoin
 * 1 forkjoinpool 通过它来执行
 * 2 计算任务 forkjoinpool.execute(ForkJoinTask task)
 * 3 ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    //临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {

    }


    /**
     * 计算方法
     *
     * @return
     */
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            //范围不大：正常计算
            Long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //forkjoin
            //中间值
            long middle = (start + end) / 2;
            ForkJoinDemo demo1 = new ForkJoinDemo(start, middle);
            demo1.fork();//拆分任务，把任务压入线程队列
            ForkJoinDemo demo2 = new ForkJoinDemo(middle, end);
            demo2.fork();

            long sum = demo1.join() + demo2.join();
            return sum;

        }
    }
}
