package vehicle_rental.service;

import vehicle_rental.dao.CategoryDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.Category;
import vehicle_rental.model.User;
import vehicle_rental.model.enums.Role;

import java.util.List;

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

    public List<Category> getAll() {
         return categoryDAO.findAll(5);
    }

    public void deleteById(long id) {
        categoryDAO.delete(id);
        System.out.println("Kategori silindi!");

    }

    public Category getById(Long categoryId) throws VehicleRentalException {
        Category foundCategory = categoryDAO.findById(categoryId);

        if (foundCategory==null){
            throw new VehicleRentalException(ExceptionMessagesConstants.CATEGORY_NOT_FOUND);
        }
        //System.out.println("Kategori bulundu! : " + foundCategory); //araç kaydında gereksiz çıktı veriyor
        return foundCategory;
    }
}
