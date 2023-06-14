import com.sun.security.jgss.GSSUtil;

import java.util.*;
public class BankSoftware {


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        BankAccount obj1 = new BankAccount();
        BankAccount obj2 = new BankAccount();
        BankAccount obj3 = new BankAccount();
        System.out.println("Welcome to the Bank.");
        int opt;
        boolean obj1init = false;
        boolean obj2init = false;
        boolean obj3init = false;

        do {
            System.out.println("Choose from the following options");
            System.out.println("1) Make a new Account");
            System.out.println("2) Log in to your existing account.");
            System.out.println("3) To exit.");
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    if(obj1init == false && obj2init == false && obj3init == false) {
                        obj1.makeAccount();
                        obj1init = true;
                    }
                    else if(obj1init == true && obj2init == false && obj3init == false){
                        obj2.makeAccount();
                        obj2init = true;
                    }
                    else if(obj1init == true && obj2init == true && obj3init == false){
                        obj3.makeAccount();
                        obj3init = true;
                    }
                    else if(obj1init == true && obj2init == true && obj3init == true){
                        System.out.println("All Slots full, no further accounts can be created");
                    }
                    break;
                case 2:
                    System.out.println("Enter your account number:");
                    int accNum = sc.nextInt();
                    sc.nextLine();
                    if (accNum==obj1.accNo) {
                        obj1.authenticate();
                    } else if (accNum==(obj2.accNo)) {
                        obj2.authenticate();
                    } else if (accNum==(obj3.accNo)) {
                        obj3.authenticate();
                    }
                    else {
                        System.out.println("Account does not exist.");
                    }


                    break;
                case 3:
                    System.out.println("Exiting the program!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Refer the menu and enter the option");
            }

        }while(opt != 3);
    }
}
class BankAccount{
    public static long accNo;
    private static String name;
    private static String email;
    private float balance;
    private int lastTransaction = 0;
    private float lastTransactionAmt = 0;
    private static String[] password = new String[2];
    boolean locked = false;

    BankAccount(){

    }

    void makeAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = sc.nextLine();
        System.out.println("Enter your E-mail: ");
        email = sc.nextLine();
        accNo = (long) (9999 * (Math.random() + 1000));
        System.out.println("Your Account Number is: " + accNo);
        int psc = 0;
        do {
            System.out.println("Set your password: ");
            password[0] = sc.nextLine();

            System.out.println("Re-enter your password: ");
            password[1] = sc.nextLine();
            if (password[1].equals(password[0])){
                System.out.println("Password Set");
                psc = 1;
            }
            else{
                System.out.println("Passwords do not match. Try again.");
            }
        }while(psc == 0);
    }
    void authenticate() {
        Scanner sc = new Scanner(System.in);

        int c = 4;
        int k = 0;

        if (locked == false) {
            System.out.println("Enter your Password " + name);
            do {
                String pass = sc.nextLine();
                if (pass.equals(password[1])) {
                    System.out.println("Login Successful. ");
                    k = 0;
                    menu();
                } else {
                    c--;
                    System.out.println("You have entered wrong credentials ");
                    if(c>0) {
                        System.out.println("Your account will be LOCKED after " + c + " wrong attempts");
                    }
                    k = 1;
                    if (c == 0) {
                        System.out.println("Account locked, Contact your Relationship Manager.");
                        locked = true;
                        break;
                    }
                }
            } while (k == 1);


        }
        else if (locked == true){
            System.out.println("Your Account is locked. Please Contact your Relationship Manager");
        }
    }
    void deposit(float amount){
        if(amount != 0){
            balance += amount;
            System.out.println("₹"+amount+ " has been deposited to your account");
            System.out.println("Current balance is ₹"+balance);
            lastTransaction = 1;
            lastTransactionAmt = amount;
        }
    }
    void withdraw(float amount){
        if(amount !=0 && balance>=5000){
            balance -= amount;
            System.out.println("₹"+amount+" has been withdrawn");
            System.out.println("Current balance is ₹"+balance);
            lastTransaction = -1;
            lastTransactionAmt = amount;

        }
        else if(balance<5000){
            System.out.println("Insufficient balance. Withdrawal Denied.");
        }
    }
    void logOut(){
        System.out.println("Thank you for using our services!");
        System.out.println("You have successfully logged out");
        System.exit(0);
    }
    void lastTransactionDetail(){
        if(lastTransaction == 1){
            System.out.println("Last Transaction was DEPOSIT of ₹"+lastTransactionAmt);
        }
        if(lastTransaction == -1){
            System.out.println("Last Transaction was WITHDRAWAL of ₹"+lastTransactionAmt);
        }
        if(lastTransaction == 0){
            System.out.println("There have been no transactions in your account yet");
        }
    }
    void menu(){
        Scanner sc = new Scanner(System.in);
        char ch;
        System.out.println("Welcome "+name);
        System.out.println("Choose from the following options ");
        System.out.println("a) Check Balance ");
        System.out.println("b) Deposit");
        System.out.println("c) Withdraw");
        System.out.println("d) Last Transaction detail");
        System.out.println("e) Log Out");
        do{
            System.out.println("Choose an option from the menu above");
            ch = sc.next().charAt(0);
            System.out.println();
            switch(ch){
                case 'a':
                    System.out.println("Your bank balance is ");
                    System.out.println(balance);
                    System.out.println();
                    break;
                case 'b':
                    System.out.println("Enter the amount to be deposited ");
                    float dAmt = sc.nextFloat();
                    deposit(dAmt);
                    break;

                case 'c':
                    System.out.println("Enter the amount to be withdrawn");
                    float wAmt = sc.nextFloat();
                    withdraw(wAmt);
                    break;

                case 'd':
                    System.out.println("Last Transaction detail");
                    lastTransactionDetail();

                    break;
                case 'e':
                    System.out.println();
                    logOut();
                    break;
                default:
                    System.out.println("Please refer the menu and choose an option to proceed");

            }
        }while(ch!='e');
    }

}
