package vehicle_rental;

import vehicle_rental.exception.VehicleRentalException;
import vehicle_rental.model.User;
import vehicle_rental.model.enums.Role;
import vehicle_rental.service.CustomerService;
import vehicle_rental.service.UserService;
import vehicle_rental.util.PasswordUtil;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

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
        while (true){
            System.out.println("=== MÜŞTERİ GİRİŞ PANELİ ===");
            System.out.println("1 - Müşteri Kayıt Ol");
            System.out.println("2 - Müşteri Giriş Yap");
            System.out.println("0 - Geri Dön");
            System.out.print("Seçim yapınız: ");

            String choice = scanner.nextLine();

            switch (choice){
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
        while (true){
            System.out.println("=== KULLANICI GİRİŞ PANELİ ===");
            System.out.println("1 - Kullanıcı Kayıt Ol");
            System.out.println("2 - Kullanıcı Giriş Yap");
            System.out.println("0 - Geri Dön");
            System.out.print("Seçim yapınız: ");

            String choice = scanner.nextLine();

            switch (choice){
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

        userService.login(userName, password);

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
        customerService.login(email, password);
    }

    private static void registerCustomer() throws VehicleRentalException {

        System.out.print("İsim: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name, email, password);
    }
}
