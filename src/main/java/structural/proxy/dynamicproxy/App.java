package structural.proxy.dynamicproxy;

import structural.proxy.service.OrderService;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        // jdk dynamic proxy
        OrderService proxy = new OrderServiceJdkProxyFactory().createProxy();
        proxy.createOrder("user123", "prod456", new BigDecimal("199.99"));
        System.out.println();
        proxy.cancelOrder("order789");

        System.out.println("========================================================");

        // cglib dynamic proxy
        proxy = new OrderServiceCglibProxyFactory().createProxy();
        proxy.createOrder("user123", "prod456", new BigDecimal("199.99"));
        System.out.println();
        proxy.cancelOrder("order789");
    }

}
