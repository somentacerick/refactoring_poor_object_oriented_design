package badstore;

import java.util.Objects;

public class Customer {

    public String name;
    public String email;
    public int loyaltyPoints;
    public String type; // STANDARD, VIP, or EMPLOYEE
    public Address shippingAddress;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name + " <" + email + "> (" + type + "), pts=" + loyaltyPoints
                + ", addr=" + shippingAddress;
    }
}
