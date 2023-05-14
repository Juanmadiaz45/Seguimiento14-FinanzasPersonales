package com.example.tableviewseguimiento14;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class FinancesController {

    public Transaction addTransaction(double amount, String description, String type, LocalDate date) {

        return new Transaction(amount, description, type, date);

    }

    public double calculateBalance(ObservableList<Transaction> transactions) {

        double balance = 0;

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals("Ingreso")) {

                balance += transaction.getAmount();

            } else {

                balance -= transaction.getAmount();

            }

        }

        return balance;

    }

}