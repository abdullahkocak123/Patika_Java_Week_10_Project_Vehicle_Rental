package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Customer;
import vehicle_rental.model.Vehicle;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
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
                v.setPrice(rs.getBigDecimal("price"));
                v.setStock(rs.getInt("stock"));
                v.setCreatedDate(LocalDateTime.parse(rs.getString("createddate")));
                v.setUpdatedDate(LocalDateTime.parse(rs.getString("updateddate")));
                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void save(Vehicle vehicle) {

    }

    @Override
    public Vehicle findById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return List.of();
    }

    @Override
    public void update(Vehicle vehicle) {

    }

    @Override
    public void delete(Long id) {

    }
}
