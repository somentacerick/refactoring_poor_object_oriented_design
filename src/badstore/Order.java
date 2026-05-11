package badstore;

public class Order {

    //Composition: order HAS a customer & product
    //fixes the original inheritance
    private final Customer customer;
    private final Product product;
    private final int quantity;
    private final double totalPrice;
    private OrderStatus status;
    private final Shipping shipping;
    private final double shippingCost;

    public Order(Customer customer, Product product, int quantity, double totalPrice, Shipping shipping, double shippingCost) {
        //helper method for null checks, avoids repitition

        this.customer = requireNonNull(customer, "Customer");
        this.product = requireNonNull(product, "Product");
        this.shipping = requireNonNull(shipping, "Shipping");

        //this enforces business rules

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (totalPrice < 0) {
            throw new IllegalArgumentException("Total price cannot be negative.");
        }

        if (shippingCost < 0) {
            throw new IllegalArgumentException("Shipping cost cannot be negative.");
        }

        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.shippingCost = shippingCost;

        this.status = OrderStatus.NEW;
    }
    //helper for null validation to reduce duplicaiton
    private<T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
        return value;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    //status is the only one that changes after being created
    //this ensures valid transactions
    public void setStatus(OrderStatus status) {
        this.status = requireNonNull(status, "Order status");
    }

    @Override
    public String toString() {
        return "Order for " + customer.getName() + " (" + customer.getEmail() + "): "
                + product.getName() + " x " + quantity
                + " = " + totalPrice
                + " [status=" + status + "]"
                + " [shipping=" + shipping + " $" + shippingCost + "]";
    }
}
