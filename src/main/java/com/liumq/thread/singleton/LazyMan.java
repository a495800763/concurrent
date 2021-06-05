package com.liumq.thread.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例模式
 */
public class LazyMan {
    private volatile static LazyMan INSTANCE;

    private static boolean initKey = false;

    private LazyMan() {
        synchronized (LazyMan.class) {
            if (initKey == false) {
                //只有真正使用private 构造函数New Instance 才为true,否则报错
                initKey = true;
            } else {
                //解决INSTANCE 已经被New出来后 使用反射new 另外一个INSTANCE
                //但是不能解决两个INSTANCE 都用反射去new 的问题
                //因为反射new出来的不是INSTANCE 指向的
                throw new RuntimeException("不要试图使用反射破坏单例模式构造函数的private性质");
            }
        }
    }

    /**
     * 双重检测锁模式的懒汉式单例模式(DCL单例模式)
     *
     * @return
     */
    public static LazyMan getInstance() {
        if (INSTANCE == null) {
            //加锁
            synchronized (LazyMan.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyMan();//不是原子性操作
                    /**
                     * 1 分配内存空间
                     * 2 执行构造方法，初始化对象
                     * 3 将对象指向这个内存空间
                     *
                     * 这三步可能会指令重排,导致问题
                     * 因此 INSTANCE 必须使用volatile
                     */
                }

            }
        }
        return INSTANCE;
    }

    //单线程下 单例是OK 的

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /**
         * 反射破坏单例模式
         */

        //LazyMan instance = LazyMan.getInstance();
        //获取其无参数的构造器
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan obj1 = declaredConstructor.newInstance();
        LazyMan obj2 = declaredConstructor.newInstance();

        System.out.println(obj1 == obj2);
    }
}
