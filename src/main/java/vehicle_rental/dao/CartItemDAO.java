package vehicle_rental.dao;

import vehicle_rental.constants.VehicleRentalConstants;
import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.CartItem;
import vehicle_rental.model.Vehicle;
import vehicle_rental.model.enums.RentalType;
import vehicle_rental.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {

    public int clear(long cartId) {

        int affectedRow = 0;
        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_DELETE)) {

            ps.setLong(1, cartId);

            affectedRow = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRow;
    }


    public void save(CartItem cartItem) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_SAVE)
        ) {
            ps.setLong(1, cartItem.getCart().getId());
            ps.setLong(2, cartItem.getVehicle().getId());
            ps.setInt(3, cartItem.getQuantity());
            ps.setString(4, cartItem.getRentalType().name());
            ps.setInt(5, cartItem.getRental_duration());
            ps.setBigDecimal(6, cartItem.getRental_unit_price());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CartItem> findByCustomerId(Long customerId, int page) {

        List<CartItem> cartItems = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_FIND_BY_CUSTOMER_ID);
        ) {

            int size = VehicleRentalConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setLong(1, customerId);
            ps.setInt(2, size);
            ps.setInt(3, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Long vehicleId = rs.getLong("vehicle_id");
                String vehicleName = rs.getString("vehicle_name");
                int quantity = rs.getInt("quantity");
                int rental_duration = rs.getInt("rental_duration");
                String rental_type_str = rs.getString("rental_type");
                BigDecimal rental_unit_price = rs.getBigDecimal("rental_unit_price");

                Vehicle vehicle = new Vehicle();
                vehicle.setId(vehicleId);
                vehicle.setName(vehicleName);

                cartItems.add(new CartItem(vehicle, quantity, RentalType.valueOf(rental_type_str), rental_duration, rental_unit_price));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }



    /* muhtemelen iptal
    public List<Cart> findAllByCustomerId(Long customerId) {

        Cart cart = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_FIND_ALL_BY_COSTOMER_ID);
        ) {

            ps.setLong(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cart = new Cart();

                //cart.setItems(List.of(new CartItem(new Vehicle(rs.getString("vehicle_name")))));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
     */
}
