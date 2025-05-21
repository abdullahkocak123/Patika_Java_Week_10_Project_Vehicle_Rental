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

    public static final String RENT_FIND_BY_CUSTOMER_ID = """
            SELECT
                r.id AS rent_id,
                r.rentdate,
                ri.quantity,
                ri.rental_type,
                ri.rental_duration,
                ri.rental_unit_price,
                v.id AS vehicle_id,
                v.name AS vehicle_name
            FROM rent r
            JOIN rent_item ri ON r.id = ri.rent_id
            JOIN vehicle v ON ri.vehicle_id = v.id
            WHERE r.customer_id = ?
            ORDER BY r.rentdate DESC
            """;

    public static final String PAYMENT_SAVE = """
            INSERT INTO payment (rent_id, payment_method, amount)
            VALUES (?,?,?)
            """;

    public static final String VEHICLE_SEARCH_BY_NAME = """
            SELECT  v.id as id,
                    v.name as name,
                    v.vehicle_cost as vehicle_cost,
                    v.stock as stock,
                    c.id as category_id,
                    c.name as category_name,
                    v.hourly_rental as hourly_rental,
                    v.daily_rental as daily_rental,
                    v.weekly_rental as weekly_rental,
                    v.monthly_rental as monthly_rental
            FROM vehicle v
            LEFT JOIN category c ON c.id = v.category_id
            WHERE v.name ILIKE ?
            """;

    public static final String VEHICLE_SAVE = """
            INSERT INTO VEHICLE (name, vehicle_cost, stock, category_id, created_by, updated_by, hourly_rental, 
            daily_rental, weekly_rental, monthly_rental)
            VALUES (?,?,?,?,?,?,?,?,?,?)
            """;

    public static final String VEHICLE_FIND_ALL = """
            SELECT  v.id as id,
                    v.name as name,
                    v.vehicle_cost as vehicle_cost,
                    v.stock as stock,
                    v.hourly_rental as hourly_rental,
                    v.daily_rental as daily_rental,
                    v.weekly_rental as weekly_rental,
                    v.monthly_rental as monthly_rental,
                    c.id as category_id,
                    c.name as category_name
            FROM vehicle v, category c WHERE c.id = v.category_id
            ORDER BY v.id
            LIMIT ? OFFSET ?
            """;

    public static final String VEHICLE_FIND_BY_CATEGORY_NAME = """
            SELECT v.id as id,
                    v.name as name,
                    v.vehicle_cost as vehicle_cost,
                    v.stock as stock,
                    c.id as category_id,
                    c.name as category_name,
                    v.hourly_rental as hourly_rental,
                    v.daily_rental as daily_rental,
                    v.weekly_rental as weekly_rental,
                    v.monthly_rental as monthly_rental
            FROM vehicle v
            JOIN category c ON c.id = v.category_id
            WHERE c.name ILIKE ?
            ORDER BY v.id
            LIMIT ? OFFSET ?
            """;

    public static final String VEHICLE_TOTAL_PAGE_COUNT = """
            SELECT COUNT(*) FROM vehicle
            """;

    public static final String VEHICLE_TOTAL_PAGE_BY_FILTER_COUNT = """
            SELECT COUNT(*)
            FROM vehicle v
            JOIN category c ON c.id = v.category_id
            WHERE c.name ILIKE ?
            """;

    public static final String VEHICLE_TOTAL_PAGE_AT_CART_COUNT = """
            SELECT COUNT(*)
            FROM cart
            WHERE customer_id = ?
            """;

    public static final String VEHİCLE_DELETE = """
            DELETE FROM vehicle WHERE id = ?
            """;

    public static final String VEHICLE_FIND_BY_NAME = """
            SELECT * FROM vehicle WHERE name ILIKE ?
            """;

    public static final String VEHICLE_FIND_BY_ID = """
            SELECT * FROM vehicle WHERE id = ?
            """;

    public static final String VEHICLE_UPDATE_STOCK = """
            UPDATE vehicle SET stock = ? WHERE id = ?
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

    public static final String CART_SAVE = """
            INSERT INTO cart (customer_id, total_amount) VALUES (?,?)
            RETURNING id
            """;

    public static final String CART_FIND_BY_COSTOMER_ID = """
            SELECT * FROM cart WHERE customer_id = ?
            """;

    public static final String CART_ITEM_SAVE = """
            INSERT INTO cart_items (cart_id, vehicle_id, quantity, rental_type, rental_duration, rental_unit_price)
            VALUES (?,?,?,?,?,?)
            RETURNING id
            """;

    public static final String CART_ITEM_DELETE = """
            DELETE FROM cart_items 
            WHERE cart_id = ?
            """;

    public static final String CART_ITEM_FIND_BY_CUSTOMER_ID = """
            SELECT
            ci.id as cart_item_id,
            ci.quantity as quantity,
            ci.rental_type as rental_type,
            ci.rental_duration as rental_duration,
            ci.rental_unit_price as rental_unit_price,
            v.id as vehicle_id,
            v.name as vehicle_name
            FROM cart_items ci
            JOIN cart c ON c.id = ci.cart_id
            JOIN vehicle v ON v.id = ci.vehicle_id
            WHERE c.customer_id = ?
            ORDER BY v.id
            LIMIT ? OFFSET ?
            """;

    public static final String CART_ITEM_FIND_BY_CUSTOMER_ID_WITHOUT_PAGING = """
            SELECT
            ci.id as cart_item_id,
            ci.quantity as quantity,
            ci.rental_type as rental_type,
            ci.rental_duration as rental_duration,
            ci.rental_unit_price as rental_unit_price,
            v.id as vehicle_id,
            v.name as vehicle_name
            FROM cart_items ci
            JOIN cart c ON c.id = ci.cart_id
            JOIN vehicle v ON v.id = ci.vehicle_id
            WHERE c.customer_id = ?
            """;

    public static final String RENT_ITEMS_SAVE = """
            INSERT INTO rent_item(rent_id, vehicle_id, rental_type, rental_duration, quantity, rental_unit_price)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String CART_FIND_ALL_BY_COSTOMER_ID = """
            SELECT * 
            FROM cart c
            JOIN vehicle v ON v.id = c.vehicle_id
            WHERE customer_id = ?
            ORDER BY c.createddate  DESC
            """;


}
