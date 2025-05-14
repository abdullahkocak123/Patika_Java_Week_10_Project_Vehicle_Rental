package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.Customer;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO <Customer>{

    public void save(Customer customer) {

        try (Connection connection = DBUtil.getConnection()){

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_SAVE);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer findById(Long id){

        Customer customer = null;
        try(Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }



    public List<Customer> findAll(){

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()){

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SqlScriptConstants.CUSTOMER_FIND_ALL);

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Long id) {

    }

    public boolean existByEmail(String email) {

        try (Connection connection = DBUtil.getConnection()){

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findByEmail(String email) {

        Customer customer = null;
        try(Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
