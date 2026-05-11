package badstore;

public class NotificationService {

    public void sendOrderConfirmation(Customer customer, Product product) {
        System.out.println("Sending email to " + customer.getEmail()
                + ":Thanks for your order of " + product.getName() + "!");
    }
}
