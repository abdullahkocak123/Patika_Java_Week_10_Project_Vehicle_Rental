package vehicle_rental;

import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.*;
import vehicle_rental.model.enums.*;
import vehicle_rental.service.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static User LOGINED_USER;
    private static Customer LOGINED_CUSTOMER;

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CategoryService categoryService = new CategoryService();
    private static final VehicleService vehicleService = new VehicleService();
    private static final CartService cartService = new CartService();
    private static final CartItemService cartItemService = new CartItemService();
    private static final RentService rentService = new RentService();

    public static void main(String[] args) {


        while (true) {

            getMainMenu();

            String choice = scanner.nextLine();

            try {
                switch (choice) {

                    case "1":
                        getUserMenu();
                        break;
                    case "2":
                        getCustomerMenu();
                        break;
                    case "0":
                        System.out.println("Çıkış yapılıyor...");
                        return;
                    default:
                        System.out.println("Geçersiz seçim!");
                }
            } catch (VehicleRentalException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void getCustomerMenu() throws VehicleRentalException {
        while (true) {
            System.out.println("=== MÜŞTERİ GİRİŞ PANELİ ===");
            System.out.println("1 - Müşteri Kayıt Ol");
            System.out.println("2 - Müşteri Giriş Yap");
            System.out.println("0 - Geri Dön");
            System.out.print("Seçim yapınız: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    loginCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void getUserMenu() throws VehicleRentalException {
        while (true) {
            System.out.println("=== KULLANICI GİRİŞ PANELİ ===");
            System.out.println("1 - Kullanıcı Kayıt Ol");
            System.out.println("2 - Kullanıcı Giriş Yap");
            System.out.println("0 - Geri Dön");
            System.out.print("Seçim yapınız: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    resgisterUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void loginUser() throws VehicleRentalException {
        System.out.print("Kullanıcı Adı: ");
        String userName = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        User loginedUser = userService.login(userName, password);

        if (loginedUser != null && loginedUser.getActive()) {

            LOGINED_USER = loginedUser;

            getLoginedUserMenu();

        } else {
            throw new RuntimeException(ExceptionMessagesConstants.USER_IS_NOT_ACTIVE);
        }

    }

    private static void getLoginedUserMenu() throws VehicleRentalException {


        while (true) {
            System.out.println("=== LOGİN OLAN KULLANICI MENÜSÜ ===");
            System.out.println("1 - Kategori Oluştur");
            System.out.println("2 - Kategori Listele");
            System.out.println("3 - Kategori Sil");
            System.out.println("4 - Araç Oluştur");
            System.out.println("5 - Araç Listele");
            System.out.println("6 - Araç Sil");
            System.out.println("7 - Araç Arama");
            System.out.println("8 - Araç Filtreleme(Kategori bazlı)");
            System.out.println("9 - Kiralama Listele");
            System.out.println("0 - Geri");
            System.out.print("Seçim yapınız: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    categoryList();
                    break;
                case "3":
                    categoryDelete();
                    break;
                case "4":
                    vehicleCreate();
                    break;
                case "5":
                    vehicleList();
                    break;
                case "6":
                    vehicleDelete();
                    break;
                case "7":
                    vehicleSearch();
                    break;
                case "8":
                    vehicleFiltering();
                    break;
                case "9":
                    rentList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void vehicleFiltering() {
        System.out.print("Kategori ismi giriniz: ");
        String categoryName = scanner.nextLine();

        int totalPageByFilter = vehicleService.getTotalPageByFilter(categoryName);

        int page = 1;

        do {
            List<Vehicle> vehicles = vehicleService.getByCategoryName(categoryName, page);

            System.out.println("\n==== ARAÇ LİSTESİ (Filtreleme Sonucu)(Sayfa )" + page + "/" + totalPageByFilter + "====");

            vehicles.forEach(v ->
                    System.out.printf("%s - %s - Araç Beddeli(%s) - Saatlik(%s) - Günlük(%s) - Haftalık(%s) - Aylık(%s)\n",
                            v.getName(), v.getCategory().getName(),
                            v.getVehicle_cost(),
                            v.getHourly_rental(), v.getDaily_rental(),
                            v.getWeekly_rental(), v.getMonthly_rental())
            );
            System.out.println("======");

            System.out.print("Sonraki sayfa sayısı: ");
            String pageStr = scanner.nextLine();
            page = Integer.parseInt(pageStr);

        } while (page <= totalPageByFilter);

    }

    private static void vehicleSearch() {

        System.out.print("Araç ismi giriniz: ");
        String searchVehicleName = scanner.nextLine();

        List<Vehicle> vehicles = vehicleService.search(searchVehicleName);

        System.out.println("\n==== ARAÇ LİSTESİ (Arama Sonucu) ====");

        vehicles.forEach(v ->
                System.out.printf("%s - %s - Araç Beddeli(%s) - Saatlik(%s) - Günlük(%s) - Haftalık(%s) - Aylık(%s)\n",
                        v.getName(), v.getCategory().getName(),
                        v.getVehicle_cost(),
                        v.getHourly_rental(), v.getDaily_rental(),
                        v.getWeekly_rental(), v.getMonthly_rental())
        );

        System.out.println("======");

    }

    private static void rentList() {

        List<Rent> rents = rentService.getAllByCustomer(LOGINED_CUSTOMER);

        System.out.println("--------------GEÇMİŞ KİRALAMALARIM-------------");

        for (Rent rent:rents){
            System.out.printf("Kiralama #%d - %s\n" ,
                    rent.getId(), rent.getRentDate());

            for (RentItem item : rent.getRentItems()){
                System.out.printf(" -> %d %s - %d %s - Kira bedeli: %s\n",
                        item.getQuantity(),
                        item.getVehicle().getName(),
                        item.getRental_duration(),
                        item.getRental_type(),
                        item.getRental_unit_price().
                                multiply(BigDecimal.valueOf(item.getQuantity())).
                                multiply(BigDecimal.valueOf(item.getRental_duration()))
                        );
            }

        }

        System.out.println("----------------------------------------------");

    }

    private static void vehicleDelete() {
        System.out.print("Silinecek araç id'sini giriniz: ");
        String vehicleId = scanner.nextLine();
        vehicleService.deleteById(Long.parseLong(vehicleId));
    }

    private static void vehicleList() {
        int totalPage = vehicleService.getTotalPage();

        int page = 1;

        do {
            List<Vehicle> vehicles = vehicleService.getAll(page);

            System.out.println("\n==== ARAÇ LİSTESİ(Sayfa )" + page + "/" + totalPage + "====");

            vehicles.forEach(v ->
                    System.out.printf("%s - %s - Araç Beddeli(%s) - Saatlik(%s) - Günlük(%s) - Haftalık(%s) - Aylık(%s)\n",
                            v.getName(), v.getCategory().getName(),
                            v.getVehicle_cost(),
                            v.getHourly_rental(), v.getDaily_rental(),
                            v.getWeekly_rental(), v.getMonthly_rental())
            );
            System.out.println("======");

            System.out.print("Sonraki sayfa sayısı: ");
            String pageStr = scanner.nextLine();
            page = Integer.parseInt(pageStr);

        } while (page <= totalPage);


    }

    private static void vehicleCreate() throws VehicleRentalException {
        System.out.print("Araç ismi giriniz: ");
        String name = scanner.nextLine();
        System.out.print("Araç bedelini giriniz: ");
        String vehicle_cost = scanner.nextLine();
        System.out.print("Araç adedini giriniz: ");
        String stock = scanner.nextLine();
        System.out.print("Kategori id giriniz: ");
        String categoryId = scanner.nextLine();
        System.out.print("Saatlik kiralama bedelini giriniz: ");
        String hourly_rental = scanner.nextLine();
        System.out.print("Günlük kiralama bedelini giriniz: ");
        String daily_rental = scanner.nextLine();
        System.out.print("Haftalık kiralama bedelini giriniz: ");
        String weekly_rental = scanner.nextLine();
        System.out.print("Aylık kiralama bedelini giriniz: ");
        String monthly_rental = scanner.nextLine();

        Category category = categoryService.getById(Long.parseLong(categoryId));

        Vehicle vehicle = new Vehicle(name, new BigDecimal(vehicle_cost), Integer.parseInt(stock), category,
                new BigDecimal(hourly_rental), new BigDecimal(daily_rental), new BigDecimal(weekly_rental),
                new BigDecimal(monthly_rental));
        vehicleService.save(vehicle, LOGINED_USER);

    }

    private static void categoryDelete() {

        System.out.print("Kategori id giriniz: ");
        String categoryId = scanner.nextLine();

        categoryService.deleteById(Long.parseLong(categoryId));

    }

    private static void categoryList() {
        List<Category> categoryList = categoryService.getAll();
        categoryList.forEach(System.out::println);
    }

    private static void createCategory() throws VehicleRentalException {
        throw new VehicleRentalException("Not Implemented");
    }

    private static void resgisterUser() throws VehicleRentalException {
        System.out.print("Kullanıcı Adı: ");
        String userName = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();
        System.out.print("Rol Seçiniz: (ADMIN, SUPPORT)");
        String roleString = scanner.nextLine().toUpperCase();

        Role role = Role.valueOf(roleString);

        userService.save(userName, password, role);
    }

    private static void getMainMenu() {
        System.out.println("=== GİRİŞ TÜRÜ SEÇİN ===");
        System.out.println("1 - Kullanıcı Girişi (ADMIN, SUPPORT)");
        System.out.println("2 - Müşteri Girişi");
        System.out.println("0 - Çıkış");
        System.out.print("Seçim yapınız: ");
    }

    private static void loginCustomer() throws VehicleRentalException {

        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();


        CustomerService customerService = new CustomerService();
        LOGINED_CUSTOMER = customerService.login(email, password);

        while (true) {
            System.out.println("1 - Araç Listele");
            System.out.println("2 - Araç Arama");
            System.out.println("3 - Araç Filtreleme(Kategori bazlı)");
            System.out.println("4 - Sepete Araç Ekle");
            System.out.println("5 - Sepeti Görüntüle");
            System.out.println("6 - Sepeti Temizle");
            System.out.println("7 - Sepetteki Araçları Kirala");
            System.out.println("8 - Kiralamaları Listele");
            System.out.println("0 - Geri");
            System.out.print("Seçim yapınız: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    vehicleList();
                    break;
                case "2":
                    vehicleSearch();
                    break;
                case "3":
                    vehicleFiltering();
                    break;
                case "4":
                    addVehicleToCart();
                    break;
                case "5":
                    listCart();
                    break;
                case "6":
                    clearCart();
                    break;
                case "7":
                    CreateRent();
                    break;
                case "8":
                    rentList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz seçim!");

            }
        }

    }

    private static void CreateRent() throws VehicleRentalException {

        System.out.println("ÖDEME YÖNTEMLERİ: ");
        System.out.println("1 - Kredi kartı");
        System.out.println("2 - Banka kartı");
        System.out.println("3 - Paypal");
        System.out.println("4 - Hesaba transfer");
        System.out.print("Ödeme yöntemi seçiniz: ");

        int payment_choice = scanner.nextInt();
        scanner.nextLine();

        PaymentMethod paymentMethod;
        switch (payment_choice){
            case 1 -> paymentMethod = PaymentMethod.CREDIT_CARD;
            case 2 -> paymentMethod = PaymentMethod.DEBIT_CARD;
            case 3 -> paymentMethod = PaymentMethod.PAYPAL;
            case 4 -> paymentMethod = PaymentMethod.BANK_TRANSFER;
            default -> {
                System.out.println("Geçersiz seçimé");
                return;
            }
        }

        rentService.save(LOGINED_CUSTOMER, paymentMethod);

    }

    private static void clearCart() {
        cartService.clear(LOGINED_CUSTOMER);
    }

    private static void listCart() {

        int totalPageAtCart = vehicleService.getTotalPageAtCart(LOGINED_CUSTOMER.getId());

        int page = 1;

        do {
            List<CartItem> cartItems = cartItemService.getByCustomer(LOGINED_CUSTOMER, page);

            System.out.println("\n==== ARAÇ LİSTESİ (Filtreleme Sonucu)(Sayfa )" + page + "/" + totalPageAtCart + "====");

            cartItems.forEach(item ->
                    System.out.printf("%s - %s adet - %s %s - Birim Ücret(%s)\n",
                            item.getVehicle().getName(),
                            item.getQuantity(),
                            item.getRental_duration(),
                            item.getRentalType().getDisplayName(),
                            item.getRental_unit_price()));

            System.out.println("======");

            System.out.print("Sonraki sayfa sayısı: ");
            String pageStr = scanner.nextLine();
            page = Integer.parseInt(pageStr);

        } while (page <= totalPageAtCart);
    }

    private static void addVehicleToCart() throws VehicleRentalException {

        boolean isContinue = true;

        if (LOGINED_CUSTOMER == null) {
            System.out.println("Müşteri oturumu açılmamış");
            return;
        }

        BigDecimal deposit = BigDecimal.valueOf(0);
        while (isContinue) {
            System.out.print("Araç adı giriniz: ");
            String vehicleName = scanner.nextLine();

            Vehicle vehicle = vehicleService.getByName(vehicleName);

            if (vehicle == null) {
                System.out.println("Araç bulunamadı!");
            } else {

                if (LOGINED_CUSTOMER.getCustomerType() == CustomerType.INDIVIDUAL &&
                        LOGINED_CUSTOMER instanceof IndividualCustomer &&
                        vehicle.getVehicle_cost().compareTo(BigDecimal.valueOf(2000000)) > 0) {

                    IndividualCustomer individualCustomer = (IndividualCustomer) LOGINED_CUSTOMER;
                    if (individualCustomer.getAge() <= 30) {
                        System.out.println("30 yaşından büyük olmadığınız için bedeli 2 Milyon TL'den fazla " +
                                "olan (" + vehicle.getVehicle_cost() + " TL)bu aracı kiralayamazsınız!");

                        System.out.println(LOGINED_CUSTOMER);
                        System.out.println(vehicle);
                        System.out.println((individualCustomer.getAge()));

                        continue;
                    } else if (individualCustomer.getAge() > 30) {
                        System.out.println("Bedeli 2 Milyon TL'den fazla olan (" + vehicle.getVehicle_cost() + " TL) bu aracı " +
                                "kiralayabilmek için araç bedelinin %10 kadarı depozito alınacaktır!");
                        deposit = deposit.add(vehicle.getVehicle_cost().divide(BigDecimal.valueOf(10), MathContext.DECIMAL128));
                    }
                }


                System.out.print("Adet giriniz: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                if (vehicle.getStock() < quantity) {
                    throw new VehicleRentalException(ExceptionMessagesConstants.VEHICLE_STOCK_IS_NOT_VALID);
                }

                BigDecimal rental_amount = null;
                RentalType rental_type = null;
                BigDecimal rental_unit_price = null;
                int rental_duration = 0;

                if (LOGINED_CUSTOMER.getCustomerType() == CustomerType.CORPORATE) {
                    System.out.println("Kurumsal müşterimiz olduğunuz için kiralamalarınız aylık olarak hesaplanacak!");
                    rental_type = RentalType.MONTHLY;
                    rental_unit_price = vehicle.getMonthly_rental();
                    System.out.print("Kaç aylık kiralama istiyorsunuz?: ");
                    int time = scanner.nextInt();
                    scanner.nextLine();

                    rental_amount = vehicle.getMonthly_rental().multiply(BigDecimal.valueOf(quantity))
                            .multiply(BigDecimal.valueOf(time));
                } else if (LOGINED_CUSTOMER.getCustomerType() == CustomerType.INDIVIDUAL) {
                    System.out.print("Kiralama türü giriniz --> saatlik(s), günlük(g), haftalık(h), aylık(a): ");
                    String rental_type_char = scanner.nextLine().toLowerCase();


                    switch (rental_type_char) {
                        case "s" -> {
                            rental_unit_price = vehicle.getHourly_rental();
                            rental_type = RentalType.HOURLY;
                        }
                        case "g" -> {
                            rental_unit_price = vehicle.getDaily_rental();
                            rental_type = RentalType.DAILY;
                        }
                        case "h" -> {
                            rental_unit_price = vehicle.getWeekly_rental();
                            rental_type = RentalType.WEEKLY;
                        }
                        case "a" -> {
                            rental_unit_price = vehicle.getMonthly_rental();
                            rental_type = RentalType.MONTHLY;
                        }
                        default -> {
                            System.out.println("Geçersiz seçim!");
                            continue;
                        }
                    }


                    System.out.print("Ne kadar süre(sayısal) kiralamak istiyorsunuz?: ");
                    rental_duration = scanner.nextInt();
                    scanner.nextLine();

                    rental_amount = rental_unit_price.multiply(BigDecimal.valueOf(quantity))
                            .multiply(BigDecimal.valueOf(rental_duration));
                }

                cartService.addToCart(LOGINED_CUSTOMER, vehicle, quantity, rental_type, rental_duration, rental_unit_price); //rental_amount ve deposit gerekebilir

                System.out.println("Kiralama bedeli: " + rental_amount);
                System.out.println("Depozito: " + deposit); //kaldırmayı unutma

                String yesNo;
                while (true) {
                    System.out.print("Sepetinize araç eklemeye devam etmek ister misiniz? (e/h): ");
                    yesNo = scanner.nextLine().trim().toLowerCase();

                    if (yesNo.equals("e") || yesNo.equals("h")) {
                        break;
                    } else {
                        System.out.println("Geçersiz seçim! Lütfen sadece 'e' veya 'h' giriniz.");
                    }
                }

                if (yesNo.equals("h")) {
                    isContinue = false;
                }

            }
        }
    }

    private static void registerCustomer() throws VehicleRentalException {

        while (true) {
            System.out.println("Müşterilik tipi seçiniz: ");
            System.out.println("1 - Bireysel");
            System.out.println("2 - Kurumsal");
            System.out.println("0 - Geri");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerIndividualCustomer();
                    break;
                case "2":
                    registerCorporateCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void registerIndividualCustomer() throws VehicleRentalException {

        System.out.print("İsim: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        int age = 0;
        while (true) { //in case of a non-integer input
            System.out.print("Yaş: ");
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz: ");
                scanner.nextLine(); //to clear the wrong input
            }
        }

        CustomerService customerService = new CustomerService();
        customerService.saveIndividual(name, email, password, age);
    }

    private static void registerCorporateCustomer() throws VehicleRentalException {

        System.out.print("İsim: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.saveCorporate(name, email, password);
    }

}
