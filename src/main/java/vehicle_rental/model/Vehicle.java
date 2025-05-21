package vehicle_rental.model;

import java.math.BigDecimal;

public class Vehicle extends BaseModel {

    private String name;
    private BigDecimal vehicle_cost;
    private int stock;
    private Category category;
    private BigDecimal hourly_rental;
    private BigDecimal daily_rental;
    private BigDecimal weekly_rental;
    private BigDecimal monthly_rental;

    public Vehicle() {
    }

    public Vehicle(long id) {
        this.setId(id);
    }

    public Vehicle(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Vehicle(String name) {
        this.name = name;
    }

    public Vehicle(Long id, String name, BigDecimal vehicle_cost, int stock, Category category, BigDecimal hourly_rental,
                   BigDecimal daily_rental, BigDecimal weekly_rental, BigDecimal monthly_rental) {
        this.setId(id);
        this.name = name;
        this.vehicle_cost = vehicle_cost;
        this.stock = stock;
        this.category = category;
        this.hourly_rental = hourly_rental;
        this.daily_rental = daily_rental;
        this.weekly_rental = weekly_rental;
        this.monthly_rental = monthly_rental;
    }

    public Vehicle(String name, BigDecimal vehicle_cost, int stock, Category category, BigDecimal hourly_rental,
                   BigDecimal daily_rental, BigDecimal weekly_rental, BigDecimal monthly_rental) {
        this.name = name;
        this.vehicle_cost = vehicle_cost;
        this.stock = stock;
        this.category = category;
        this.hourly_rental = hourly_rental;
        this.daily_rental = daily_rental;
        this.weekly_rental = weekly_rental;
        this.monthly_rental = monthly_rental;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getVehicle_cost() {
        return vehicle_cost;
    }

    public void setVehicle_cost(BigDecimal vehicle_cost) {
        this.vehicle_cost = vehicle_cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getHourly_rental() {
        return hourly_rental;
    }

    public void setHourly_rental(BigDecimal hourly_rental) {
        this.hourly_rental = hourly_rental;
    }

    public BigDecimal getDaily_rental() {
        return daily_rental;
    }

    public void setDaily_rental(BigDecimal daily_rental) {
        this.daily_rental = daily_rental;
    }

    public BigDecimal getWeekly_rental() {
        return weekly_rental;
    }

    public void setWeekly_rental(BigDecimal weekly_rental) {
        this.weekly_rental = weekly_rental;
    }

    public BigDecimal getMonthly_rental() {
        return monthly_rental;
    }

    public void setMonthly_rental(BigDecimal monthly_rental) {
        this.monthly_rental = monthly_rental;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", vehicle_cost=" + vehicle_cost +
                ", stock=" + stock +
                ", category=" + category +
                ", hourly_rental=" + hourly_rental +
                ", daily_rental=" + daily_rental +
                ", weekly_rental=" + weekly_rental +
                ", monthly_rental=" + monthly_rental +
                '}';
    }
}
