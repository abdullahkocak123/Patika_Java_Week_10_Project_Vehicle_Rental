package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Cart;
import vehicle_rental.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO implements BaseDAO<Cart>{

    @Override
    public void save(Cart cart) {

    }

    public Long saveInitial(Cart cart) {
        Long initialId = null;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_SAVE)
        ){
            ps.setLong(1, cart.getCustomerId());
            ps.setBigDecimal(2, cart.getTotalAmount() != null ? cart.getTotalAmount() : BigDecimal.ZERO);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                initialId = rs.getLong("id");
                cart.setId(initialId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return initialId;
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public List<Cart> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public void delete(Long id) {

    }

    public Cart findByCustomerId(Long customerId) {

        Cart cart = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_COSTOMER_ID)) {

            ps.setLong(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cart = new Cart(rs.getLong("id"),
                rs.getLong("customer_id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
}
