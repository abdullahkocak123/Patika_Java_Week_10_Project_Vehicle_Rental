package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Payment;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment> {


    public long save(Payment payment) {

        long generatedId = 0;
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE);
            ps.setLong(1, payment.getRent().getId());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setBigDecimal(3, payment.getAmount());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                generatedId = rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
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
