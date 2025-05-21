package vehicle_rental.model;

import vehicle_rental.model.enums.RentalType;

import java.math.BigDecimal;

public class CartItem {

    private Long id;
    private Vehicle vehicle;
    private String vehicle_name;
    private Cart cart;
    private Integer quantity;
    private RentalType rentalType;
    private Integer rental_duration;
    private BigDecimal rental_unit_price;

    public CartItem() {
    }

    public CartItem(Long id) {
        this.id = id;
    }

    public CartItem(Long id, Vehicle vehicle, Cart cart, Integer quantity, RentalType rentalType, Integer rental_duration, BigDecimal rental_unit_price) {
        this.id = id;
        this.vehicle = vehicle;
        this.cart = cart;
        this.quantity = quantity;
        this.rentalType = rentalType;
        this.rental_duration = rental_duration;
        this.rental_unit_price = rental_unit_price;
    }

    public CartItem(Vehicle vehicle, Cart cart, Integer quantity, RentalType rentalType, Integer rental_duration, BigDecimal rental_unit_price) {
        this.vehicle = vehicle;
        this.cart = cart;
        this.quantity = quantity;
        this.rentalType = rentalType;
        this.rental_duration = rental_duration;
        this.rental_unit_price = rental_unit_price;
    }

    public CartItem(String vehicle_name, Integer quantity, RentalType rentalType, Integer rental_duration, BigDecimal rental_unit_price) {
        this.vehicle_name = vehicle_name;
        this.quantity = quantity;
        this.rentalType = rentalType;
        this.rental_duration = rental_duration;
        this.rental_unit_price = rental_unit_price;
    }

    public CartItem(Vehicle vehicle, Integer quantity, RentalType rentalType, Integer rental_duration, BigDecimal rental_unit_price) {
        this.vehicle = vehicle;
        this.vehicle_name = vehicle.getName();
        this.quantity = quantity;
        this.rentalType = rentalType;
        this.rental_duration = rental_duration;
        this.rental_unit_price = rental_unit_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public Integer getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(Integer rental_duration) {
        this.rental_duration = rental_duration;
    }

    public BigDecimal getRental_unit_price() {
        return rental_unit_price;
    }

    public void setRental_unit_price(BigDecimal rental_unit_price) {
        this.rental_unit_price = rental_unit_price;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }
}
