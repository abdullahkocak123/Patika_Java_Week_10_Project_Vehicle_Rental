package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Rent;
import vehicle_rental.model.RentItem;
import vehicle_rental.model.Vehicle;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RentDAO implements BaseDAO<Rent> {


    public long save(Rent rent) {

        long generatedId = 0;
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_SAVE, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, rent.getCustomer().getId());
            ps.setTimestamp(2, Timestamp.valueOf(rent.getRentDate()));
            ps.setBigDecimal(3, rent.getTotalAmount());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    long id =rs.getLong(1);
                    rent.setId(id);
                    return id;
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public List<Rent> findAllByCustomerId(Long customerId) {
        List<Rent> rents = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.RENT_FIND_BY_CUSTOMER_ID)) {

            ps.setLong(1, customerId);

            ResultSet rs = ps.executeQuery();
            Map<Long, Rent> rentMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long rentId = rs.getLong("rent_id");

                Rent rent = rentMap.get(rentId);
                if (rent == null) {
                    rent = new Rent();
                    rent.setId(rentId);
                    rent.setRentDate(rs.getTimestamp("rentdate").toLocalDateTime());
                    rent.setRentItems(new ArrayList<>());
                    rentMap.put(rentId, rent);
                }

                RentItem item = new RentItem();
                item.setVehicle(new Vehicle(
                        rs.getLong("vehicle_id"),
                        rs.getString("vehicle_name")
                ));
                item.setRental_type(rs.getString("rental_type"));
                item.setRental_duration(rs.getInt("rental_duration"));
                item.setQuantity(rs.getInt("quantity"));
                item.setRental_unit_price(rs.getBigDecimal("rental_unit_price"));

                rent.getRentItems().add(item);
            }

            rents.addAll(rentMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rents;
    }

}
