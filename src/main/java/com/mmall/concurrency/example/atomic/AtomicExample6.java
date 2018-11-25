package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @功能说明：当你值只希望某一段代码只执行一次，可以参考如下例子。
 * @典型用法：
 * @特殊用法：
 * @创建者： panxyu
 * @创建时间：2018/11/22 23:54
 * @修改人：
 * @修改时间：
 * @修改原因：
 * @修改内容：
 **/
@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean isHappended =
            new AtomicBoolean(false);
    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量，定义允许并发的数量，传入线程数threadTotal
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 计数器闭锁,传入请求数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 放入请求
        for (int i = 0; i < clientTotal; i++) {
            // 请求放入线程池，采用jdk8提供的lamda写法
            executorService.execute(() -> {
                try {
                    // 引入信号量semaphore，当它调用acquire()方法时实则在判断当前进程是否允许被执行
                    // 如果达到一定并发数时，接下来的add()方法会被临时阻塞
                    // 当acquire()方法能返回值时，add()方法才会被执行
                    semaphore.acquire();
                    test();
                    // 释放当前的进程
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                // 以上为一个线程执行完毕，完毕后调用一次countDown()，计数器减1操作
                countDownLatch.countDown();
            });
        }
        // await()方法可以保证之前的countDown()计数器值减至0时（所有进程执行完毕）才执行接下来的操作
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        // 打印计数值
        log.info("isHappended:{}", isHappended.get());
    }

    private static void test(){
        if (isHappended.compareAndSet(false,true)){
            log.info("execute");
        }
    }

}
