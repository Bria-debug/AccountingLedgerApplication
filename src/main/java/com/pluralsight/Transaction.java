package com.pluralsight;

public class Transaction { //Step 1 class that reps a transaction
    //Step 2 instance V to hold and store transaction action
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

//Step 3 Constructor - called when you create a new transaction
    public Transaction(String date, String time, String description,String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
//Step 4 Getters - allow other classes to safely access each field
    public String getDate() {
        return date;
    }
    //Step 5 (optional) - only needed if you want to modify the data later
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    // Step 6 toString() - defines how the transaction will look when printed
    public String toString(){
        String formattedAmount = String.format("$%.2f", amount);
        //formatting the amount positive deposits and negative payments

        return String.format("%-12s %10s %20s %15s %10s",
                date, time, description, vendor, formattedAmount);
}}

