package badstore;

import java.util.Scanner;

public class StoreAppMain {

    // Very simple command-line "UI"
    public static void main(String[] args) {
        StoreService service = new StoreService();
        Scanner scanner = new Scanner(System.in);

        // Preload a couple of products and customers
        service.initSampleData();

        System.out.println("Welcome to the Bad Store Management System");
        System.out.println("Type commands: LIST_PRODUCTS, LIST_CUSTOMERS, ORDER, REPORT, EXIT");

        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine();

            if (cmd.equalsIgnoreCase("LIST_PRODUCTS")) {
                service.printProducts();
            } else if (cmd.equalsIgnoreCase("LIST_CUSTOMERS")) {
                service.printCustomers();
            } else if (cmd.equalsIgnoreCase("ORDER")) {
                System.out.print("Customer email: ");
                String email = scanner.nextLine();
                System.out.print("Product id: ");
                String productId = scanner.nextLine();
                System.out.print("Quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());
                service.placeOrder(email, productId, qty);
            } else if (cmd.equalsIgnoreCase("REPORT")) {
                service.printSalesReport();
            } else if (cmd.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting.");
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }
    }
}
