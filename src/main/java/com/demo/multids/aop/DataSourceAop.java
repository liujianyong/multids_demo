package com.demo.multids.aop;

import com.demo.multids.dbutils.LogicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    /**
     * 从库的切点，没有标注Master注解，并且方法名为select和get开头的方法，走从库
     */
    @Pointcut("!@annotation(com.demo.multids.annotation.Master) " +
            "&& (execution(* com.demo.multids.service..*.select*(..))" +
            "|| execution(* com.demo.multids.service..*.get*(..)))" +
            "|| execution(* com.demo.multids.service..*.find*(..))" +
            "|| execution(* com.demo.multids.service..*.query*(..)))")
    public void slavePointcut() {

    }

    /**
     * 主库的切点，或者标注了Master注解或者方法名为insert、update等开头的方法，走主库
     */
    @Pointcut("@annotation(com.demo.multids.annotation.Master) " +
            "|| execution(* com.demo.multids.service..*.insert*(..))" +
            "|| execution(* com.demo.multids.service..*.add*(..))" +
            "|| execution(* com.demo.multids.service..*.update*(..))" +
            "|| execution(* com.demo.multids.service..*.edit*(..))" +
            "|| execution(* com.demo.multids.service..*.delete*(..))" +
            "|| execution(* com.demo.multids.service..*.remove*(..))")
    public void masterPointcut() {

    }

    /**
     * 前置通知，在方法执行之前执行
     */
    @Before("slavePointcut()")
    public void slave() {
        LogicDataSource.slave();
    }

    /**
     * 前置通知，在方法执行之前执行
     */
    @Before("masterPointcut()")
    public void master() {
        LogicDataSource.master();
    }

}
