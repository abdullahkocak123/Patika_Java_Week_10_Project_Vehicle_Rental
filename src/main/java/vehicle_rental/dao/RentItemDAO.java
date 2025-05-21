package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.RentItem;
import vehicle_rental.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RentItemDAO implements BaseDAO<RentItem>{

    public void saveAll(List<RentItem> rentItems) {

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_ITEMS_SAVE)) {

            for (RentItem rentItem : rentItems) {
                ps.setLong(1, rentItem.getRent().getId());
                ps.setLong(2, rentItem.getVehicle().getId());
                ps.setString(3, rentItem.getRental_type());
                ps.setInt(4, rentItem.getRental_duration());
                ps.setInt(5, rentItem.getQuantity());
                ps.setBigDecimal(6, rentItem.getRental_unit_price());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public long save(RentItem rentItem) {
        return 0;
    }

    @Override
    public RentItem findById(Long id) {
        return null;
    }

    @Override
    public List<RentItem> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(RentItem rentItem) {

    }

    @Override
    public void delete(Long id) {

    }
}

