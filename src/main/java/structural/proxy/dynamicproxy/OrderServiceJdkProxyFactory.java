package structural.proxy.dynamicproxy;

import structural.proxy.service.AuthService;
import structural.proxy.service.LogService;
import structural.proxy.service.OrderService;
import structural.proxy.service.impl.AuthServiceImpl;
import structural.proxy.service.impl.LogServiceImpl;
import structural.proxy.service.impl.OrderServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OrderServiceJdkProxyFactory {
    private final OrderService orderService;
    private final AuthService authService;
    private final LogService logService;
    private final OrderServiceProxyProcess orderServiceProxyProcess;

    public OrderServiceJdkProxyFactory() {
        this.orderService = new OrderServiceImpl();
        this.authService = new AuthServiceImpl();
        this.logService = new LogServiceImpl();
        this.orderServiceProxyProcess = new OrderServiceProxyProcess();
    }

    public OrderService createProxy() {
        return (OrderService) Proxy.newProxyInstance(
                orderService.getClass().getClassLoader(),
                orderService.getClass().getInterfaces(),
                new OrderServiceInvocationHandler()
        );
    }

    private class OrderServiceInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // before
            orderServiceProxyProcess.processBefore(method, args, authService, logService);

            // invoke real objects
            Object result = method.invoke(orderService, args);

            // after
            orderServiceProxyProcess.processAfter(method, args, authService, logService);

            return result;
        }
    }
}
