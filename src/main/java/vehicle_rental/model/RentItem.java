package vehicle_rental.model;

import java.math.BigDecimal;

public class RentItem {

    private Long id;
    private Rent rent;
    private Vehicle vehicle;
    private String rental_type;
    private Integer quantity;
    private Integer rental_duration;
    private BigDecimal rental_unit_price;

    public RentItem() {
    }

    public RentItem(Long id, Rent rent, Vehicle vehicle, String rental_type, Integer quantity, Integer rental_duration, BigDecimal rental_unit_price) {
        this.id = id;
        this.rent = rent;
        this.vehicle = vehicle;
        this.rental_type = rental_type;
        this.quantity = quantity;
        this.rental_duration = rental_duration;
        this.rental_unit_price = rental_unit_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getRental_type() {
        return rental_type;
    }

    public void setRental_type(String rental_type) {
        this.rental_type = rental_type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
