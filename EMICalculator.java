import java.util.Scanner;

public class EMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Principal Amount: ");
        double principal = scanner.nextDouble();
        while (principal < 0) {
            System.out.print("Principal amount cannot be negative. Please enter a valid Principal Amount: ");
            principal = scanner.nextDouble();
        }

        System.out.print("Enter Annual Interest Rate (%): ");
        double annualRate = scanner.nextDouble();
        while (annualRate < 0) {
            System.out.print("Interest rate cannot be negative. Please enter a valid Annual Interest Rate (%): ");
            annualRate = scanner.nextDouble();
        }

        System.out.print("Enter Loan Tenure (months): ");
        int tenure = scanner.nextInt();
        while (tenure < 0) {
            System.out.print("Loan tenure cannot be negative. Please enter a valid Loan Tenure (months): ");
            tenure = scanner.nextInt();
        }

        double monthlyRate = annualRate / 12 / 100;
        double emi = calculateEMI(principal, monthlyRate, tenure);
        double totalPayment = emi * tenure;
        double totalInterest = totalPayment - principal;

        System.out.printf("Monthly EMI: %d\n", Math.round(emi));
        System.out.printf("Total Interest Payable: %d\n", Math.round(totalInterest));
        System.out.printf("Total Payment (Principal + Interest): %d\n\n", Math.round(totalPayment));

        generateAmortizationTable(principal, monthlyRate, tenure, emi);

        scanner.close();
    }

    public static double calculateEMI(double principal, double rate, int tenure) {
        return (principal * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
    }

    public static void generateAmortizationTable(double principal, double rate, int tenure, double emi) {
        double remainingPrincipal = principal;

        System.out.println("Month\tPrincipal Payment\tInterest Payment\tTotal Payment\tRemaining Principal");
        for (int month = 1; month <= tenure; month++) {
            double interestPayment = remainingPrincipal * rate;
            double principalPayment = emi - interestPayment;
            remainingPrincipal -= principalPayment;

            System.out.printf("%d\t%d\t%d\t%d\t%d\n", 
                month, 
                Math.round(principalPayment), 
                Math.round(interestPayment), 
                Math.round(emi), 
                Math.round(remainingPrincipal)
            );
        }
    }
}
