package vehicle_rental.service;

import vehicle_rental.dao.CategoryDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.Category;
import vehicle_rental.model.User;
import vehicle_rental.model.enums.Role;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void save(String name, User user) throws VehicleRentalException {
        if (!Role.ADMIN.equals(user.getRole())){
            throw new VehicleRentalException(ExceptionMessagesConstants.USER_IS_NOT_ADMIN);
        }

        categoryDAO.save(new Category(name, user, user));

        System.out.println("Kategori oluşturuldu!");
    }
}
