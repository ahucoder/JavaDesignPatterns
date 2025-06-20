package structural.proxy.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import structural.proxy.service.AuthService;
import structural.proxy.service.LogService;
import structural.proxy.service.OrderService;
import structural.proxy.service.impl.AuthServiceImpl;
import structural.proxy.service.impl.LogServiceImpl;
import structural.proxy.service.impl.OrderServiceImpl;

import java.lang.reflect.Method;

public class OrderServiceCglibProxyFactory {
    private final OrderService orderService;
    private final AuthService authService;
    private final LogService logService;
    private final OrderServiceProxyProcess orderServiceProxyProcess;

    public OrderServiceCglibProxyFactory() {
        this.orderService = new OrderServiceImpl();
        this.authService = new AuthServiceImpl();
        this.logService = new LogServiceImpl();
        this.orderServiceProxyProcess = new OrderServiceProxyProcess();
    }

    public OrderService createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(orderService.getClass());
        enhancer.setCallback(new OrderServiceMethodInterceptor());
        return (OrderService) enhancer.create();
    }

    private class OrderServiceMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            // before
            orderServiceProxyProcess.processBefore(method, args, authService, logService);

            // invoke real objects
            Object result = proxy.invokeSuper(obj, args);

            // after
            orderServiceProxyProcess.processAfter(method, args, authService, logService);

            return result;
        }
    }
}
