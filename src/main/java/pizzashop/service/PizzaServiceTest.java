package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PizzaServiceTest {

    private static PizzaService pizzaService;
    private static MenuRepository menuRepository;
    private static PaymentRepository paymentRepository;

    @BeforeAll
    static void setup() {
        menuRepository = new MenuRepository("test_data/menu.txt");
        paymentRepository = new PaymentRepository("test_data/payments.txt");
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @BeforeEach
    void clear() {
        paymentRepository.deleteAll();
    }

    @Test
    void TC1_ECP() {
        pizzaService.addPayment(5, PaymentType.Cash, 10);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 1);
    }

    @Test
    void TC2_ECP() {
        pizzaService.addPayment(5, PaymentType.Cash, -12.112);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 0);
    }

    @Test
    void TC3_ECP() {
        pizzaService.addPayment(10, PaymentType.Cash, 35.789);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 0);
    }

    @Test
    void TC1_BVA() {
        pizzaService.addPayment(1, PaymentType.Cash, 1.0);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 1);
    }

    @Test
    void TC2_BVA() {
        pizzaService.addPayment(8, PaymentType.Cash, 10000.00);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 1);
    }

    @Test
    void TC3_BVA() {
        pizzaService.addPayment(9, PaymentType.Cash, 9999);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 1);
    }

    @Test
    void TC4_BVA() {
        pizzaService.addPayment(2, PaymentType.Cash
                , 0.99);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 0);

    }

    @Test
    void TC5_BVA() {
        pizzaService.addPayment(0, PaymentType.Cash, 1.01);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 0);
    }

    @RepeatedTest(3)
    @Tag("Repeated test")
    @Test
    void TC6_BVA() {
        pizzaService.addPayment(10, PaymentType.Cash, 1);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 0);
    }


    @Test
    void TC7_BVA() {
        pizzaService.addPayment(1, PaymentType.Cash, 10001);
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentList.size(), 0);
    }
    @Test
    void TC11_WBT(){
        double totalAmount = pizzaService.getTotalAmount(PaymentType.Cash);
        assertEquals(0, totalAmount);
    }

    @Test
    void TC12_WBT(){
        pizzaService.addPayment(PaymentType.Cash, 1.00, -1);
        double totalAmount = pizzaService.getTotalAmount(PaymentType.Cash);
        assertEquals(0, totalAmount);
    }

    @Test
    void TC13_WBT(){
        pizzaService.addPayment(PaymentType.Cash, 25.00,6);
        double totalAmount = pizzaService.getTotalAmount(PaymentType.Cash);
        assertEquals(25.00, totalAmount);
    }

    @Test
    void TC14_WBT(){
        pizzaService.addPayment(PaymentType.Cash, -25.00,6);
        double totalAmount = pizzaService.getTotalAmount(PaymentType.Cash);
        assertEquals(0, totalAmount);
    }
}