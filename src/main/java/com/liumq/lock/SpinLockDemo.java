package com.liumq.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 */
public class SpinLockDemo {

    //Thread null
    AtomicReference<Thread> atomicReference=new AtomicReference<>();


    // 加锁
   public void myLock(){
       Thread thread = Thread.currentThread();
       System.out.println(Thread.currentThread().getName()+"-->mylock");

       while (!atomicReference.compareAndSet(null,thread))
       {

       }
   }

    // 解锁
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"-->myUnLock");

        atomicReference.compareAndSet(thread,null);
    }
}

