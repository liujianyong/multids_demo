package com.demo.multids.dbutils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义的逻辑数据源，包含了实际的物理数据源，充当了真正的路由中介
 */
public class LogicDataSource extends AbstractRoutingDataSource {
    public final static String MASTER = "master";
    public final static String SLAVE_1 = "slave_1";
    /* 更多的从库继续添加即可 */
    // public final static String SLAVE_2 = "slave_2";

    /* 保存系统中存在的数据源的key，然后通过该Key定位到实际的数据源实体 */
    private static String db_src;

    public static void slave() {
        db_src = SLAVE_1;
        System.out.println("切换到从库....");
    }

    public static void master() {
        db_src = MASTER;
        System.out.println("切换到主库....");
    }

    /**
     * 个
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return db_src;
    }

}
