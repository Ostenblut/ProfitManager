import java.util.Scanner;

public class EmployeeManager {
    public static void EmployeeSalaryManager() {
        Scanner scan = new Scanner(System.in);

        int employee_number = Methods.int_zero_control(scan, "How many employees do you have?: ");
        String[] employee_names = new String[employee_number];
        double[] employee_gross_salary = new double[employee_number];

        for (int i = 0; i < employee_number; i++) {
            scan.nextLine();
            employee_names[i] = Methods.name_control(scan, "Enter the name of the " + (i + 1) + ". employee: ");
            employee_gross_salary[i] = Methods.double_zero_control(scan, "Enter the employee's monthly gross salary: ");
        }

        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n",
                "Employee Name", "Yearly Gross Salary", "Yearly Net Salary", "Monthly Gross Salary", "Monthly Net Salary", "Yearly Tax Amount", "Tax Percentage");

        for (int i = 0; i < employee_number; i++) {
            System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n",
                    employee_names[i],
                    String.format("%.2f$", Methods.yearly_salary_convertor(employee_gross_salary[i])),
                    String.format("%.2f$", Methods.net_salary_calculator(employee_gross_salary[i]) * 12),
                    String.format("%.2f$", employee_gross_salary[i]),
                    String.format("%.2f$", Methods.net_salary_calculator(employee_gross_salary[i])),
                    String.format("%.2f$", Methods.yearly_tax_amount(employee_gross_salary[i])),
                    String.format("%.2f%%", Methods.salary_tax_percentage(employee_gross_salary[i])));
        }
    }
}