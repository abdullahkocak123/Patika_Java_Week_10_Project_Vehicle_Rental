package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Payment;
import vehicle_rental.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment> {


    public void save(Payment payment) {

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE);
            ps.setLong(1, payment.getRent().getId());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setBigDecimal(3, payment.getAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment findById(Long id) {
        return null;
    }

    @Override
    public List<Payment> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(Long id) {

    }
}
