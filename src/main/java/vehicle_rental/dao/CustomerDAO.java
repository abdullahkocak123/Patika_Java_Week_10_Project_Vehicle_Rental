package vehicle_rental.dao;

import vehicle_rental.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final String saveScript = """
            INSERT INTO customer (name, email, password) VALUES (?,?,?)            
            """;

    private final String findByIdScript = """
            SELECT * FROM customer WHERE id = ?            
            """;

    private final String findAllScript = """
            SELECT * FROM customer            
            """;

    private final String existByEmailScript = """
            SELECT * FROM customer WHERE email = ?          
            """;

    public void save(Customer customer) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Customer findById(Long id){

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        Customer customer = null;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(findByIdScript);
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
            throw new RuntimeException(e);
        }
        return customer;
    }

    public List<Customer> findAll(){

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        List<Customer> customers = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(findAllScript);

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
            throw new RuntimeException(e);
        }
        return customers;
    }

    public boolean existByEmail(String email) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(existByEmailScript);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findByEmail(String email) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        Customer customer = null;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(existByEmailScript);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.setName(resultSet.getString("name"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPassword(resultSet.getString("password"));
            customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
            customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
