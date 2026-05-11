package badstore;

public class ShippingCalculator {

    public static double calculateShippingCost(
            Shipping shippingMethod, Address address, double orderTotal) {

        if (shippingMethod == null) {
            shippingMethod = Shipping.STANDARD;
        }

        double baseCost;

        if (shippingMethod == Shipping.EXPRESS) {
            baseCost = 15.0;
        } else if (shippingMethod == Shipping.OVERNIGHT) {
            baseCost = 25.0;
        } else {
            baseCost = 5.0; // "standard"
        }

        // free shipping for large orders
        if (orderTotal > 200.0) {
            baseCost = 0.0;
        }

        // "international" check
        if (address != null && address.isInternational()) {
            baseCost += 10.0;
        }

        return baseCost;
    }
}
