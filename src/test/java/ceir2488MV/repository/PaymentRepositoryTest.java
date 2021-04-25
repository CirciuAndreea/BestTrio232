package ceir2488MV.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ceir2488MV.model.Payment;
import ceir2488MV.service.PaymentValidator;

public class PaymentRepositoryTest {

    @Mock
    private PaymentValidator paymentValidator;

    @InjectMocks
    private final PaymentRepository paymentRepository = new PaymentRepository(paymentValidator);

    @Mock
    private Payment payment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addInMemory() {
        Mockito.when(paymentValidator.validate(payment)).thenReturn(Boolean.TRUE);
        paymentRepository.addInMemory(payment);
        Assertions.assertEquals(1, paymentRepository.getAll().size());
        Mockito.verify(paymentValidator, Mockito.times(1)).validate(payment);
    }

    @Test
    public void getAll() {
        Mockito.when(paymentValidator.validate(payment)).thenReturn(Boolean.TRUE);
        paymentRepository.addInMemory(payment);
        paymentRepository.addInMemory(payment);
        paymentRepository.addInMemory(payment);
        Assertions.assertEquals(3, paymentRepository.getAll().size());
        Mockito.verify(paymentValidator, Mockito.times(3)).validate(payment);
    }
}
