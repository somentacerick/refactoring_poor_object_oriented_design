package badstore;

import java.util.ArrayList;
import java.util.List;

public class StoreService {

    public static List<Product> products = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    public void initSampleData() {
        Product p1 = new Product();
        p1.id = "P1";
        p1.name = "Laptop";
        p1.price = -999.99;
        p1.quantityInStock = 10;
        p1.category = "electronics";

        Product p2 = new Product();
        p2.id = "P2";
        p2.name = "Book - Design Patterns";
        p2.price = 0;
        p2.quantityInStock = 2;
        p2.category = "books";

        Address a1 = new Address();
        a1.street = "123 Main St";
        a1.city = "Chicago";
        a1.state = "IL";
        a1.zip = "60601";
        a1.country = "USA";

        Address a2 = new Address();
        a2.street = "456 Side St";
        a2.city = "Toronto";
        a2.state = "ON";
        a2.zip = "M5H 2N2";
        a2.country = "Canada";

        Customer c1 = new Customer();
        c1.name = "Alice";
        c1.email = "alice@example.com";
        c1.loyaltyPoints = -100; // negative points
        c1.type = "VIP";
        c1.shippingAddress = a1;

        Customer c2 = new Customer();
        c2.name = "Bob";
        c2.email = "bob@example.com";
        c2.loyaltyPoints = 0;
        c2.type = "standard";
        c2.shippingAddress = a2;

        products.add(p1);
        products.add(p2);
        customers.add(c1);
        customers.add(c2);
    }

    public void printProducts() {
        System.out.println("Products:");
        for (Product p : products) {
            System.out.println(p.id + " - " + p.name + " ($" + p.price + "), qty=" + p.quantityInStock);
        }
    }

    public void printCustomers() {
        System.out.println("Customers:");
        for (Customer c : customers) {
            System.out.println(c.name + " (" + c.email + "), pts=" + c.loyaltyPoints
                    + ", type=" + c.type + ", addr=" + c.shippingAddress);
        }
    }

    public void placeOrder(String email, String productId, int quantity) {
        // find customer
        Customer foundCustomer = null;
        for (Customer c : customers) {
            if (c.email.equals(email)) {
                foundCustomer = c;
                break;
            }
        }

        if (foundCustomer == null) {
            System.out.println("No such customer.");
            return;
        }

        // find product
        Product foundProduct = null;
        for (Product p : products) {
            if (p.id.equals(productId)) {
                foundProduct = p;
                break;
            }
        }

        if (foundProduct == null) {
            System.out.println("No such product.");
            return;
        }

        foundProduct.quantityInStock = foundProduct.quantityInStock - quantity;

        Order order = new Order();
        order.name = foundCustomer.name;
        order.email = foundCustomer.email;
        order.loyaltyPoints = foundCustomer.loyaltyPoints;
        order.type = foundCustomer.type;

        order.product = foundProduct;
        order.quantity = quantity;

        // Product's discount logic
        double baseTotal = foundProduct.price * quantity;
        double discountedTotal = foundProduct.getDiscountedPrice(foundCustomer.type) * quantity;

        order.totalPrice = discountedTotal;

        // shipping logic
        order.shippingMethod = "standard";
        order.shippingCost = ShippingCalculator.calculateShippingCost(
                order.shippingMethod,
                foundCustomer.shippingAddress,
                order.totalPrice
        );
        System.out.println("Shipping cost: " + order.shippingCost);

        order.status = "NEW";

        orders.add(order);

        System.out.println("Order placed for " + foundCustomer.name + " : "
                + foundProduct.name + " x " + quantity);

        LoyaltyProgram.instance.applyPurchasePoints(foundCustomer, order.totalPrice);

        log("Order placed: " + order.toString());
        sendEmail(foundCustomer.email, "Thanks for your order of " + foundProduct.name + "!");
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
