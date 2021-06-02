package com.liumq.thread.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁（共享锁 独占锁）
 *
 * 读锁 共享锁
 * 写锁 独占锁
 *
 * 读读  可以共存
 * 读写   不能共存
 * 写写  更不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock cache = new MyCacheLock();
        //写入
        for (int i = 1; i <= 1000; i++) {
            Integer temp = i;
            new Thread(() -> {
                cache.put(temp+"",temp+"");
            }, String.valueOf(i)).start();
        }


        //读取
        for (int i = 1; i <= 1000; i++) {
            Integer temp = i;
            new Thread(() -> {
                cache.get(temp+"");
            }, String.valueOf(i)).start();
        }
    }

}

/**
 * 自定义缓存
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 存
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK" + key);

    }

    /**
     * 读取
     *
     * @return
     */
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK" + key);
    }


}


/**
 * 枷锁的自定义缓存
 */
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 读写锁 更加细粒度的控制
     */
    private ReentrantReadWriteLock lock =  new ReentrantReadWriteLock();

    /**
     * 存，写的时候只有一个写线程
     */
    public void put(String key, Object value) {
        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK" + key);

        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();
        }




        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK" + key);

    }

    /**
     * 读取，读的时候可以有多个读线程，不能有写线程
     *
     * @return
     */
    public void get(String key) {


        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK" + key);

        }catch (Exception e){

        }finally {
            lock.readLock().unlock();
        }

       }


}