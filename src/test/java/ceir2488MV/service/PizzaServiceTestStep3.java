package ceir2488MV.service;

import ceir2488MV.model.Payment;
import ceir2488MV.model.PaymentType;
import ceir2488MV.repository.MenuRepository;
import ceir2488MV.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Payment payment = new Payment(7, PaymentType.Cash, 86.31);
        assertEquals(0, service.getPayments().size());
        service.addInMemory(payment);
        assertEquals(1, service.getPayments().size());
    }

    @Test
    void getTotalAmount() {
        Payment payment = new Payment(7, PaymentType.Cash, 86.31);
        Payment payment1 = new Payment(7, PaymentType.Card, 20);
        Payment payment2 = new Payment(7, PaymentType.Cash, 63);
        Payment payment3 = new Payment(7, PaymentType.Card, 86.14);
        service.addInMemory(payment);
        service.addInMemory(payment1);
        service.addInMemory(payment2);
        service.addInMemory(payment3);
        double cardValue = service.getTotalAmount(PaymentType.Card);
        double cashValue = service.getTotalAmount(PaymentType.Cash);
        assertEquals(106.14, cardValue);
        assertEquals(149.31, cashValue);
    }
}

