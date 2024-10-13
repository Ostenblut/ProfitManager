import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(10);
        userManager.add_user("Admin", "12345", "admin");   // Permissions: 1,2,3,4,5
        userManager.add_user("Kemal", "12345", "user1");   // Permissions: 1
        userManager.add_user("Burcu", "12345", "user2");   // Permissions: 2,3
        userManager.add_user("Dogukan", "12345", "user3"); // Permissions: 2,5
        userManager.add_user("Guest", "12345", "user4");   // Permissions: none

        Scanner scan = new Scanner(System.in);
        int attempt_counter = 0;
        int max_attempt = 3;

        while (attempt_counter < max_attempt) {
            System.out.print("Username: ");
            String username = scan.nextLine();
            System.out.print("Password: ");
            String password = scan.nextLine();

            User user = userManager.authenticate(username, password);
            if (user != null) {
                System.out.println("Login successful!");
                RunApplication(scan, user);
                return;
            } else {
                attempt_counter++;
                int remaining_attempt = max_attempt - attempt_counter;
                System.out.println("\nInvalid username or password. Remaining attempts: " + remaining_attempt);
            }
        }
        System.out.println("Too many failed attempts. Exiting the program...");
    }

    private static void RunApplication(Scanner scan, User user) {
        boolean inventory_control = false;
        boolean sale_control = false;

        while (true) {
            System.out.println("\n\n\t\t'Profit Manager'");
            int option_counter = 1;

            if (user.get_role().equals("admin") || user.get_role().equals("user1")) {
                System.out.println(option_counter + ". Employee Salary Manager");
                option_counter++;
            }
            if (user.get_role().equals("admin") || user.get_role().equals("user2") || user.get_role().equals("user3")) {
                System.out.println(option_counter + ". Inventory Logistics Manager");
                option_counter++;
            }
            if (user.get_role().equals("admin") || user.get_role().equals("user2")) {
                System.out.println(option_counter + ". Sales And Purchase Manager");
                option_counter++;
            }
            if (user.get_role().equals("admin")) {
                System.out.println(option_counter + ". Yearly Tax and Earning Manager");
                option_counter++;
            }
            if (user.get_role().equals("admin") || user.get_role().equals("user3")) {
                System.out.println(option_counter + ". Customer Order Manager");
                option_counter++;
            }
            System.out.println(option_counter + ". Exit");

            int choice = Methods.int_zero_control(scan, "Please enter your choice: ");
            int current_option_counter = 1;

            if (user.get_role().equals("admin") || user.get_role().equals("user1")) {
                if (choice == current_option_counter) {
                    EmployeeManager.EmployeeSalaryManager();
                    continue;
                }
                current_option_counter++;
            }
            if (user.get_role().equals("admin") || user.get_role().equals("user2") || user.get_role().equals("user3")) {
                if (choice == current_option_counter) {
                    InventoryManager.InventoryLogisticsManager();
                    inventory_control = true;
                    continue;
                }
                current_option_counter++;
            }
            if (user.get_role().equals("admin") || user.get_role().equals("user2")) {
                if (choice == current_option_counter) {
                    if (inventory_control) {
                        SalesManager.SalesAndPurchaseManager();
                        sale_control = true;
                    } else {
                        System.out.println("You need to complete Inventory Logistics Manager (option 2) first.");
                    }
                    continue;
                }
                current_option_counter++;
            }
            if (user.get_role().equals("admin")) {
                if (choice == current_option_counter) {
                    if (sale_control) {
                        TaxManager.YearlyTaxAndEarningManager();
                    } else {
                        System.out.println("You need to complete Sales And Purchase Manager (option 3) first.");
                    }
                    continue;
                }
                current_option_counter++;
            }
            if (user.get_role().equals("admin") || user.get_role().equals("user3")) {
                if (choice == current_option_counter) {
                    if (inventory_control) {
                        OrderManager.CustomerOrderManager();
                    } else {
                        System.out.println("You need to complete Inventory Logistics Manager (option 2) first.");
                    }
                    continue;
                }
            }
            if (choice == option_counter) {
                System.out.println("Exiting the program...");
                return;
            }
            System.out.println("Invalid choice. Please enter a valid command:");
        }
    }
}
