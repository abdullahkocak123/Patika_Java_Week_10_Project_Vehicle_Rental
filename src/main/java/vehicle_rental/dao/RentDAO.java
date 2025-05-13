package vehicle_rental.dao;

import vehicle_rental.model.Rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentDAO {

    private static final String saveScript = """
            INSERT INTO rent (customer_id, rentdate, totalrent, createddate, updateddate)
            VALUES (?,?,?,?,?)
            """;

    public static void save(Rent rent) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setLong(1, rent.getCustomer().getId());
            //ps.setDate();
            ps.setBigDecimal(3, rent.getTotalRent());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
