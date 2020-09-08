package com.demo.multids.annotation;

/**
 * 查询数据方法都是从从库读取数据，如经过方法上用了此注解的，表示要求强制走主库
 */
public @interface Master {
}
