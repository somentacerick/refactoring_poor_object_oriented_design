package badstore;

import java.util.ArrayList;
import java.util.List;

public class StoreService {

    public static List<Product> products = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    public void initSampleData() {
        Product p1 = new Product("P1", "Laptop", 999.99, 10, "electronics");

        Product p2 = new Product("P2", "Book - Design Patterns", 0, 2, "books");

        Address a1 = new Address(
        //cleaned up by removing a1.__ =
        "123 Main St",
        "Chicago",
        "IL",
        "60601",
        "USA"
        );

        Address a2 = new Address(
        //same as a1
        "456 Side St",
        "Toronto",
        "ON",
        "M5H 2N2",
        "Canada"
        );

        Customer c1 = new Customer(
        //cleaned up, removed c1.__ =
        //added customertype
        "Alice",
        "alice@example.com",
        100, // negative points correctly rejected
        CustomerType.VIP,
        a1
        );

        Customer c2 = new Customer(
        //same as c1
        "Bob",
        "bob@example.com",
        0,
        CustomerType.STANDARD,
        a2
        );

        products.add(p1);
        products.add(p2);
        customers.add(c1);
        customers.add(c2);
    }

    public void printProducts() {
        System.out.println("Products:");
        for (Product p : products) {
            System.out.println(p.getId() + " - " + p.getName() + " ($" + p.getPrice() + "), qty=" + p.getQuantityInStock());
        }
    }

    public void printCustomers() {
        System.out.println("Customers:");
        for (Customer c : customers) {
            System.out.println(
                    c.getName() +  " (" + c.getEmail() + "), pts="
                        + c.getLoyaltyPoints()
                        + ", type=" + c.getType()
                        + ", addr =" + c.getShippingAddress()
            );
        }
    }

    public void placeOrder(String email, String productId, int quantity) {
        // find customer
        Customer foundCustomer = findCustomerByEmail(email);

        if (foundCustomer == null) {
            System.out.println("No such customer.");
            return;
        }

        // find product
        Product foundProduct = findProductById(productId);
        if (foundProduct == null) {
            System.out.println("No such product.");
            return;
        }

        try {
            foundProduct.reduceStock(quantity);

            double discountedTotal = calculateDiscountedTotal(foundProduct, foundCustomer, quantity);

            Shipping shipping = Shipping.STANDARD;

            double shippingCost = ShippingCalculator.calculateShippingCost(
                    shipping,
                    foundCustomer.getShippingAddress(),
                    discountedTotal
            );

            Order order = new Order(
                    foundCustomer,
                    foundProduct,
                    quantity,
                    discountedTotal,
                    shipping,
                    shippingCost
            );

            orders.add(order);

            System.out.println("Shipping cost: " + order.getShippingCost());

            System.out.println("Order placed for " + foundCustomer.getName() + " : "
                    + foundProduct.getName() + " x " + quantity);

            LoyaltyProgram.instance.applyPurchasePoints(foundCustomer, order.getTotalPrice());

            log("Order placed: " + order);
            sendEmail(foundCustomer.getEmail(), "Thanks for your order of " + foundProduct.getName() + "!");

        } catch (IllegalArgumentException e) {
            System.out.println("Could not place order: " + e.getMessage());
        }
    }

    private Customer findCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }

        return null;
    }

    private Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }

    private double calculateDiscountedTotal(Product product, Customer customer, int quantity) {
        double price = product.getPrice();

        if (customer.getType() == CustomerType.VIP) {
            price = price * .8;
        } else if (customer.getType() == CustomerType.STANDARD) {
            price = price * .95;
        } else if (customer.getType() == CustomerType.EMPLOYEE) {
            price = price * .5;
        }
        return price * quantity;
    }

    public void printSalesReport() {
        double totalRevenue = 0;
        System.out.println("Sales Report");
        for (Order o : orders) {
            System.out.println(o.toString());
            totalRevenue += o.totalPrice;
        }
        System.out.println("Total revenue: " + totalRevenue);
    }

    // Fake logging - prints to console
    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }

    // Fake email - prints to console
    public void sendEmail(String email, String body) {
        System.out.println("Sending email to " + email + ": " + body);
    }
}
