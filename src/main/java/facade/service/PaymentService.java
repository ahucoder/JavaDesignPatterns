package facade.service;

public interface PaymentService {
    boolean processPayment(String user, double amount);
}
