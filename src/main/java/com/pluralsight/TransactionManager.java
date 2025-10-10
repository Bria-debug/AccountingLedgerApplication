package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class TransactionManager {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter name of transaction file to read:");
        String importName = keyboard.nextLine().trim();
        //to read file

        System.out.println("Enter name of file to write new transactions to");
        String exportName = keyboard.nextLine().trim();

        Transaction[] transactionsList = new Transaction[50]; //50 max transactions
        int count = 0;

        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/" + exportName));

            String input;
            bufWriter.write("date|time|description|vendor|amount\n");

            while ((input = bufReader.readLine()) != null) {
                String[] tokens = input.split("\\|");

                if (!tokens[0].equals("date")) {
                    String date = tokens[0];
                    String time = tokens[1];
                    String description = tokens[2];
                    String vendor = tokens[3];
                    double amount = Double.parseDouble(tokens[4]);

                    transactionsList[count] = new Transaction(date, time, description, vendor, amount);
                    BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/" + exportName));

                    count++;

                }
                bufReader.close();
                bufWriter.close();
            }

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    }







