import java.util.Scanner;

public class SalesManager {
    static double[][] monthly_profit;
    static double total_profit;

    public static void SalesAndPurchaseManager() {
        Scanner scan = new Scanner(System.in);

        monthly_profit = new double[InventoryManager.item_number][12];
        int[][] excess_amount = new int[InventoryManager.item_number][12];

        int[][] produce_amount = new int[InventoryManager.item_number][12];
        int[][] sale_amount = new int[InventoryManager.item_number][12];
        int[] total_sale = new int[InventoryManager.item_number];
        int space_counter = InventoryManager.item_number - 1;

        for (int i = 0; i < InventoryManager.item_number; i++) {
            System.out.println((i + 1) + ". item:\n");
            excess_amount[i][0] = Methods.int_control(scan, "How many " + InventoryManager.item_name_list[i] + " were in storage from the previous financial year?: ");
            total_sale[i] = 0;

            for (int j = 0; j < 12; j++) {
                if (j > 0) {
                    excess_amount[i][j] = excess_amount[i][j-1];
                }
                produce_amount[i][j] = Methods.int_control(scan, "How many units of the " + InventoryManager.item_name_list[i] + " did you purchase/produce in " + (j + 1) + ". month?: ");
                sale_amount[i][j] = Methods.sale_control(scan, excess_amount[i][j], produce_amount[i][j], InventoryManager.item_name_list[i], j + 1);

                excess_amount[i][j] += produce_amount[i][j];
                excess_amount[i][j] -= sale_amount[i][j];
                total_sale[i] += sale_amount[i][j];
            }
            System.out.println();
        }

        for (int i = 0; i < InventoryManager.item_number; i++) {
            total_profit = 0;
            System.out.printf("%s:\n\n", InventoryManager.item_name_list[i]);
            System.out.printf("%-18s%-18s%-18s%-18s%-18s\n", "Month", "Purchase/Produce", "Sale", "Excess", "Profit");
            for (int j = 0; j < 12; j++) {
                monthly_profit[i][j] = Methods.profit_calculator(produce_amount[i][j], sale_amount[i][j], InventoryManager.item_cost[i], (InventoryManager.profit_amount[i] + InventoryManager.item_cost[i]));
                total_profit += monthly_profit[i][j];
                System.out.printf("%-18s%-18d%-18d%-18d%-18s\n",
                        String.format("%d.", (j + 1)),
                        produce_amount[i][j],
                        sale_amount[i][j],
                        excess_amount[i][j],
                        String.format("%.2f$", monthly_profit[i][j]));
            }

            System.out.printf("Total Sale: %d\nTotal Excess: %d\nTotal Profit: %-18s\n",
                    total_sale[i],
                    excess_amount[i][11],
                    String.format("%.2f$", total_profit));

            if (space_counter-- > 0) {
                System.out.print("\n\n");
            }
        }
    }
}
