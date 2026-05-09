package badstore;

public class LoyaltyProgram {

    // Global singleton-ish access (not enforced, just a public static)
    public static LoyaltyProgram instance = new LoyaltyProgram();

    // Thresholds and multipliers
    public int vipThreshold = 1000;    // points required for VIP
    public double vipMultiplier = 2.0; // VIP earns double points
    public double standardMultiplier = 1.0;

    public void applyPurchasePoints(Customer customer, double purchaseAmount) {
        if (customer == null) return;

        int pointsToAdd;
        if ("VIP".equalsIgnoreCase(customer.type)) {
            pointsToAdd = (int) (purchaseAmount * vipMultiplier);
        } else {
            pointsToAdd = (int) (purchaseAmount * standardMultiplier);
        }

        customer.loyaltyPoints = customer.loyaltyPoints + pointsToAdd;

        if (!"VIP".equalsIgnoreCase(customer.type) &&
                customer.loyaltyPoints >= vipThreshold) {
            customer.type = "VIP"; // silently changes type
        }
    }

    public void reset() {
        vipThreshold = 0;
        vipMultiplier = 1.0;
        standardMultiplier = 1.0;
    }
}
