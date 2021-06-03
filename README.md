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

### 四种拒绝策略
1 AbortPolicy// 不处理 抛出异常  
2 CallerRunsPolicy// 哪来的回哪去，被创建线程的父线程执行  
3 DiscardPolicy // 丢掉任务， 不抛出异常  
3 DiscardOldestPolicy // 尝试与最早的线程竞争，也不会抛出异常



