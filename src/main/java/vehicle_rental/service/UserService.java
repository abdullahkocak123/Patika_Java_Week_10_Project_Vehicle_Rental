package vehicle_rental.service;

import vehicle_rental.dao.UserDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.User;
import vehicle_rental.model.enums.Role;
import vehicle_rental.util.PasswordUtil;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public void save(String userName, String password, Role role) throws VehicleRentalException {

        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            throw new VehicleRentalException(ExceptionMessagesConstants.USER_EMAIL_ALREADY_EXIST);
        }

        userDAO.save(new User(userName, PasswordUtil.hash(password), role));
        System.out.println("Kayıt Başarılı!");

    }

    public User login(String userName, String password) throws VehicleRentalException {
        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            String hashedPassword = PasswordUtil.hash(password);
            if (!hashedPassword.equals(foundUser.getPassword())) {
                throw new VehicleRentalException(ExceptionMessagesConstants.USER_PASSWORD_DOES_NOT_MATCH);
            }
        } else {
            throw new VehicleRentalException(ExceptionMessagesConstants.USER_PASSWORD_DOES_NOT_MATCH);
        }
        System.out.println("Giriş Başarılı!");
        System.out.println("Hoş Geldin " + foundUser.getUsername());

        return foundUser;
    }
}
