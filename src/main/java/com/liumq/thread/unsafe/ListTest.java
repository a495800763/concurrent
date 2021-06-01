package com.liumq.thread.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 不安全的List测试
 * <p>
 * java.util.ConcurrentModificationException  并发修改异常
 */
public class ListTest {
    public static void main(String[] args) {
        //并发下 arrayList 不安全
        /**
         * 解决方案 ：
         * 1 List<String> list = new Vector<>(); 太老了 不能得要点
         * 2 Collections.synchronizedList();  Collections工具类 不是JUC 的方案
         * 3 List<String> list = new CopyOnWriteArrayList<>();  底层： private transient volatile Object[] array;
         */

        // CopyOnWrite ： 写入时复制： COW思想：计算机程序设计领域的一种优化思想
        //多个线程调用时，唯一list , 读取是固定的，写入时复制保存备份
        // 多线程写入时避免因覆盖引起的数据问题
        // 读写分离: myCat


        //CopyOnWriteArrayList 比Vector NB 在哪
        // Vector 使用synchonized 修饰  效率比较低
        //CopyOnWriteArrayList 使用 Lock
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
