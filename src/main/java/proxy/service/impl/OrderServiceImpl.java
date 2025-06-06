package proxy.service.impl;

import proxy.service.OrderService;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {
    @Override
    public void createOrder(String userId, String productId, BigDecimal amount) {
        System.out.printf("create order: user[%s] product[%s] amount[%s]\n", userId, productId, amount);
        // do sth...
    }

    @Override
    public void cancelOrder(String orderId) {
        System.out.printf("cancel order: [%s]\n", orderId);
        // do sth...
    }
}
