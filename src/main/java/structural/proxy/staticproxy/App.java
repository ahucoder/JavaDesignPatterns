package structural.proxy.staticproxy;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        OrderServiceStaticProxy proxy = new OrderServiceStaticProxy();

        proxy.createOrder("user123", "prod456", new BigDecimal("199.99"));
        System.out.println();
        proxy.cancelOrder("order789");
    }

}
