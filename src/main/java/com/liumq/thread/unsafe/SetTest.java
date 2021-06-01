package com.liumq.thread.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理可证：ConcurrentModificationException
 */
public class SetTest {
    public static void main(String[] args) {

        //Set<String> set = new HashSet<>();
        /**
         * 解决方案 ：
         * 1  Collections.synchronizedSet(new HashSet<>())
         * 2  CopyOnWriteArraySet
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
