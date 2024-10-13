import java.util.Scanner;

public class Methods {
    static double yearly_salary_convertor(double salary) {
        return salary * 12;
    }

    static double yearly_tax_amount(double gross_salary) {
        double yearly_employee_salary = yearly_salary_convertor(gross_salary);
        if (yearly_employee_salary <= 110000) {
            return yearly_employee_salary * 0.15;
        } else if (yearly_employee_salary <= 230000) {
            return (yearly_employee_salary - 110000) * 0.2 + 16500;
        } else if (yearly_employee_salary <= 870000) {
            return (yearly_employee_salary - 230000) * 0.27 + 40500;
        } else if (yearly_employee_salary <= 3000000) {
            return (yearly_employee_salary - 870000) * 0.35 + 213300;
        } else {
            return (yearly_employee_salary - 3000000) * 0.4 + 958800;
        }
    }

    static double net_salary_calculator(double gross_salary) {
        return (yearly_salary_convertor(gross_salary) - yearly_tax_amount(gross_salary)) / 12;
    }

    static double salary_tax_percentage(double gross_salary) {
        return (yearly_tax_amount(gross_salary) / yearly_salary_convertor(gross_salary)) * 100;
    }

    static double percentage_convertor(double num) {
        return num / 100;
    }

    static double profit_calculator(int produce_amount, int sale_amount, double cost, double price) {
        double total_sales = sale_amount * price;
        double total_cost = produce_amount * cost;
        return total_sales - total_cost;
    }

    static int int_control(Scanner scan, String prompt) {
        int value;
        while (true) {
            System.out.println(prompt);
            if (scan.hasNextInt()) {
                value = scan.nextInt();
                if (value >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.\n");
                }
            } else {
                System.out.println("Invalid input. Please enter a integer.\n");
                scan.next();
            }
        }
        return value;
    }

    static double double_control(Scanner scan, String prompt) {
        double value;
        while (true) {
            System.out.println(prompt);
            if (scan.hasNextDouble()) {
                value = scan.nextDouble();
                if (value >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a positive number.\n");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.\n");
                scan.next();
            }
        }
        return value;
    }

    static String name_control(Scanner scan, String prompt) {
        String value;
        while (true) {
            System.out.println(prompt);
            value = scan.nextLine();
            boolean control = true;
            char[] characters = value.toCharArray();
            for (char character : characters) {
                if (!(character >= 'a' && character <= 'z') && !(character >= 'A' && character <= 'Z') && character != ' ') {
                    control = false;
                    break;
                }
            }
            if (control) {
                break;
            } else {
                System.out.println("Invalid input. Please only use letters.\n");
            }
        }
        return value;
    }

    public static int sale_control(Scanner scan, int current_stock, int produce_amount, String item_name, int month) {
        int sale_amount;
        while (true) {
            sale_amount = Methods.int_control(scan, "How many units of the " + item_name + " did you sell in " + month + ". month ?: ");
            if (sale_amount <= current_stock + produce_amount) {
                break;
            } else {
                System.out.println("The sold amount exceeds the current stock plus produced amount for " + item_name + " in month " + month + ". Please enter a valid amount.");
            }
        }
        return sale_amount;
    }

    public static int int_zero_control(Scanner scan, String prompt) {
        int value;
        while (true) {
            value = int_control(scan, prompt);
            if (value > 0) {
                break;
            } else {
                System.out.println("Value must be greater than 0. Please enter again.");
            }
        }
        return value;
    }

    public static double double_zero_control(Scanner scan, String prompt) {
        double value;
        while (true) {
            value = double_control(scan, prompt);
            if (value > 0) {
                break;
            } else {
                System.out.println("Value must be greater than 0. Please enter again.");
            }
        }
        return value;
    }
}