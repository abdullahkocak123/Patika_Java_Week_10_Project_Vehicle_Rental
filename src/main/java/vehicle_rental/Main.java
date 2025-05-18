package vehicle_rental;

import vehicle_rental.exception.ExceptionMessagesConstants;
import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.Category;
import vehicle_rental.model.Customer;
import vehicle_rental.model.User;
import vehicle_rental.model.Vehicle;
import vehicle_rental.model.enums.Role;
import vehicle_rental.service.CategoryService;
import vehicle_rental.service.CustomerService;
import vehicle_rental.service.UserService;
import vehicle_rental.service.VehicleService;

import java.math.BigDecimal;
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
    }

    private static void vehicleSearch() {

        System.out.print("Araç ismi giriniz: ");
        String searchVehicleName = scanner.nextLine();

        List<Vehicle> vehicles = vehicleService.search(searchVehicleName);

        System.out.println("\n==== ARAÇ LİSTESİ (Arama Sonucu) ====");

        vehicles.forEach(vehicle ->
                System.out.printf("%s - %s - %s\n", vehicle.getName(), vehicle.getPrice(), vehicle.getCategory().getName())
        );

        System.out.println("======");

    }

    private static void rentList() {
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

            vehicles.forEach(vehicle ->
                    System.out.printf("%s - %s - %s\n", vehicle.getName(), vehicle.getPrice(), vehicle.getCategory().getName())
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
        System.out.print("Araç kiralama bedelini giriniz: ");
        String price = scanner.nextLine();
        System.out.print("Araç adedini giriniz: ");
        String stock = scanner.nextLine();
        System.out.print("Kategori id giriniz: ");
        String categoryId = scanner.nextLine();

        Category category = categoryService.getById(Long.parseLong(categoryId));

        Vehicle vehicle = new Vehicle(name, new BigDecimal(price), Integer.parseInt(stock), category);
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
        LOGINED_CUSTOMER =  customerService.login(email, password);

        while (true){
            System.out.println("1 - Araç Listele");
            System.out.println("2 - Araç Arama");
            System.out.println("3 - Araç Filtreleme(Kategori bazlı)");
            System.out.println("4 - Kiralama Oluştur");
            System.out.println("5 - Kiralamaları Listele");
            System.out.println("0 - Geri");
            System.out.print("Seçim yapınız: ");
            String choice = scanner.nextLine();

            switch (choice){
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
                    rentCreate();
                    break;
                case "5":
                    rentList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz seçim!");

            }
        }

    }

    private static void rentCreate() {
    }

    private static void registerCustomer() throws VehicleRentalException {

        while (true){
            System.out.println("Müşterilik tipi seçiniz: ");
            System.out.println("1 - Bireysel");
            System.out.println("2 - Kurumsal");
            System.out.println("0 - Geri");
            String choice = scanner.nextLine();

            switch (choice){
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
        while (true){ //in case of a non-integer input
            System.out.print("Yaş: ");
            try {
                age =scanner.nextInt();
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
