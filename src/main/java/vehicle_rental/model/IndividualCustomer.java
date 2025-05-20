package vehicle_rental.model;

import vehicle_rental.model.enums.CustomerType;

public class IndividualCustomer extends Customer {

    private int age;

    public IndividualCustomer() {
    }

    public IndividualCustomer(Long id) {
        super(id);
    }

    public IndividualCustomer(String name, String email, String password, int age) {
        super(name, email, password);
        this.setCustomerType(CustomerType.INDIVIDUAL);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
