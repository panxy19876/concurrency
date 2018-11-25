package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @功能说明：AtomicReference
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
public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        // 如果当前是0的话，更新为2
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());
    }

}
