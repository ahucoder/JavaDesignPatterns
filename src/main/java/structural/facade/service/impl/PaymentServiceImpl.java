package structural.facade.service.impl;

import structural.facade.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean processPayment(String user, double amount) {
        System.out.printf("Processing payment for %s, amount: %.2f%n", user, amount);
        return true;
    }
}
