package pizzashop.repository;

import javafx.collections.ObservableList;
import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PaymentValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class PaymentRepository {
    private static String filename = "data/payments.txt";
    private List<Payment> paymentList;
    private PaymentValidator paymentValidator;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        this.paymentValidator = new PaymentValidator();
        this.readPayments();
    }

    public PaymentRepository(String filename){
        this.paymentList = new ArrayList<>();
        this.filename = filename;
        this.paymentValidator = new PaymentValidator();
        readPayments();
    }

    public PaymentRepository(PaymentValidator paymentValidator, String filename){
        this.filename = filename;
        this.paymentValidator = paymentValidator;
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    public PaymentRepository(PaymentValidator paymentValidator) {
        this.paymentList = new ArrayList<>();
        this.paymentValidator = paymentValidator;
    }

    public void deleteAll() {
        this.paymentList.clear();
    }

    private void readPayments(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment){
        if (paymentValidator.validate(payment)) {
            paymentList.add(payment);
            writeAll();
        }
    }

    public void addInMemory(Payment payment){
        if (paymentValidator.validate(payment)) {
            paymentList.add(payment);
        }
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
