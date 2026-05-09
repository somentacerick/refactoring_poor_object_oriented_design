package badstore;

public class ShippingCalculator {

    public static double calculateShippingCost(
            String shippingMethod, Address address, double orderTotal) {

        if (shippingMethod == null) {
            shippingMethod = "standard";
        }

        double baseCost;

        if ("express".equalsIgnoreCase(shippingMethod)) {
            baseCost = 15.0;
        } else if ("overnight".equalsIgnoreCase(shippingMethod)) {
            baseCost = 25.0;
        } else {
            baseCost = 5.0; // "standard"
        }

        // free shipping for large orders
        if (orderTotal > 200.0) {
            baseCost = 0.0;
        }

        // "international" check
        if (address != null && address.country != null &&
                !"USA".equalsIgnoreCase(address.country)) {
            baseCost = baseCost + 10.0;
        }

        return baseCost;
    }
}
