package vehicle_rental.service;

import vehicle_rental.dao.VehicleDAO;

public class VehicleService {

    private final VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }
}
