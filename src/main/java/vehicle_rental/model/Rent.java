package vehicle_rental.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Rent extends BaseModel{

    private Customer customer;
    private BigDecimal totalRent; // gerekecek mi?
    private LocalDateTime rentDate;

    public Rent(Customer customer) {
        this.customer = customer;
        this.rentDate = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(BigDecimal totalRent) {
        this.totalRent = totalRent;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }
}
