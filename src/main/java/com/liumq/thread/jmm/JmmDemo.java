package com.liumq.thread.jmm;

public class JmmDemo {

    /**
     *
     * 可见性： 不加volatile程序将会死循环，因为子线程中感知不到主线程对num 的修改
     * 加了volatile 后，程序将会很快停止，因为volatile保证可见性后
     * 子线程可以感知到主线程对num 的修改
     */
    private volatile  static  int num = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            while (num == 0) {
                //子线程当值为0 时一直执行，不为0 时执行应该能结束，真的这样吗
                //System.out.println("do sth");
            }
        }).start();


        Thread.sleep(1000);


        //主线程将值设置为1
        num = 1;
        System.out.println("主线程已经将值设置为1 了");
    }
}
