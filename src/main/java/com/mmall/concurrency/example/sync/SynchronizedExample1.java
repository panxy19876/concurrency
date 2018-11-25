package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @功能说明： synchronized关键字修饰同步代码块和同步方法测试类
 *             使用线程池，为了观察一个对象的两个进程同时调用同一方法时的输出结果
 * @典型用法：
 * @特殊用法： 若子类继承父类调用父类的synchronized方法，是带不上synchronized关键字的
 *            原因：synchronized 不属于方法声明的一部分
 *            如果子类也想使用同步需要在方法上声明
 * @创建者： panxyu
 * @创建时间：2018/11/25 0:26
 * @修改人：
 * @修改时间：
 * @修改原因：
 * @修改内容：
 **/
@Slf4j
public class SynchronizedExample1 {
    // 修饰一个代码块
    public void test1(int j) {
        synchronized(this) {
            for (int i = 0; i < 10; i++){
                log.info("test1 {} - {}" , j, i);
            }
        }
    }
    // 修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++){
            log.info("test2 {} - {}" , j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        // 声明线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 开启一个进程
        // 使用线程池模拟两个对象的两个进程同时调用一段sync代码

        // 例子1：同一个对象，调用使用synchronized关键字修饰的方法时，同一时刻，只有一个线程执行
        // 结果：对象1先输出0-9，然后对象2再输出0-9，两个对象各自顺序输出
//        executorService.execute(() ->{
//            example1.test2(1);
//        });
//        executorService.execute(() ->{
//            example1.test2(2);
//        });
        // 例子2：对象1和对象2交替输出
//        executorService.execute(() ->{
//            example1.test2(1);
//        });
//        executorService.execute(() ->{
//            example2.test2(2);
//        });
        // 例子：同一个对象调用2次，结果对比
        // 1. 不使用synchronized，交替输出
        // 2. 使用synchronized，先0-9->再0-9,依次输出
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example1.test2(2);
        });
    }
}
