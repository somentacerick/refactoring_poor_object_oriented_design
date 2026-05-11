package badstore;

import java.util.List;

public class SalesReportService {

    public void printSalesReport(List<Order> orders) {
        double totalRevenue = 0;

        System.out.println("Sales Report");

        for (Order order : orders) {
            System.out.println(order);
            totalRevenue += order.getTotalPrice();
        }

        System.out.println("Total Revenue: " + totalRevenue);
    }
}
