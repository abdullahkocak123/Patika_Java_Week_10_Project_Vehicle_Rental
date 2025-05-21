package vehicle_rental.service;

import vehicle_rental.dao.CartItemDAO;
import vehicle_rental.model.Cart;
import vehicle_rental.model.CartItem;
import vehicle_rental.model.Customer;

import java.util.List;

public class CartItemService {

    private CartItemDAO cartItemDAO;

    public CartItemService() {
        this.cartItemDAO = new CartItemDAO();
    }

    public List<CartItem> getByCustomer(Customer loginedCustomer, int page) {
        return cartItemDAO.findByCustomerId(loginedCustomer.getId(), page);
    }

    public List<CartItem> getByCustomerWithoutPaging(Customer loginedCustomer) {
        return cartItemDAO.findByCustomerIdWithoutPaging(loginedCustomer.getId());
    }
}
