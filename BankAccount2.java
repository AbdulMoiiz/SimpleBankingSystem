import java.util.Scanner;

abstract class BankAccount {
    Scanner sc1 = new Scanner(System.in);
    String address_C;
    String cnic_C;
    String password_C;
    String name_C;
    int credit_amount_c, debit_amount_c, total_Amount_c;
    double fine_C;

    BankAccount() {
        debit_amount_c = 0;
        credit_amount_c = 0;
    }

    void setData() {
        System.out.println("Enter Your Name:");
        name_C = sc1.nextLine();
        System.out.println("Enter Your CNIC:");
        cnic_C = sc1.nextLine();
        System.out.println("Enter Your Pin:");
        password_C = sc1.nextLine();
        System.out.println("Enter Your Address:");
        address_C = sc1.nextLine();
        System.out.println("Enter Your Amount You Want to Credit:");
        total_Amount_c = sc1.nextInt();
        sc1.nextLine(); // consume the newline character
    }

    void debitAmount() {
        System.out.println("Enter Amount You Want To Withdraw: ");
        int amount = sc1.nextInt();
        if (total_Amount_c < amount) {
            System.out.println("Not Enough Amount in Account.");
        } else {
            total_Amount_c -= amount;
            debit_amount_c = amount;
            System.out.println("Your Account Balance Updated.");
            display();
        }
    }

    void creditAmount() {
        System.out.println("Enter Amount You Want To Credit: ");
        int amount = sc1.nextInt();
        total_Amount_c += amount;
        credit_amount_c = amount;
        System.out.println("Your Account Balance Updated.");
        display();
    }

    void display() {
        System.out.println();
        System.out.println("Your Total Balance is: " + total_Amount_c);
        System.out.println("Penalty on your amount is: " + fine_C);
        System.out.println("Your Last Credit Amount: " + credit_amount_c);
        System.out.println("Your Last Debit Amount: " + debit_amount_c);
    }

    abstract void checkBook_C();

    abstract void penalty_C();
}

class CurrentA extends BankAccount {
    void checkBook_C() {
        System.out.println("Check Book Issued / No fee.");
    }

    void penalty_C() {
        if (total_Amount_c < 1000) {
            fine_C = (0.05 * total_Amount_c);
            total_Amount_c -= fine_C;
        } else {
            fine_C = 0;
        }
    }
}

class SavingA extends BankAccount {
    void checkBook_C() {
        total_Amount_c -= 1500;
        System.out.println("Check Book Charges 1500 has been deducted from your account.");
    }

    void penalty_C() {
        if (total_Amount_c < 1000) {
            fine_C = (0.05 * total_Amount_c);
            total_Amount_c -= fine_C;
        } else {
            fine_C = 0;
        }
    }
}

public class BankAccount2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            SavingA[] s = new SavingA[100];
            CurrentA[] c = new CurrentA[100];
            for (int i = 0; i < 100; i++) {
                s[i] = new SavingA();
                c[i] = new CurrentA();
            }
            int savingCount = 0;
            int currentCount = 0;
            String cHoice2;
            do {
                System.out.println("\033[H\033[2J");
                System.out.flush();
                System.out.println("* -------------------------------- *");
                System.out.println("| Hello! Welcome to the ABC Bank   |");
                System.out.println("* -------------------------------- *");
                int choice;
                System.out.println("Enter 1 to create account \nEnter 2 To proceed with Existing Account");
                choice = sc.nextInt();
                sc.nextLine(); // consume the newline character
                if (choice == 1) {
                    System.out.println("Enter 1 to Create Saving Account\nEnter 2 to Create Current Account");
                    int soc = sc.nextInt();
                    sc.nextLine(); // consume the newline character
                    switch (soc) {
                        case 1:
                            s[savingCount].setData();
                            System.out.println("*-------------------------------*");
                            System.out.println("| Your account Has Been Created |");
                            System.out.println("*-------------------------------*");
                            savingCount++;
                            break;
                        case 2:
                            c[currentCount].setData();
                            System.out.println("*-------------------------------*");
                            System.out.println("| Your account Has Been Created |");
                            System.out.println("*-------------------------------*");
                            currentCount++;
                            break;
                        default:
                            System.out.println("Wrong Choice");
                    }

                } else if (choice == 2) {
                    String nameC1, passwordC1;
                    System.out.println("Enter Your Name: ");
                    nameC1 = sc.nextLine();
                    System.out.println("Enter Your Pin");
                    passwordC1 = sc.nextLine();
                    boolean accountFound = false;
                    for (int j = 0; j < savingCount; j++) {
                        if (nameC1.equals(s[j].name_C) && passwordC1.equals(s[j].password_C)) {
                            System.out.println();
                            System.out.println("You Have A Saving Account ");
                            int choice4;
                            do {
                                s[j].penalty_C();
                                s[j].display();
                                int Choice3;
                                System.out.println("  1--> To credit Amount \n 2--> To withdraw \n 3-9 -> For Exit");
                                Choice3 = sc.nextInt();
                                if (Choice3 == 1) {
                                    s[j].creditAmount();

                                } else if (Choice3 == 2) {
                                    s[j].debitAmount();
                                } else {
                                    System.out.println("Exit");
                                    break;
                                }

                                System.out.println("Press 1 to Continue To Your Bank Account ");
                                choice4 = sc.nextInt();
                            } while (choice4 == 1);
                            accountFound = true;
                            break;
                        }
                    }
                    for (int j = 0; j < currentCount; j++) {
                        if (nameC1.equals(c[j].name_C) && passwordC1.equals(c[j].password_C)) {
                            System.out.println();
                            System.out.println("You Have A Current Account ");
                            int choice4;
                            do {
                                c[j].penalty_C();
                                c[j].display();
                                int Choice3;
                                System.out.println("  1--> To credit Amount \n 2--> To withdraw \n 3-9 -> For Exit");
                                Choice3 = sc.nextInt();
                                if (Choice3 == 1) {
                                    c[j].creditAmount();

                                } else if (Choice3 == 2) {
                                    c[j].debitAmount();
                                } else {
                                    System.out.println("Exit");
                                    break;
                                }

                                System.out.println("Press 1 to Continue To Your Bank Account ");
                                choice4 = sc.nextInt();
                            } while (choice4 == 1);
                            accountFound = true;
                            break;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("You Don't Have Any Account ");
                    }

                } else {
                    System.out.println("Exit");
                }

                System.out.println("Do You Want to Continue To ABC Bank: y/n");
                cHoice2 = sc.next();
            } while (cHoice2.equalsIgnoreCase("y"));
        }
    }
}
