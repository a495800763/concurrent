package com.liumq.thread.functional;

import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer, 消费型接口，只有输入没有返回值
 *
 * 消费传进来的参数，对参数做处理
 *
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("out: " + s);
            }
        };


        consumer.accept("我是测试类");

    }
}
