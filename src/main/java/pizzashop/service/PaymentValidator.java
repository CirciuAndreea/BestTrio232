package pizzashop.service;

import pizzashop.model.Payment;

public class PaymentValidator  {

    public static boolean validate(Payment payment) {
        if (payment.getAmount() < 1) return false;
        if (payment.getAmount() > 10000) return false;
        return payment.getTableNumber() >= 1 && payment.getTableNumber() <= 9;

    }
}