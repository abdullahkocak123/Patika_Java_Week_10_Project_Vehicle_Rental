package vehicle_rental.service;


import vehicle_rental.dao.PaymentDAO;
import vehicle_rental.model.Payment;
import vehicle_rental.model.Rent;
import vehicle_rental.model.enums.PaymentMethod;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public Payment save(Rent rent, PaymentMethod paymentMethod){

        Payment payment = new Payment(rent, paymentMethod);
        paymentDAO.save(payment);
        return payment;
    }
}
