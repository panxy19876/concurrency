package com.mmall.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对于【线程安全】的类，加入一个@ThreadSafe注解的标示
 * @Target(ElementType.TYPE) TYPE说明作用于类上
 * @Retention(RetentionPolicy.SOURCE) 指定注解作用的范围
 *      SOURCE:在编译的时候就会被忽略掉。
 *      CLASS:编译器将在类文件中记录注释,但是不需要在运行时被VM保留。这是默认值
 *      RUNTIME:：在实际编程中使用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";
}
