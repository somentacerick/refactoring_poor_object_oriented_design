package badstore;

import java.util.Objects;

public class Customer {

    //private fields to protect customer data
    private final String name;
    private final String email;
    private int loyaltyPoints;
    private CustomerType type; // STANDARD, VIP, or EMPLOYEE
    private final Address shippingAddress;

    public Customer(String name, String email, int loyaltyPoints, CustomerType type, Address shippingAddress) {
        requireNonBlank(name, "Name");
        requireNonBlank(email, "Email");

        if (loyaltyPoints < 0) {
            throw new IllegalArgumentException("Loyalty points cannot be negative.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Customer type cannot be null.");
        }

        if (shippingAddress == null) {
            throw new IllegalArgumentException("Shipping address cannot be null.");
        }

        this.name = name;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
        this.type = type;
        this.shippingAddress = shippingAddress;
    }

    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank.");
        }
    }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getLoyaltyPoints() {
            return loyaltyPoints;
        }

        public CustomerType getType() {
            return type;
        }

        public Address getShippingAddress() {
            return shippingAddress;
        }

        //method for adding loy points
        // prevents outside classes from invalidating values
        public void addLoyaltyPoints(int points) {
            if (points < 0) {
                throw new IllegalArgumentException("Cannot add negative loyalty points.");
            }
            loyaltyPoints += points;
        }

        //method for changing customer type
        //used when upgrading a customer to VIP
        public void setType(CustomerType type) {
            if (type == null) {
                throw new IllegalArgumentException("Customer type cannot be null.");
            }
            this.type = type;
        }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return email.equalsIgnoreCase(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }

    @Override
    public String toString() {
        return name + " <" + email + "> (" + type + "), pts=" + loyaltyPoints
                + ", addr=" + shippingAddress;
    }
}
