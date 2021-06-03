package com.liumq.thread.functional;

import java.util.function.Function;

/**
 * Function 函数式接口 ，传入一种类型参数T 输出一种类型返回值R
 * 只要是函数式接口，就可以用lambda 表达式简化编程
 */
public class Test {
    public static void main(String[] args) {
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return "Result is --》" + str;
//            }
//        };


        Function function = (s) -> {

            return  Integer.valueOf((String) s);
        };


        System.out.println(function.apply("1222"));
    }
}
