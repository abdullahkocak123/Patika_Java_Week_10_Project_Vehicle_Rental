package vehicle_rental.service;

import vehicle_rental.dao.CustomerDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.Customer;
import vehicle_rental.util.PasswordUtil;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService(){

        customerDAO = new CustomerDAO();

    }

    public void save(String name, String email, String password){

        boolean isExist = customerDAO.existByEmail(email);

        if (isExist){
            throw new VehicleRentalException(ExceptionMessagesConstants.CUSTOMER_EMAIL_ALREADY_EXIST);
        }

        Customer customer = new Customer(name, email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Kayıt başarılı.");
    }

    public void login(String email, String password) {

        boolean isExist = customerDAO.existByEmail(email);

        if (!isExist){
            throw new VehicleRentalException(ExceptionMessagesConstants.CUSTOMER_EMAIL_DOES_NOT_EXIST);
        }

        String hashedPassword = PasswordUtil.hash(password);

       Customer foundCustomer = customerDAO.findByEmail(email);

       if (foundCustomer!=null){
           boolean passwordsEqual = foundCustomer.getPassword().equals(hashedPassword);
           if (!passwordsEqual){
               throw new VehicleRentalException(ExceptionMessagesConstants.CUSTOMER_PASSWORD_DOES_NOT_MATCH);
           } else {
               System.out.println("Kullanıcı sisteme giriş yaptı!");
           }
       }
    }
}
