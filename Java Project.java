package package_1;
import java.util.Scanner;

class BankDetails {  
    private String accno;  
    private String name;  
    private String acc_type;  
    private long balance;  
    private double interestRate;  
    private long loanAmount; 
    Scanner sc = new Scanner(System.in);  
    
    public void openAccount() { 
        System.out.print("Enter Account No: ");  
        accno = sc.next();  
        System.out.print("Enter Account type: ");  
        acc_type = sc.next();  
        System.out.print("Enter Name: ");  
        name = sc.next();  
        System.out.print("Enter Balance: ");  
        balance = sc.nextLong();  
        System.out.print("Enter Interest Rate (%): ");  
        interestRate = sc.nextDouble();  
    }  
     
    public void showAccount() {  
        System.out.println("Name of account holder: " + name);  
        System.out.println("Account no.: " + accno);  
        System.out.println("Account type: " + acc_type);  
        System.out.println("Balance: " + balance);
        System.out.println("Loan amount: " + loanAmount); 
    }  
    
    public void setLoanAmount(long loanAmount) { 
        this.loanAmount = loanAmount;
    }
    
    public long getLoanAmount() { 
        return loanAmount;
    }
    
    public void deposit() {  
        long amt;  
        System.out.println("Enter the amount you want to deposit: ");  
        amt = sc.nextLong();  
        if (amt <= 0) {  
            System.out.println("Invalid deposit amount! Please enter a positive value.");
            return;
        }
        balance = balance + amt;
        calculateInterest();  
        System.out.println("Deposit successful. Updated balance: " + balance);
    }  
    
    public void withdrawal() {  
        long amt;  
        System.out.println("Enter the amount you want to withdraw: ");  
        amt = sc.nextLong();  
        if (amt <= 0) {  
            System.out.println("Invalid withdrawal amount! Please enter a positive value.");
            return;
        }
        if (balance >= amt) {  
            balance = balance - amt;  
            System.out.println("Withdrawal successful. Updated balance: " + balance);  
        } else {  
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );  
        }  
    }  
    
    public boolean search(String ac_no) {  
        if (accno.equals(ac_no)) {  
            showAccount();  
            return (true);  
        }  
        return (false);  
    }

    private void calculateInterest() {
        double interestAmount = balance * (interestRate / 100.0);  
        balance += interestAmount;  
        System.out.println("Interest added: " + interestAmount);
    }

    public void deleteAccount() {
        balance = 0;
        name = "";
        acc_type = "";
        System.out.println("Account deleted successfully.");
    }

    public void updateAccount() {
        System.out.println("Enter new Name: ");
        name = sc.next();
        System.out.println("Enter new Account type: ");
        acc_type = sc.next();
        System.out.println("Enter new Balance: ");
        balance = sc.nextLong();
        System.out.println("Enter new Interest Rate (%): ");
        interestRate = sc.nextDouble();
        System.out.println("Account details updated successfully.");
    }

    public double getTotalInterestEarned() {
        double interestAmount = balance * (interestRate / 100.0);
        return interestAmount;
    }

    public void takeLoan() {
        System.out.println("Enter the loan amount you want to take: ");
        long loanAmount = sc.nextLong();
        if (loanAmount <= 0) {
            System.out.println("Invalid loan amount! Please enter a positive value.");
            return;
        }
        if (balance * 0.5 >= loanAmount) {
            balance -= loanAmount;
            setLoanAmount(loanAmount); 
            System.out.println("Loan of " + loanAmount + " taken successfully. Updated balance: " + balance);
        } else {
            System.out.println("You are not eligible for the loan. Your balance is too low.");
        }
    }
}  

public class Bank_Application {  
    private static final String USERNAME = "siddesh";
    private static final String PASSWORD = "s";

    public static void main(String arg[]) {  
        Scanner sc = new Scanner(System.in);    
        System.out.println("Welcome to the Banking System!");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (authenticate(username, password)) {
            System.out.println("Authentication successful!");
            startBankingSystem(sc);
        } else {
            System.out.println("Authentication failed! Please check your username and password.");
        }
    }

    private static boolean authenticate(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }

    private static void startBankingSystem(Scanner sc) {
        BankDetails account = new BankDetails();
        int ch;
        do {
            System.out.println("\n ***Banking System Application***");  
            System.out.println("1. Display account details \n 2. Deposit the amount \n 3. Withdraw the amount \n 4. Delete account \n 5. Update account details \n 6. Display total interest earned \n 7. Take a loan \n 8. Create account \n 9. Exit ");  
            System.out.println("Enter your choice: ");  
            ch = sc.nextInt();  

            switch (ch) {  
                case 1:  
                    account.showAccount();  
                    break;  
                case 2:  
                    account.deposit();  
                    break;  
                case 3:  
                    account.withdrawal();  
                    break;  
                case 4:  
                    account.deleteAccount();  
                    break;
                case 5:
                    account.updateAccount();  
                    break;
                case 6:
                    double totalInterest = account.getTotalInterestEarned();
                    System.out.println("Total interest earned: " + totalInterest);
                    break;
                case 7:
                    account.takeLoan();  
                    break;
                case 8:
                    account.openAccount();  
                    break;
                case 9:
                    System.out.println("Exiting...");  
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");  
                    break;
            }  
        } while (ch != 9);  
    }
}
