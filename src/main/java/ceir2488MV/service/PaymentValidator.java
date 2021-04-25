package ceir2488MV.service;

import ceir2488MV.model.Payment;

public class PaymentValidator  {

    public boolean validate(Payment payment) {
        if (payment.getAmount() < 1) return false;
        if (payment.getAmount() > 10000) return false;
        return payment.getTableNumber() >= 1 && payment.getTableNumber() <= 9;

    }
}