package vehicle_rental.service;

import vehicle_rental.dao.CartDAO;
import vehicle_rental.dao.CartItemDAO;
import vehicle_rental.model.Cart;
import vehicle_rental.model.CartItem;
import vehicle_rental.model.Customer;
import vehicle_rental.model.Vehicle;
import vehicle_rental.model.enums.RentalType;

import java.math.BigDecimal;
import java.util.List;

public class CartService {

    private CartDAO cartDAO;

    private CartItemDAO cartItemDAO;

    public CartService() {
        cartDAO = new CartDAO();
        cartItemDAO = new CartItemDAO();
    }

    public void addToCart(Customer loginedCustomer, Vehicle vehicle, int quantity, RentalType rental_type, int rental_duration, BigDecimal rental_unit_price) {

        Cart cart = cartDAO.findByCustomerId(loginedCustomer.getId());

        if (cart == null) { //we must generate a cart and also set an initail id
            cart = new Cart();
            cart.setCustomer(loginedCustomer);
            cart.setCustomerId(loginedCustomer.getId());
            cart.setTotalAmount(BigDecimal.ZERO);

            Long initialCartId = cartDAO.saveInitial(cart);
            cart.setId(initialCartId);
        }

        CartItem cartItem = new CartItem();
        cartItem.setVehicle(vehicle);
        cartItem.setVehicle_name(vehicle.getName());
        cartItem.setQuantity(quantity);
        cartItem.setRentalType(rental_type);
        cartItem.setRental_duration(rental_duration);
        cartItem.setRental_unit_price(rental_unit_price);
        cartItem.setCart(cart);

        cartItemDAO.save(cartItem);

        System.out.print("Araç sepetinize eklendi\n");
    }

    public void clear(Customer loginedCustomer) {

        Cart cart = cartDAO.findByCustomerId(loginedCustomer.getId());

        int affectedRow = cartItemDAO.clear(cart.getId());

        System.out.print("Sepetinizdeki "+ affectedRow + " araç silindi.\n");

    }
}
