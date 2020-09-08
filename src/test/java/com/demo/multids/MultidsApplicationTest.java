package com.demo.multids;

import com.demo.multids.service.ProcessOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultidsApplicationTest {
    @Autowired
    private ProcessOrderService processOrderService;

    @Test
    public void insertOrders() {
        processOrderService.insertOrders(5);
    }

    @Test
    public void queryOrders() {
        processOrderService.findOrders();
        //processOrder.findOrdersMaster();
    }


}
