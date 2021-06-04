package com.liumq.thread.functional;

public class Supplier {
    public static void main(String[] args) {
        java.util.function.Supplier<String> supplier = new java.util.function.Supplier<String>() {
            @Override
            public String get() {
                return "我是Supplier测试类";
            }
        };

        System.out.println(supplier.get());
    }
}
