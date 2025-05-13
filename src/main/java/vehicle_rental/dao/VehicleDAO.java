package vehicle_rental.dao;

import vehicle_rental.model.Customer;
import vehicle_rental.model.Vehicle;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    private static final String searchByNameScript = """
            SELECT * FROM vehicle WHERE name LIKE ?
            """;

    public List<Vehicle> searchByName(String name) {
        List<Vehicle> vehicles = new ArrayList<>();

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = connection.prepareStatement(searchByNameScript);
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
}
