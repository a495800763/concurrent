package com.liumq.thread.functional;

import java.util.function.Predicate;

/**
 * 谓词函数式接口 demo
 * 有一个输入参数，返回一个对参数的判断 boolean类型 
 */
public class PredicateDemo {
    public static void main(String[] args) {
        //判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };


        System.out.println(predicate.test("asdasd"));
    }
}
