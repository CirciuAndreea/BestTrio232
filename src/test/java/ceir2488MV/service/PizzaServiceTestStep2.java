package ceir2488MV.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ceir2488MV.model.Payment;
import ceir2488MV.repository.MenuRepository;
import ceir2488MV.repository.PaymentRepository;

import static org.mockito.ArgumentMatchers.any;

public class PizzaServiceTestStep2 {

    private PaymentRepository paymentRepository;
    private PizzaService service;
    private MenuRepository menuRepository;

    @Mock
    private PaymentValidator paymentValidator;

    @Mock
    private Payment payment;
    @Mock
    private Payment payment2;
    @Mock
    private Payment payment3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        menuRepository = new MenuRepository("test_data/menu.txt");
        paymentRepository = new PaymentRepository(paymentValidator);
        service = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    void getPayments() {
        Mockito.when(paymentValidator.validate(any())).thenReturn(true);
        service.addInMemory(payment);
        service.addInMemory(payment2);
        service.addInMemory(payment3);
        Assertions.assertEquals(3, service.getPayments().size());
        Mockito.verify(paymentValidator, Mockito.times(3)).validate(any());
    }

    @Test
    void addPayment() {
        Mockito.when(paymentValidator.validate(payment)).thenReturn(true);
        service.addInMemory(payment);
        service.addInMemory(payment);
        Assertions.assertEquals(2, service.getPayments().size());
    }
}
