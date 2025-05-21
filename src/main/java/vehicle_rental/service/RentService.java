package vehicle_rental.service;

import vehicle_rental.dao.CartItemDAO;
import vehicle_rental.dao.RentDAO;
import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.*;
import vehicle_rental.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentService {

    private final RentDAO rentDAO;
    private final CartItemService cartItemService;
    private final PaymentService paymentService;
    private final CartService cartService;
    private final RentItemsService rentItemsService;
    private final VehicleService vehicleService;

    public RentService() {
        this.rentDAO = new RentDAO();
        this.cartItemService = new CartItemService();
        this.paymentService = new PaymentService();
        this.cartService = new CartService();
        this.rentItemsService = new RentItemsService();
        this.vehicleService = new VehicleService();
    }

    public Rent save(Customer customer, PaymentMethod paymentMethod) throws VehicleRentalException {

        List<CartItem> cartItems = cartItemService.getByCustomerWithoutPaging(customer);

        if (cartItems.isEmpty()) {
            throw new VehicleRentalException(ExceptionMessagesConstants.CART_ITEMS_EMPTY);
        }

        BigDecimal totalAmount = cartItems.stream()
                .map(cartItem -> cartItem.getRental_unit_price()
                        .multiply(BigDecimal.valueOf(cartItem.getRental_duration()))
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Rent rent = new Rent();
        rent.setCustomer(customer);
        rent.setTotalAmount(totalAmount);
        rent.setRentDate(LocalDateTime.now());

        long rentId = rentDAO.save(rent);

        List<RentItem> rentItems = new ArrayList<>();

        cartItems.forEach(cartItem -> {

            RentItem rentItem = new RentItem();
            rentItem.setRent(new Rent(rentId));
            rentItem.setRent(new Rent(rentId));
            rentItem.setVehicle(cartItem.getVehicle());
            rentItem.setRental_type(cartItem.getRentalType().name());
            rentItem.setRental_duration(cartItem.getRental_duration());
            rentItem.setQuantity(cartItem.getQuantity());
            rentItem.setRental_unit_price(cartItem.getRental_unit_price());
            rentItem.setQuantity(cartItem.getQuantity());
            rentItem.setRental_unit_price(cartItem.getRental_unit_price());
            rentItems.add(rentItem);
        });

        rentItemsService.save(rentItems);

        paymentService.save(rent, paymentMethod);

        cartService.clear(customer);

        cartItems.forEach(cartItem ->{
            Vehicle vehicle = new Vehicle(cartItem.getVehicle().getId());
            vehicleService.updateStock(vehicle, cartItem.getQuantity());

        });


        System.out.println("Araç kiralama ve ödeme işlemi başarıyla tamamlandı.");
        return rent;

    }

    public List<Rent> getAllByCustomer(Customer loginedCustomer) {
        return rentDAO.findAllByCustomerId(loginedCustomer.getId());
    }
}
