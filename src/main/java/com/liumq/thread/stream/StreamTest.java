package com.liumq.thread.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 流式计算测试类
 * 目标： 现在有五个用户，筛选
 * 1 id必须是偶数
 * 2 年龄必须大于23岁
 * 3 用户名称转为大写字母
 * 4 用户名称字母倒叙
 * 5 只输出一个用户
 */
public class StreamTest {

    public static void main(String[] args) {

        User user1 = new User(1, "a", 21);
        User user2 = new User(2, "b", 22);
        User user3 = new User(3, "c", 23);
        User user4 = new User(4, "d", 24);
        User user5 = new User(5, "e", 25);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);

        /**
         * id必须是偶数
         */
        list.stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() % 2 == 0;
            }
        }).forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });

        System.out.println("================");


        /**
         * 年龄必须大于23
         */
        list.stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getAge() > 23;
            }
        }).forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });

        System.out.println("================");
        /**
         * 用户名转换为大写
         */
        list.stream().map(new Function<User, User>() {
            @Override
            public User apply(User s) {
                s.setName(s.getName().toUpperCase());
                return s;
            }
        }).forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });



        System.out.println("================");
        /**
         * 用户名称字母倒叙
         *
         */
        list.stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getName().compareTo(o1.getName());
            }
        }).forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }

}
