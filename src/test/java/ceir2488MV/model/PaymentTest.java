package ceir2488MV.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    private final Payment payment = new Payment(7, PaymentType.Cash, 80);

    @Test
    public void getTableNumber() {
        Assertions.assertEquals(7, payment.getTableNumber());
    }

    @Test
    public void getType(){
        Assertions.assertEquals(PaymentType.Cash, payment.getType());
    }

    @Test
    public void getAmount(){
        Assertions.assertEquals(80, payment.getAmount());
    }

}
