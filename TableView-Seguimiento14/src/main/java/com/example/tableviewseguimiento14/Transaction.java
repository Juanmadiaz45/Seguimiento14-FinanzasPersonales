package com.example.tableviewseguimiento14;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {

    private final DoubleProperty amount;

    private final StringProperty description;

    private final StringProperty type;

    private final ObjectProperty<LocalDate> date;

    public Transaction(double amount, String description, String type, LocalDate date) {

        this.amount = new SimpleDoubleProperty(amount);

        this.description = new SimpleStringProperty(description);

        this.type = new SimpleStringProperty(type);

        this.date = new SimpleObjectProperty<>(date);

    }

    public double getAmount() {
        return amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

}