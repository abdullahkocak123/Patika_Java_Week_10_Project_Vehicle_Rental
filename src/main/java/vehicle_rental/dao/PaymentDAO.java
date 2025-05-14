package vehicle_rental.dao;

import vehicle_rental.model.Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {

    private static final String saveScript = """
            INSERT INTO payment (rent_id, payment_method, amount)
            VALUES (?,?,?)
            """;

    public void save(Payment payment) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setLong(1, payment.getRent().getId());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setBigDecimal(3, payment.getAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
