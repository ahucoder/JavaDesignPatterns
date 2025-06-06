package proxy.staticproxy;

import proxy.service.AuthService;
import proxy.service.LogService;
import proxy.service.OrderService;
import proxy.service.impl.AuthServiceImpl;
import proxy.service.impl.LogServiceImpl;
import proxy.service.impl.OrderServiceImpl;

import java.math.BigDecimal;

public class OrderServiceStaticProxy {
    private final OrderService orderService;
    private final AuthService authService;
    private final LogService logService;

    public OrderServiceStaticProxy() {
        this.orderService = new OrderServiceImpl();
        this.authService = new AuthServiceImpl();
        this.logService = new LogServiceImpl();
    }

    public void createOrder(String userId, String productId, BigDecimal amount) {
        // before
        authService.checkPermission(userId, "CREATE_ORDER");
        logService.log("Begin to create order", userId, productId, amount.toString());

        // invoke real objects
        orderService.createOrder(userId, productId, amount);

        // after
        logService.log("Order creation completed", userId, productId, amount.toString());
    }

    public void cancelOrder(String orderId) {
        // before
        String userId = getUserIdByOrderId(orderId);
        authService.checkPermission(userId, "CANCEL_ORDER");
        logService.log("Start canceling orders", userId, orderId);

        // invoke real objects
        orderService.cancelOrder(orderId);

        // after
        logService.log("Order cancellation completed", userId, orderId);
    }

    private String getUserIdByOrderId(String orderId) {
        return STR."user_\{orderId.hashCode()}"; // Mock
    }

}
