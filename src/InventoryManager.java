import java.util.Scanner;

public class InventoryManager {
    static int item_number;
    static double[] item_cost;
    static double[] profit_amount;
    static String[] item_name_list;
    static double[] item_price;

    public static void InventoryLogisticsManager() {
        Scanner scan = new Scanner(System.in);

        item_number = Methods.int_zero_control(scan, "How many items do you want to add ?: ");
        item_cost = new double[item_number];
        profit_amount = new double[item_number];
        item_name_list = new String[item_number];
        item_price = new double[item_number];

        for (int i = 0; i < item_number; i++) {
            scan.nextLine();
            item_name_list[i] = Methods.name_control(scan, "Please enter the " + (i + 1) + ". item's name: ");
            item_cost[i] = Methods.double_zero_control(scan, "Please enter the " + (i + 1) + ". item's cost: ");

            while (true) {
                System.out.print("How would you like to add profit to the item:\n1.Percentage\n2.Value\n");
                String profit_control = scan.next();
                if (profit_control.charAt(0) == '1' || profit_control.charAt(0) == 'P' || profit_control.charAt(0) == 'p') {
                    double percentage = Methods.double_zero_control(scan, "Please enter the percentage: ");
                    profit_amount[i] = item_cost[i] * Methods.percentage_convertor(percentage);
                    item_price[i] = item_cost[i] + profit_amount[i];
                    break;
                } else if (profit_control.charAt(0) == '2' || profit_control.charAt(0) == 'V' || profit_control.charAt(0) == 'v') {
                    profit_amount[i] = Methods.double_zero_control(scan, "Please enter the profit value: ");
                    item_price[i] = item_cost[i] + profit_amount[i];
                    break;
                } else {
                    System.out.println("Invalid Command! ");
                }
            }

            while (true) {
                System.out.println("Does this item include OTV tax (Yes/No): ");
                String otv_control = scan.next();
                if (otv_control.charAt(0) == 'Y' || otv_control.charAt(0) == 'y') {
                    double otv_percentage = Methods.double_control(scan, "Please enter the OTV percentage: ");
                    item_price[i] += (item_price[i] * Methods.percentage_convertor(otv_percentage));
                    break;
                } else if (otv_control.charAt(0) == 'n' || otv_control.charAt(0) == 'N') {
                    break;
                } else {
                    System.out.println("Invalid Command!");
                }
            }

            double kdv_percentage = Methods.double_control(scan, "Please enter the KDV percentage: ");
            item_price[i] += (item_price[i] * Methods.percentage_convertor(kdv_percentage));
        }

        System.out.printf("\n\n%-25s%-25s%-25s%-25s%-25s\n", "Item Name", "Item Cost", "Profit Amount", "Total Tax Amount", "Item Price");
        for (int i = 0; i < item_number; i++) {
            System.out.printf("%-25s%-25s%-25s%-25s%-25s\n",
                    item_name_list[i],
                    String.format("%.2f$", item_cost[i]),
                    String.format("%.2f$", profit_amount[i]),
                    String.format("%.2f$", (item_price[i] - item_cost[i] - profit_amount[i])),
                    String.format("%.2f$", item_price[i])
            );
        }
    }
}
