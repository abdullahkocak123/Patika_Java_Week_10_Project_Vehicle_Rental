package vehicle_rental.exception;

public class ExceptionMessagesConstants {



    private ExceptionMessagesConstants() {
    }

    public static final String CUSTOMER_EMAIL_DOES_NOT_EXIST = "Girilen email ile bir müşteri bulunmamaktadır!";

    public static final String CUSTOMER_EMAIL_ALREADY_EXIST = "Müşteri emaili zaten kayıtlı!";

    public static final String CUSTOMER_PASSWORD_DOES_NOT_MATCH = "Girilen email veya şifre bilgisi yanlış!";

    public static final String INDIVIDUAL_CUSTOMER_HAS_NEGATIVE_AGE = "Yaş pozitif bir sayı olmalıdır!" ;

    public static final String USER_EMAIL_DOES_NOT_EXIST = "Girilen email ile bir kullanıcı bulunmamaktadır!";

    public static final String USER_EMAIL_ALREADY_EXIST = "Girilen email zaten kayıtlı!";

    public static final String USER_PASSWORD_DOES_NOT_MATCH = "Girilen kullanıcı bilgisi veya şifre yanlış!";

    public static final String USER_IS_NOT_ADMIN = "Giriş yapan kullanıcı ADMIN rolüne sahip değildir!";

    public static final String USER_IS_NOT_ACTIVE = "Kullanıcı aktif değil ya da bulunamadı!";

    public static final String CATEGORY_NOT_FOUND = "Kategori bulunamadı!" ;

    public static final String VEHICLE_STOCK_IS_NOT_VALID = "İstenilen aracın yeterli stok adedi bulunmamaktadır";

    public static final String CART_ITEMS_EMPTY = "Sepetiniz boş!";

}
