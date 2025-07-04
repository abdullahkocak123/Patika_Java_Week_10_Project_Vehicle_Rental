package vehicle_rental.dao;

import vehicle_rental.dao.constants.SqlScriptConstants;
import vehicle_rental.model.CorporateCustomer;
import vehicle_rental.model.Customer;
import vehicle_rental.model.IndividualCustomer;
import vehicle_rental.model.enums.CustomerType;
import vehicle_rental.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO <Customer>{

    @Override
    public long save(Customer customer) {

        throw new UnsupportedOperationException("CustomerDAO için save(Customer) kullanmayınız. " +
                "Bunun yerine saveIndividual/saveCorporate kullanılmalıdır.");

    }

    public void saveIndividual(IndividualCustomer individualCustomer) {

        try (Connection connection = DBUtil.getConnection()){

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.INDIVIDUAL_CUSTOMER_SAVE);
            ps.setString(1, individualCustomer.getName());
            ps.setString(2, individualCustomer.getEmail());
            ps.setString(3, individualCustomer.getPassword());
            ps.setInt(4, individualCustomer.getAge());
            ps.setString(5, individualCustomer.getCustomerType().name());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCorporate(CorporateCustomer corporateCustomer) {

        try (Connection connection = DBUtil.getConnection()){

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CORPORATE_CUSTOMER_SAVE);
            ps.setString(1, corporateCustomer.getName());
            ps.setString(2, corporateCustomer.getEmail());
            ps.setString(3, corporateCustomer.getPassword());
            ps.setString(4, corporateCustomer.getCustomerType().name());
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
                customer = new IndividualCustomer();
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



    public List<Customer> findAll(int page){

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()){

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SqlScriptConstants.CUSTOMER_FIND_ALL);

            while (resultSet.next()){
                Customer customer = new IndividualCustomer();
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

        IndividualCustomer customer = null;
        try(Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_BY_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                customer = new IndividualCustomer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setCreatedDate(new Timestamp(rs.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updatedDate").getTime()).toLocalDateTime());
                customer.setCustomerType(CustomerType.valueOf(rs.getString("type")));
                customer.setAge(rs.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
