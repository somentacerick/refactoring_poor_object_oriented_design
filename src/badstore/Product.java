package badstore;

public class Product {

    public String id;
    public String name;
    public double price;
    public int quantityInStock;
    public String category; // "electronics", "books", etc.

    public double getDiscountedPrice(String customerType) {
        double finalPrice = price;

        if ("VIP".equalsIgnoreCase(customerType)) {
            finalPrice = price * 0.8;   // 20% off
        } else if ("standard".equalsIgnoreCase(customerType)) {
            finalPrice = price * 0.95;  // 5% off
        } else if ("employee".equalsIgnoreCase(customerType)) {
            finalPrice = price * 0.5;   // 50% off
        } else {
            // no discount for unknown types
        }

        return finalPrice;
    }

    @Override
    public String toString() {
        return id + ":" + name + ":" + price + ":" + quantityInStock + ":" + category;
    }
}

