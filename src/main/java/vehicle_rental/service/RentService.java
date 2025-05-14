package vehicle_rental.service;

import vehicle_rental.dao.RentDAO;
import vehicle_rental.model.Customer;
import vehicle_rental.model.Rent;
import vehicle_rental.model.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public class RentService {

    private final RentDAO rentDAO;

    public RentService(RentDAO rentDAO) {
        this.rentDAO = new RentDAO();
    }

    public Rent save(Customer customer, List<Vehicle> vehicles){

        BigDecimal totalRent = vehicles.stream()
                .map(Vehicle::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Rent rent = new Rent();
        rent.setVehicles(vehicles);
        rent.setCustomer(customer);
        rent.setTotalRent(totalRent);

        rentDAO.save(rent);

        return rent;

    }
}
