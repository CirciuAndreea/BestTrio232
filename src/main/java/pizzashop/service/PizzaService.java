package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        Payment payment= new Payment(table, type, amount);
        if (PaymentValidator.validate(payment)) payRepo.add(payment);
    }

    public void addPayment(PaymentType type, double amount, int tableNumber) {
        Payment payment = new Payment(tableNumber, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        int i = 0;
        List<Payment> l = getPayments();
        if (l == null) return total;
        while (i < l.size()) {
            Payment p = l.get(i);
            if (p.getTableNumber() < 1 || p.getTableNumber() > 9) {
                i = i + 1;
                continue;
            }
            if (p.getAmount() < 1){
                i = i + 1;
                continue;
            }
            if (p.getType().equals(type)){
                i = i + 1;
                total = total + p.getAmount();
            }
        }
        return total;
    }

}
