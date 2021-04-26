package ceir2488MV.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ceir2488MV.model.Payment;
import ceir2488MV.model.PaymentType;
import ceir2488MV.repository.MenuRepository;
import ceir2488MV.repository.PaymentRepository;

public class PizzaServiceTestStep3 {

    private PaymentRepository paymentRepository;
    private PizzaService service;
    private MenuRepository menuRepository;
    private PaymentValidator paymentValidator;

    @BeforeEach
    void setUp() {
        paymentValidator = new PaymentValidator();
        menuRepository = new MenuRepository("test_data/menu.txt");
        paymentRepository = new PaymentRepository(paymentValidator);
        service = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    void addPaymentObject() {
        Payment payment = new Payment(7, PaymentType.Cash,86.31);
        Assertions.assertEquals(0,service.getPayments().size());
        service.addInMemory(payment);
        Assertions.assertEquals(1,service.getPayments().size());
    }

    @Test
    void getTotalAmount() {
        Payment payment = new Payment(7, PaymentType.Cash,86.31);
        Payment payment1 = new Payment(7, PaymentType.Cash,20);
        Payment payment2 = new Payment(7, PaymentType.Cash,63);
        Payment payment3 = new Payment(7, PaymentType.Cash,86.14);
        service.addInMemory(payment);
        service.addInMemory(payment1);
        service.addInMemory(payment2);
        service.addInMemory(payment3);
        double cashValue = service.getTotalAmount(PaymentType.Cash);
        Assertions.assertEquals(255.45,cashValue);
    }
}
