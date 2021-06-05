# JUC 笔记

## 2021.05.30

### Synchronized 和 Lock 的区别
1、 Synchronized 是内置的java 关键字， Lock 是java 的一个接口  
2、 Synchronized 无法判断获取锁的状态，Lock可以判断是否获取到了锁  
3、 Synchronized 会自动释放锁，Lock必须手动释放锁，如果不释放，会死锁  
4、 Synchronized 线程1（获取锁，阻塞），线程2（会等待，傻傻的等待）; Lock锁就不一定会一直等待，可以使用tryLock()  
5、 Synchronized：可重入，不可中断，非公平; Lock：可重入，可以判断，公平性可以自己设置  
6、 Synchronized 适合锁少量的代码的同步问题; Lock 锁适合锁大量的代码

### 锁是什么，如何判断锁的是谁
1、位于非静态方法上的Synchronized锁的是调用方法的对象  
2、位于静态方法上的Synchronized锁的是调用方法的类的Class,全局唯一

## 2020.06.01
### 集合类不安全

### 常用的辅助类
1 CountDownLatch  
2 CyclicBarrier  
3 Semaphore

## 2020.06.03 线程池

### 四种拒绝策略
1 AbortPolicy// 不处理 抛出异常  
2 CallerRunsPolicy// 哪来的回哪去，被创建线程的父线程执行  
3 DiscardPolicy // 丢掉任务， 不抛出异常  
3 DiscardOldestPolicy // 尝试与最早的线程竞争，也不会抛出异常

#### 最大线程应该如何设置
1 CPU密集型：CPU几核心就是几，可以保持CPU 的效率最高  
2 IO 密集型：判断程序中十分耗IO 的线程有几个，设置成其两倍

## 2020.06.04 Stream
### Stream 流式计算
大数据：存储+计算 集合、MySql 本质上都是存储数据  
而计算都应该交给Stream流 来操作

### ForkJoin
#### 什么是ForkJoin
在JDK1.7之后出来的，并行执行任务，提高效率，大数据量!  
大数据：map reduce 大任务拆分成很多小任务

forkJoin 特点： 工作窃取 ： 先执行完的线程可以拿到还未执行完的其他线程 的任务
，帮助执行。 双端队列，可以从队尾取

### volatile
#### 保证可见性:其他线程对共享数据的修改对所有线程可见
#### 不保证原子性：原子性： 不可分割性，多条指令要么一起执行 要么都不执  提问：如果不加lock 和synchronized 如何保证原子性：
a++ 不是原子操作 使用AtomicInteger 等原子包装类
#### 禁止指令重排：
指令重排： 程序执行时的顺序与代码的排序可能不同  
源代码-->编译器优化的重排-->指令并行也可能会重排-->内存系统重排-->执行  
**处理器在进行指令重排时，会考虑数据之间的相互依赖，不会任意排序**
