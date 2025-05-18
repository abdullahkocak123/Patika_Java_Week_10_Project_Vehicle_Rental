package vehicle_rental.model;

import vehicle_rental.model.enums.CustomerType;

public class CorporateCustomer extends Customer{

    public CorporateCustomer() {
    }

    public CorporateCustomer(String name, String email, String password) {
        super(name, email, password);
        this.setCustomerType(CustomerType.CORPORATE);
    }

}
