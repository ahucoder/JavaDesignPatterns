package structural.proxy.dynamicproxy;

import structural.proxy.service.AuthService;
import structural.proxy.service.LogService;

import java.lang.reflect.Method;

public class OrderServiceProxyProcess {

    public void processBefore(Method method, Object[] args, AuthService authService, LogService logService) {
        String methodName = method.getName();
        if ("createOrder".equals(methodName)) {
            String userId = (String) args[0];
            authService.checkPermission(userId, "CREATE_ORDER");
            logService.log("Begin to create order", userId, (String) args[1], args[2].toString());
        } else if ("cancelOrder".equals(methodName)) {
            String orderId = (String) args[0];
            String userId = getUserIdByOrderId(orderId);
            authService.checkPermission(userId, "CANCEL_ORDER");
            logService.log("Start canceling orders", userId, orderId);
        }
    }

    public void processAfter(Method method, Object[] args, AuthService authService, LogService logService) {
        String methodName = method.getName();
        if ("createOrder".equals(methodName)) {
            String userId = (String) args[0];
            logService.log("Order creation completed", userId, (String) args[1], args[2].toString());
        } else if ("cancelOrder".equals(methodName)) {
            String orderId = (String) args[0];
            String userId = getUserIdByOrderId(orderId);
            logService.log("Order cancellation completed", userId, orderId);
        }
    }

    private String getUserIdByOrderId(String orderId) {
        return STR."user_\{orderId.hashCode()}"; // Mock
    }

}
