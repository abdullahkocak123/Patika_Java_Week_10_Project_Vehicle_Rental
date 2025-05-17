package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Rent;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.util.List;

public class RentDAO implements BaseDAO<Rent> {


    public void save(Rent rent) {

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_SAVE);
            ps.setLong(1, rent.getCustomer().getId());
            ps.setTimestamp(2, Timestamp.valueOf(rent.getRentDate()));
            ps.setBigDecimal(3, rent.getTotalRent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rent findById(Long id) {
        return null;
    }

    @Override
    public List<Rent> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Rent rent) {

    }

    @Override
    public void delete(Long id) {

    }
}
