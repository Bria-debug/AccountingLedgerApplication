package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class TransactionManager {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); //reads what is typed in

        System.out.println("Enter name of transaction file to read:");
        String importName = keyboard.nextLine().trim(); //STEP 1 Ask for input and output file names

        System.out.println("Enter name of file to write new transactions to:");
        String exportName = keyboard.nextLine().trim(); //to write transactions to

        Transaction[] transactionsList = new Transaction[100]; //STEP 2 Make ARRAY to hold transactions-max 100
        int count = 0;

        try { //STEP 3 Open the input and output files
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/" + importName));
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/" + exportName));
            bufWriter.write("date|time|description|vendor|amount"); //STEP 4 create header for export file
            bufWriter.newLine(); //to read and write file

            String inputLine;
            //STEP 5 Use loop to read each line of file till there are none left
            while ((inputLine = bufReader.readLine()) != null) { //reads import file by line
                String[] tokens = inputLine.split("\\|");
                if (!tokens[0].equalsIgnoreCase("date")) { //skipping that header
                    String date = tokens[0];
                    String time = tokens[1];
                    String description = tokens[2];
                    String vendor = tokens[3];
                    double amount = Double.parseDouble(tokens[4]);

                    //STEP 6 Make a new transaction file array style

                    transactionsList[count] = new Transaction(date, time, description, vendor, amount);
                    count++;
                    //STEP 7 writes transactions to export file
                    bufWriter.write(String.join("|", tokens));
                    bufWriter.newLine();
                }
            }
            //STEP 8 close it up after reading and writing
            bufReader.close();
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        //STEP 8 use loop to keep displaying main menu until exit
        boolean running = true;
        while (running) {
            System.out.println("\n*** Home Menu ***");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = keyboard.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    count = addTransaction(keyboard, transactionsList, count, exportName, true);
                    break;
                case "P":
                    count = addTransaction(keyboard, transactionsList, count, exportName, false);
                    break;
                case "L":
                    showLedger(transactionsList, count);
                    break;
                case "X":
                    running = false;
                    System.out.println("Exiting Transaction Manager. GOOD BYE");
                    break;
                default:
                    System.out.println("Option Invalid. Please Try Again");
            }
        }
        keyboard.close();
    }
    //shows all transactions stored/saved
    public static void showLedger(Transaction[] list, int count) {
        System.out.println("\n*** Ledger ***");
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }}

    //adds new deposit or payment
    public static int addTransaction(Scanner keyboard, Transaction[] list, int count, String exportName, boolean isDeposit) {
        System.out.println(isDeposit ? "Adding Deposit..." : "Adding Payment...");
        try {
            //gets transaction details
            System.out.print("Enter date(YYYY-MM-DD):");
            String date = keyboard.nextLine().trim();

            System.out.print("Enter time(HH:MM:ss):");
            String time = keyboard.nextLine().trim();

            System.out.print("Enter description:");
            String description = keyboard.nextLine().trim();

            System.out.print("Enter vendor:");
            String vendor = keyboard.nextLine().trim();

            System.out.println("Enter amount");
            double amount = Double.parseDouble(keyboard.nextLine().trim());

            //make payments is negative make deposits is postive
            if (!isDeposit) {
                amount = -Math.abs(amount);

            }
            //create a transaction to add to array
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            list[count] = newTransaction;
            count++;

            //Step idk at this point but append the transaction to export file
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/" + exportName, true));
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            bufWriter.newLine();
            bufWriter.close();

            System.out.println("Transactions added successfully");

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }catch (NumberFormatException e) {
            System.out.println("Invalid amount entered..try again");
        }
        return count;


        }
}








