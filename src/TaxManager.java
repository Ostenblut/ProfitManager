public class TaxManager {
    public static void YearlyTaxAndEarningManager() {
        double[] total_monthly_profit = new double[12];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < InventoryManager.item_number; j++) {
                total_monthly_profit[i] += SalesManager.monthly_profit[j][i];
            }
        }

        System.out.println("\nYour Total profit and tax amount you need to pay:");

        double grand_total_profit = 0;

        for (int j = 0; j < 4; j++) {
            double cumulative_profit = 0;
            System.out.println("\n" + (j + 1) + ". Quarter:");
            System.out.printf("%-18s%-18s\n", "Month", "Profit");

            for (int k = 0; k < 3; k++) {
                cumulative_profit += total_monthly_profit[(j * 3) + k];
                System.out.printf("%-18s%-18s\n", String.format("%d.", ((j * 3) + k + 1)), String.format("%.2f$", total_monthly_profit[(j * 3) + k]));
            }
            System.out.printf("\n%d. Quarter's total profit: %-18s\n", (j + 1), String.format("%.2f$", cumulative_profit));
            grand_total_profit += cumulative_profit;
        }
        System.out.printf("\nTotal Profit: %-18s\n", String.format("%.2f$", grand_total_profit));
        if (grand_total_profit > 0){
        System.out.printf("Income tax you need to pay this year: %-18s\n", String.format("%.2f$", Methods.yearly_tax_amount(grand_total_profit / 12)));
        }else {
            System.out.printf("Income tax you need to pay this year: %-18s\n","0$");
        }
    }
}
