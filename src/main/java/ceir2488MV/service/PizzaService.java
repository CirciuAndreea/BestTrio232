package ceir2488MV.service;

import ceir2488MV.model.MenuDataModel;
import ceir2488MV.model.Payment;
import ceir2488MV.model.PaymentType;
import ceir2488MV.repository.MenuRepository;
import ceir2488MV.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
    }

    public List<MenuDataModel> getMenuData() {
        return menuRepo.getMenu();
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) {
        Payment payment = new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public void addPayment(PaymentType type, double amount, int tableNumber) {
        Payment payment = new Payment(tableNumber, type, amount);
        payRepo.add(payment);
    }

    public void addInMemory(Payment payment) {
        payRepo.addInMemory(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        int i = 0;
        List<Payment> l = getPayments();
        if (l == null) return total;
        while (i < l.size()) {
            Payment p = l.get(i);
            if (p.getTableNumber() < 0 || p.getTableNumber() > 8) {
                i++;
                continue;
            }
            if (p.getAmount() < 0) {
                i++;
                continue;
            }
            if (p.getType().equals(type))
                total += p.getAmount();
            i++;
        }
        return total;
    }

}
