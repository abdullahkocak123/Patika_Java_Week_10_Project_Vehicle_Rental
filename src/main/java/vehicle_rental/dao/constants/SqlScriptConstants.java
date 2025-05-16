package vehicle_rental.dao.constants;

public class SqlScriptConstants {


    private SqlScriptConstants() {
    }

    public static final String CUSTOMER_SAVE = """
            INSERT INTO customer (name, email, password) VALUES (?,?,?)            
            """;

    public static final String CUSTOMER_FIND_BY_ID = """
            SELECT * FROM customer WHERE id = ?            
            """;

    public static final String CUSTOMER_FIND_ALL = """
            SELECT * FROM customer            
            """;

    public static final String CUSTOMER_EXIST_BY_EMAIL = """
            SELECT * FROM customer WHERE email = ?          
            """;

    public static final String RENT_SAVE = """
            INSERT INTO rent (customer_id, rentdate, totalrent)
            VALUES (?,?,?)
            """;

    public static final String PAYMENT_SAVE = """
            INSERT INTO payment (rent_id, payment_method, amount)
            VALUES (?,?,?)
            """;

    public static final String VEHICLE_SEARCH_BY_NAME = """
            SELECT * FROM vehicle WHERE name LIKE ?
            """;

    public static final String VEHİCLE_SAVE = """
            INSERT INTO VEHICLE (name, price, stock, category_id, created_by, updated_by)
            VALUES (?,?,?,?,?,?)
            """ ;

    public static final String USER_SAVE = """
            INSERT INTO users (username, password, role, active)
            VALUES (?,?,?,?)
            """;

    public static final String USER_FIND_BY_NAME = """
            SELECT * FROM users WHERE username = ?
            """;

    public static final String CATEGORY_SAVE = """
            INSERT INTO category (name, created_by, updated_by)
            VALUES (?,?,?)
            """ ;
}
