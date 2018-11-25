package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @功能说明：AtomicIntegerFieldUpdater
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
public class AtomicExample5 {
    // AtomicIntegerFieldUpdater是拿原子性来更新某一个类的实例对象（本例中的example5）对应的字段（count）
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    // 这个字段必须通过volatile及非static来修饰
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        // 更新操作，如果当前字段是100的话，则更新为120
        if (updater.compareAndSet(example5,100,120)){
            log.info("update success,{}",example5.getCount());
        }
        if (updater.compareAndSet(example5,100,120)){
            log.info("update success,{}",example5.getCount());
        } else {
            log.info("update failed,{}",example5.getCount());
        }
    }

}
