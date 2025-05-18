package vehicle_rental.dao;

import vehicle_rental.constants.VehicleRentalConstants;
import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Category;
import vehicle_rental.model.Vehicle;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements BaseDAO<Vehicle> {

    public List<Vehicle> searchByName(String name) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_SEARCH_BY_NAME);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getLong("id"));
                v.setName(rs.getString("name"));
                v.setVehicle_cost(rs.getBigDecimal("vehicle_cost"));
                v.setStock(rs.getInt("stock"));
                //v.setCreatedDate(LocalDateTime.parse(rs.getString("createddate")));
                //v.setUpdatedDate(LocalDateTime.parse(rs.getString("updateddate")));
                v.setCategory(new Category(rs.getLong("category_id"), rs.getString("category_name")));
                v.setHourly_rental(rs.getBigDecimal("hourly_rental"));
                v.setDaily_rental(rs.getBigDecimal("daily_rental"));
                v.setWeekly_rental(rs.getBigDecimal("weekly_rental"));
                v.setMonthly_rental(rs.getBigDecimal("monthly_rental"));
                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void save(Vehicle vehicle) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_SAVE)) {

            ps.setString(1, vehicle.getName());
            ps.setBigDecimal(2, vehicle.getVehicle_cost());
            ps.setInt(3, vehicle.getStock());
            ps.setLong(4, vehicle.getCategory().getId());
            ps.setLong(5, vehicle.getCreatedUser().getId());
            ps.setLong(6, vehicle.getUpdatedUser().getId());
            ps.setBigDecimal(7, vehicle.getHourly_rental());
            ps.setBigDecimal(8, vehicle.getDaily_rental());
            ps.setBigDecimal(9, vehicle.getWeekly_rental());
            ps.setBigDecimal(10, vehicle.getMonthly_rental());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Vehicle findById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll(int page) {

        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHICLE_FIND_ALL)) {
            int size = VehicleRentalConstants.PAGE_SIZE;
            int offset = (page -1) * size;
            ps.setInt(1, size);
            ps.setInt(2, offset);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                vehicles.add(new Vehicle(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("vehicle_cost"),
                        rs.getInt("stock"),
                        new Category(rs.getLong("category_id"), rs.getString("category_name")),
                        rs.getBigDecimal("hourly_rental"),
                        rs.getBigDecimal("daily_rental"),
                        rs.getBigDecimal("weekly_rental"),
                        rs.getBigDecimal("monthly_rental")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public void update(Vehicle vehicle) {

    }

    @Override
    public void delete(Long id) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.VEHİCLE_DELETE)
        ) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int findTotalPage() {

        try (Connection connection = DBUtil.getConnection();
        Statement stmt = connection.createStatement()){

            ResultSet rs = stmt.executeQuery(SqlScriptConstants.VEHICLE_TOTAL_PAGE_COUNT);

            if (rs.next()){
                int totalRows = rs.getInt(1); //9
                return (int) Math.ceil((double) totalRows/VehicleRentalConstants.PAGE_SIZE);//9/5 - > 2
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
