package badstore;

public class Product {

    //private to enforce encap
    private final String id;
    private final String name;
    private final double price;
    private int quantityInStock;
    private final String category; // "electronics", "books", etc.

    //removed getDiscountPrice, violates separation of concerns
    public Product(String id, String name, double price, int quantityInStock, String category) {

        requireNonBlank(id,"ID");
        requireNonBlank(name, "Name");
        requireNonBlank(category, "Category");

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (quantityInStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
    }

    //helper to avoid repeating checks
    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getCategory() {
        return category;
    }

    //updates stock
    public void reduceStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (amount > quantityInStock) {
            throw new IllegalArgumentException("Not enough stock available.");
        }

        quantityInStock -= amount;
    }

    @Override
    public String toString() {
        return id + ":" + name + ":" + "($" + price + "), qty=" + quantityInStock + ", category=" + category;
    }
}

