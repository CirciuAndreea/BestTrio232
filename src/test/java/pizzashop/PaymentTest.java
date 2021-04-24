package pizzashop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

public class PaymentTest {

    private final Payment payment = new Payment(7, PaymentType.Cash, 80);

    @Test
    public void testGetTableNumber() {
        Assertions.assertEquals(7, payment.getTableNumber());
    }

    @Test
    public void testGetType(){
        Assertions.assertEquals(PaymentType.Cash, payment.getType());
    }

    @Test
    public void testGetAmount(){
        Assertions.assertEquals(80, payment.getAmount());
    }

}
