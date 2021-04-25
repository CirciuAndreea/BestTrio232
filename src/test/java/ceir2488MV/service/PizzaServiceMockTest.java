package ceir2488MV.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ceir2488MV.model.Payment;
import ceir2488MV.model.PaymentType;
import ceir2488MV.repository.PaymentRepository;

import java.util.Arrays;

public class PizzaServiceMockTest {
    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PizzaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addPayment() {
        Payment payment = new Payment(5, PaymentType.Card, 80);
        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(payment));
        Mockito.doNothing().when(repository).add(payment);
        service.addInMemory(payment);
        Assertions.assertEquals(1,service.getPayments().size());
    }


    @Test
    void getTotalAmount() {
        Payment payment1 = new Payment(5, PaymentType.Cash, 30);
        Payment payment2 = new Payment(6, PaymentType.Cash, 76);
        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(payment1, payment2));
        double value = service.getTotalAmount(PaymentType.Cash);
        Mockito.verify(repository,Mockito.times(1)).getAll();
        Assertions.assertEquals(106.0,value);
    }
}
