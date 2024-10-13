import java.util.Scanner;

// Abstract class representing a general Report
abstract class Report {
    private final int serialNumber; // Marked as final
    private final String state;      // Marked as final
    private final String financialYear; // Marked as final

    // Constructor
    public Report(int serialNumber, String state, String financialYear) {
        this.serialNumber = serialNumber;
        this.state = state;
        this.financialYear = financialYear;
    }

    // Getters
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getState() {
        return state;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    // Abstract method for displaying the report, to be implemented by subclasses
    public abstract void display();
}

// Subclass representing an Audit Report
class AuditReport extends Report {
    private final String minutes;   // Marked as final
    private final String fileSize;  // Marked as final

    // Constructor
    public AuditReport(int serialNumber, String state, String financialYear, String minutes, String fileSize) {
        super(serialNumber, state, financialYear);
        this.minutes = minutes;
        this.fileSize = fileSize;
    }

    // Overridden display method (Polymorphism)
    @Override
    public void display() {
        System.out.printf("%-5d %-30s %-15s %-40s %-10s%n", getSerialNumber(), getState(), getFinancialYear(), minutes, fileSize);
    }
}

// Subclass representing a Financial Sanction
class FinancialSanction extends Report {
    private final String title;                // Marked as final
    private final String schemeCategory;       // Marked as final
    private final String schemeSubCategory;    // Marked as final
    private final String expensesCategory;      // Marked as final
    private final String component;             // Marked as final

    // Constructor
    public FinancialSanction(int serialNumber, String title, String financialYear, String state, String schemeCategory, 
                             String schemeSubCategory, String expensesCategory, String component) {
        super(serialNumber, state, financialYear);
        this.title = title;
        this.schemeCategory = schemeCategory;
        this.schemeSubCategory = schemeSubCategory;
        this.expensesCategory = expensesCategory;
        this.component = component;
    }

    // Overridden display method (Polymorphism)
    @Override
    public void display() {
        System.out.printf("%-5d %-75s %-15s %-10s %-20s %-25s %-15s %-10s%n", getSerialNumber(), title, getFinancialYear(), 
                getState(), schemeCategory, schemeSubCategory, expensesCategory, component);
    }
}

public class Main {
    public static void main(String[] args) {
        // Use try-with-resources for the Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            // Audit Reports array
            AuditReport[] auditReports = {
                new AuditReport(1, "Andaman and Nicobar Islands", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "524.98 KB"),
                new AuditReport(2, "Andhra Pradesh", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.05 MB"),
                new AuditReport(3, "Arunachal Pradesh", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "3.25 MB"),
                new AuditReport(4, "Assam", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.48 MB"),
                new AuditReport(5, "Bihar", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "3.55 MB"),
                new AuditReport(6, "Chandigarh", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "577.93 KB"),
                new AuditReport(7, "Chhattisgarh", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.44 MB"),
                new AuditReport(8, "Dadra and Nagar Haveli", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "908.09 KB"),
                new AuditReport(9, "Daman and Diu", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.69 MB"),
                new AuditReport(10, "Delhi", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "618.47 KB"),
                new AuditReport(11, "Goa", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.48 MB"),
                new AuditReport(12, "Gujarat", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.62 MB"),
                new AuditReport(13, "Haryana", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "5.43 MB"),
                new AuditReport(14, "Himachal Pradesh", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.44 MB"),
                new AuditReport(15, "Jammu and Kashmir", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "4.86 MB"),
                new AuditReport(16, "Jharkhand", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.04 MB"),
                new AuditReport(17, "Karnataka", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "6.59 MB"),
                new AuditReport(18, "Kerala", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.88 MB"),
                new AuditReport(19, "Lakshadweep", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "18.72 MB"),
                new AuditReport(20, "Madhya Pradesh", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.72 MB"),
                new AuditReport(21, "Maharashtra", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.75 MB"),
                new AuditReport(22, "Manipur", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.13 MB"),
                new AuditReport(23, "Meghalaya", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.81 MB"),
                new AuditReport(24, "Mizoram", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "7.21 MB"),
                new AuditReport(25, "Nagaland", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "13.64 MB"),
                new AuditReport(26, "Odisha", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.83 MB"),
                new AuditReport(27, "Puducherry", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.52 MB"),
                new AuditReport(28, "Punjab", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "2.66 MB"),
                new AuditReport(29, "Rajasthan", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "3.22 MB"),
                new AuditReport(30, "Sikkim", "2016 - 2017", "Audit Report (Monday, May 27, 2019)", "1.77 MB")
            };

            // Financial Sanctions array
            FinancialSanction[] financialSanctions = {
                new FinancialSanction(1, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.71 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Secondary Education - SE", "Recurring", "SC"),
            new FinancialSanction(2, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.72 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Secondary Education - SE", "Non Recurring", "SC"),
            new FinancialSanction(3, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.6 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Elementary Education - EE", "Recurring", "SC"),
            new FinancialSanction(4, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.53 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Teacher Education - TE", "Recurring", "SC"),
            new FinancialSanction(5, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.67 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Secondary Education - SE", "Recurring", "General"),
            new FinancialSanction(6, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.62 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Elementary Education - EE", "Recurring", "General"),
            new FinancialSanction(7, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.6 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Teacher Education - TE", "Recurring", "General"),
            new FinancialSanction(8, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.7 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Secondary Education - SE", "Recurring", "General"),
            new FinancialSanction(9, "Releases of Balance of 1st Installment (Friday, November 1, 2019) - (1.68 MB)", "2019 - 2020", "Haryana", "Samagra Shiksha", "Elementary Education - EE", "Recurring", "General")
            };

            while (true) {
                // Display the selection menu
                System.out.println("Select the data you want to display:");
                System.out.println("1. Audit Reports");
                System.out.println("2. Financial Sanctions");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                // Input handling
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (choice) {
                        case 1: // Display Audit Reports
                            System.out.print("Enter the state: ");
                            String stateAudit = scanner.nextLine();
                            System.out.println("\nAudit Reports for " + stateAudit + ":");
                            boolean foundAudit = false;
                            for (AuditReport report : auditReports) {
                                if (report.getState().equalsIgnoreCase(stateAudit)) {
                                    report.display();
                                    foundAudit = true;
                                }
                            }
                            if (!foundAudit) {
                                System.out.println("No audit reports found for " + stateAudit + ".");
                            }
                            break;

                        case 2: // Display Financial Sanctions
                            System.out.print("Enter the state: ");
                            String stateSanction = scanner.nextLine();
                            System.out.println("\nFinancial Sanctions for " + stateSanction + ":");
                            boolean foundSanction = false;
                            for (FinancialSanction sanction : financialSanctions) {
                                if (sanction.getState().equalsIgnoreCase(stateSanction)) {
                                    sanction.display();
                                    foundSanction = true;
                                }
                            }
                            if (!foundSanction) {
                                System.out.println("No financial sanctions found for " + stateSanction + ".");
                            }
                            break;

                        case 3: // Exit
                            System.out.println("Exiting the program. Goodbye!");
                            return; // Exit the program

                        default:
                            System.out.println("Invalid choice! Please select 1, 2, or 3.");
                            break;
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
                    scanner.next(); // Clear the invalid input
                }
            }
        }
    }
}
