package badstore;

public class Order extends Customer {

    public Product product;
    public int quantity;
    public double totalPrice;
    public String status; // NEW, PAID, SHIPPED, DELIVERED, or CANCELLED

    // shipping-related fields
    public String shippingMethod; // STANDARD, EXPRESS, or OVERNIGHT
    public double shippingCost;

    @Override
    public String toString() {
        return "Order for " + name + " (" + email + "): "
                + product.name + " x " + quantity
                + " = " + totalPrice
                + " [status=" + status + "]"
                + " [shipping=" + shippingMethod + " $" + shippingCost + "]";
    }
}
