package vehicle_rental.service;

import vehicle_rental.dao.CustomerDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.CorporateCustomer;
import vehicle_rental.model.Customer;
import vehicle_rental.model.IndividualCustomer;
import vehicle_rental.util.PasswordUtil;

public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(){

        customerDAO = new CustomerDAO();

    }

    public void saveIndividual(String name, String email, String password, int age) throws VehicleRentalException {

        boolean isExist = customerDAO.existByEmail(email);

        if (isExist){
            throw new VehicleRentalException(ExceptionMessagesConstants.CUSTOMER_EMAIL_ALREADY_EXIST);
        }

        if (age<=0){
            throw new VehicleRentalException(ExceptionMessagesConstants.INDIVIDUAL_CUSTOMER_HAS_NEGATIVE_AGE);
        }

        IndividualCustomer individualCustomer = new IndividualCustomer(name, email, PasswordUtil.hash(password), age);
        customerDAO.saveIndividual(individualCustomer);
        System.out.println("Bireysel müşteri kaydı başarılı.");
    }

    public void saveCorporate(String name, String email, String password) throws VehicleRentalException {

        boolean isExist = customerDAO.existByEmail(email);

        if (isExist){
            throw new VehicleRentalException(ExceptionMessagesConstants.CUSTOMER_EMAIL_ALREADY_EXIST);
        }

        CorporateCustomer corporateCustomer = new CorporateCustomer(name, email, PasswordUtil.hash(password));
        customerDAO.saveCorporate(corporateCustomer);
        System.out.println("Kurumsal müşteri kaydı başarılı.");
    }

    public Customer login(String email, String password) throws VehicleRentalException {

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
       return foundCustomer;
    }
}
