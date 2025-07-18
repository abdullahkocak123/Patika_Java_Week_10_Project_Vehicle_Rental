package vehicle_rental.service;

import vehicle_rental.dao.VehicleDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.User;
import vehicle_rental.model.Vehicle;
import vehicle_rental.model.enums.Role;

import java.util.List;

public class VehicleService {

    private final VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void save(Vehicle vehicle, User user) throws VehicleRentalException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new VehicleRentalException(ExceptionMessagesConstants.USER_IS_NOT_ADMIN);
        }

        vehicle.setCreatedUser(user);
        vehicle.setUpdatedUser(user);

        vehicleDAO.save(vehicle);

        System.out.println("Araç kaydedildi!");
    }

    public List<Vehicle> getAll(int page) {

        return vehicleDAO.findAll(page);
    }

    public void deleteById(long id) {
        vehicleDAO.delete(id);
        System.out.println("Araç silindi!: " + id);
    }

    public int getTotalPage() {
        return vehicleDAO.findTotalPage();
    }

    public List<Vehicle> search(String searchVehicleName) {
        return vehicleDAO.searchByName(searchVehicleName);
    }

    public List<Vehicle> getByCategoryName(String categoryName, int page) {
        return vehicleDAO.findAllByCategoryName(categoryName, page);
    }

    public int getTotalPageByFilter(String categoryName) {
        return vehicleDAO.findTotalPageByFilter(categoryName);
    }

    public Vehicle getByName(String vehicleName) {
        return vehicleDAO.findByName(vehicleName);
    }

    public int getTotalPageAtCart(Long customerId) {
        return vehicleDAO.findTotalPageAtCart(customerId);
    }

    public void updateStock(Vehicle vehicle, int quantity) {
        Vehicle foundVehicle = vehicleDAO.findById(vehicle.getId());
        vehicleDAO.updateStock(vehicle.getId(), foundVehicle.getStock() - quantity);
        System.out.println(foundVehicle. getName() +" aracın stok bilgileri güncellendi.");
    }
}
