package vehicle_rental.dao.constants;

public class SqlScriptConstants {


    private SqlScriptConstants() {
    }

    public static final String INDIVIDUAL_CUSTOMER_SAVE = """
            INSERT INTO customer (name, email, password, age, type) VALUES (?,?,?,?,?)            
            """;

    public static final String CORPORATE_CUSTOMER_SAVE = """
            INSERT INTO customer (name, email, password, type) VALUES (?,?,?,?)            
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
            SELECT  v.id as id,
                    v.name as name,
                    v.price as price,
                    v.stock as stock,
                    c.id as category_id,
                    c.name as category_name
            FROM vehicle v
            LEFT JOIN category c ON c.id = v.category_id
            WHERE v.name ILIKE ?
            """;

    public static final String VEHICLE_SAVE = """
            INSERT INTO VEHICLE (name, price, stock, category_id, created_by, updated_by)
            VALUES (?,?,?,?,?,?)
            """;

    public static final String PRODUCT_FIND_ALL = """
            SELECT  v.id as id,
                    v.name as name,
                    v.price as price,
                    v.stock as stock,
                    c.id as category_id,
                    c.name as category_name
            FROM vehicle v, category c WHERE c.id = v.category_id
            ORDER BY v.id
            LIMIT ? OFFSET ?
            """;

    public static final String VEHICLE_TOTAL_PAGE_COUNT = """
            SELECT COUNT(*) FROM vehicle
            """;

    public static final String VEHİCLE_DELETE = """
            DELETE FROM vehicle WHERE id = ?
            """;

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
            """;

    public static final String CATEGORY_DELETE = """
            DELETE FROM category WHERE id = ?
            """;

    public static final String CATEGORY_FIND_BY_ID = """
            SELECT * FROM category WHERE id = ?
            """;

    public static final String CATEGORY_FIND_ALL = """
            SELECT * FROM category
            """;


}
