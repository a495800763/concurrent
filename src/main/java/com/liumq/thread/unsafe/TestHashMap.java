package com.liumq.thread.unsafe;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TestHashMap {
    public static void main(String[] args) {

        //map = new HashMap<>(); 这样的初始化在工作中使用吗
        //回答： 工作中一般不用HashMap
        //无参的构造函数默认等于什么
        // 回答 加载因子是0.75,初始容量是 16  HashMap<>(16,0.75)
        //加载因子，初始化容量


        /**
         * 解决方案 ：
         * 1 Collections.synchronizedMap(new HashMap<>());
         * 2 new ConcurrentHashMap<>();
         */
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
//              set.add(UUID.randomUUID().toString().substring(0, 5));
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
