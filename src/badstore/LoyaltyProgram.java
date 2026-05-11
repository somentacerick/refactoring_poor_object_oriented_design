package badstore;

public class LoyaltyProgram {

    //private and final
    //values for loyalty system
    private final int vipThreshold;
    private final double vipMultiplier;
    private final double standardMultiplier;

    public LoyaltyProgram() {

    //default values
        this.vipThreshold =1000;
        this.vipMultiplier =2.0;
        this.standardMultiplier =1.0;
    }

    public void applyPurchasePoints(Customer customer, double purchaseAmount) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (purchaseAmount < 0) {
            throw new IllegalArgumentException("Purchase amount cannot be negative");
        }

        int pointsToAdd;

        if (customer.getType() == CustomerType.VIP) {
            pointsToAdd = (int) (purchaseAmount * vipMultiplier);
        } else {
            pointsToAdd = (int) (purchaseAmount * standardMultiplier);
        }

        customer.addLoyaltyPoints(pointsToAdd);

        if (customer.getType() != CustomerType.VIP
                && customer.getLoyaltyPoints() >= vipThreshold) {

                customer.setType(CustomerType.VIP);
        }
    }

}
