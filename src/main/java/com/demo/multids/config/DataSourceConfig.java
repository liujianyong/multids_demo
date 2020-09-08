package com.demo.multids.config;

import com.demo.multids.dbutils.LogicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源的相关配置，包括实际数据源和自定义数据源
 *
 */
@Configuration
public class DataSourceConfig {

    /**
     * 物理上，主要的数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 物理上，从库1的数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return  DataSourceBuilder.create().build();
    }

    /**
     * 自定义数据源，内部持有了主库和从库的数据源
     * 通过某种机制让应用程序在进行数据读写时，按业务情况走主库或者从库
     */
    @Bean
    public DataSource logicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                      @Qualifier("slave1DataSource") DataSource slave1DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(LogicDataSource.MASTER, masterDataSource);
        targetDataSources.put(LogicDataSource.SLAVE_1, slave1DataSource);
        LogicDataSource logicDataSource = new LogicDataSource();
        logicDataSource.setTargetDataSources(targetDataSources);
        /*缺省数据源，防止aop没有切到，走默认数据源*/
        logicDataSource.setDefaultTargetDataSource(masterDataSource);
        return logicDataSource;
    }



}
