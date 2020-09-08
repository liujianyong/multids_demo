package com.demo.multids.dao;

import com.demo.multids.model.OrderExp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderExpMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OrderExp record);

    int insertSelective(OrderExp record);

    OrderExp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderExp record);

    int updateByPrimaryKey(OrderExp record);

    List<OrderExp> selectUnPayOrders();
}
