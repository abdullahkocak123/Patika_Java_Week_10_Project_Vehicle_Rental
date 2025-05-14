package vehicle_rental.exception;

public class ExceptionMessagesConstants {


    private ExceptionMessagesConstants() {
    }

    public static final String CUSTOMER_EMAIL_DOES_NOT_EXIST = "Girilen email ile bir müşteri bulunmamaktadır!";

    public static final String CUSTOMER_EMAIL_ALREADY_EXIST = "Müşteri emaili zaten kayıtlı!";

    public static final String CUSTOMER_PASSWORD_DOES_NOT_MATCH = "Girilen email veya şifre bilgisi yanlış!";

    public static final String USER_EMAIL_DOES_NOT_EXIST = "Girilen email ile bir kullanıcı bulunmamaktadır!";

    public static final String USER_EMAIL_ALREADY_EXIST = "Girilen email zaten kayıtlı!";

    public static final String USER_PASSWORD_DOES_NOT_MATCH = "Girilen kullanıcı bilgisi veya şifre yanlış!";
}
