package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @功能说明： synchronized关键字修饰静态方法和类的测试类
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
public class SynchronizedExample2 {
    // 修饰一个类
    public static void test1(int j) {
        synchronized(SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++){
                log.info("test1 {} - {}" , j, i);
            }
        }
    }
    // 修饰一个静态方法
    public static synchronized  void test2(int j) {
        for (int i = 0; i < 10; i++){
            log.info("test2 {} - {}" , j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        // 声明线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 开启一个进程
        // 使用线程池模拟两个对象的两个进程同时调用一段sync代码

        // 例子1：使用不同的类调用用synchronized关键字修饰的同一个类时，同一个时间，只有一个线程执行
        // 结果：对象1先输出0-9，然后对象2再输出0-9，两个对象各自顺序输出
        executorService.execute(() ->{
            example1.test2(1);
        });
        executorService.execute(() ->{
            example2.test2(2);
        });
        // 例子2：使用不同的类，它们在调用用synchronized关键字修饰的同一静态方法时，同一个时间，只有一个线程执行
        // 结果：对象1先输出0-9，然后对象2再输出0-9，两个对象各自顺序输出
//        executorService.execute(() ->{
//            example1.test2(1);
//        });
//        executorService.execute(() ->{
//            example2.test2(2);
//        });
    }
}
