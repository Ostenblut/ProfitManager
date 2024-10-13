import java.util.Scanner;

public class OrderManager {
    public static void CustomerOrderManager() {
        Scanner scan = new Scanner(System.in);
        int order_amount = Methods.int_zero_control(scan, "Please enter the order amount: ");

        String[] delivery_date = new String[order_amount];
        String[] order_date = new String[order_amount];
        String[] customer_name = new String[order_amount];
        int[] order_quantity = new int[order_amount];
        int[] selected_item = new int[order_amount];
        double[] new_price = new double[order_amount];
        double total_price;

        char pricing_control;

        for (int i = 0; i < order_amount; i++) {

            scan.nextLine();
            customer_name[i] = Methods.name_control(scan, "Please enter the " + (i + 1) + ". Customers name: ");

            while (true) {
                System.out.println("Select the sold product from the list:");

                for (int j = 0; j < InventoryManager.item_number; j++) {
                    System.out.println((j + 1) + ". " + InventoryManager.item_name_list[j]);
                }

                if (scan.hasNextInt()) {
                    selected_item[i] = scan.nextInt();
                    if (selected_item[i] > 0 && selected_item[i] <= InventoryManager.item_number) {
                        break;
                    } else {
                        System.out.println("Please enter a number from the list.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number from the list.");
                    scan.next();
                }
            }

            while (true) {
                System.out.println("Is there any special pricing for this order (Yes/No): ");
                pricing_control = scan.next().charAt(0);
                if (pricing_control == 'Y' || pricing_control == 'y') {
                    new_price[i] = Methods.double_control(scan, "Enter the price:");
                    break;
                } else if (pricing_control == 'N' || pricing_control == 'n') {
                    new_price[i] = InventoryManager.item_price[selected_item[i] - 1];
                    break;
                } else {
                    System.out.println("Invalid command!");
                }
            }

            order_quantity[i] = Methods.int_zero_control(scan, "Enter the orders quantity: ");

            System.out.println("Enter the orders date: ");
            order_date[i] = scan.next();

            System.out.println("Enter the delivery date of the order: ");
            delivery_date[i] = scan.next();
            System.out.print("\n");
        }

        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Customer Name", "Order Date", "Delivery Date", "Item Name", "Quantity", "Unit Price", "Total Price");
        for (int i = 0; i < order_amount; i++) {
            total_price = new_price[i] * order_quantity[i];
            System.out.printf("%-20s%-20s%-20s%-20s%-20d%-20s%-20s\n", customer_name[i], order_date[i], delivery_date[i], InventoryManager.item_name_list[selected_item[i] - 1], order_quantity[i],
                    String.format("%.2f$", new_price[i]),
                    String.format("%.2f$", total_price));
        }
    }
}
