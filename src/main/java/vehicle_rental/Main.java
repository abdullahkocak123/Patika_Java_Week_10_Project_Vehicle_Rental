package vehicle_rental;

import vehicle_rental.service.CustomerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.println("Araç Kiralama Servisi'ne Hoş Geldiniz");
            System.out.println("1 - Müşteri Kaydı");
            System.out.println("2 - Giriş yap");
            System.out.println("0 - Çıkış");

            System.out.print("Seçim yapınız: ");
            String choice = scanner.nextLine();

            switch (choice){

                case "1":
                    saveCustomer(scanner);
                    break;
                case "2":
                    loginCustomer(scanner);
                    break;
                case "0":
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }

        }

    }

    private static void loginCustomer(Scanner scanner) {

        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email, password);
    }

    private static void saveCustomer(Scanner scanner) {

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
