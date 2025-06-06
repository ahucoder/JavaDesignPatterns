package proxy.service;

import java.math.BigDecimal;

public interface OrderService {
    void createOrder(String userId, String productId, BigDecimal amount);

    void cancelOrder(String orderId);
}
