package com.example.tableviewseguimiento14;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.geometry.Insets;

import javafx.scene.control.*;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import java.time.LocalDate;

class FinancesView {

    private final FinancesController financesController;

    private TableView<Transaction> transactionsTable = new TableView<>();

    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    private final TextField amountTextField = new TextField();

    private final TextField descriptionTextField = new TextField();

    private final ComboBox<String> typeComboBox = new ComboBox<>();

    private final DatePicker dateDatePicker = new DatePicker();

    private final Label balanceLabel = new Label("Balance: $0.00");

    public FinancesView(FinancesController financesController) {

        this.financesController = financesController;

    }

    public VBox createView() {

        // Crea tabla de transacciones.

        TableColumn<Transaction, String> amountColumn = new TableColumn<>("Monto");

        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asString("$%.2f"));

        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Descripción");

        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        TableColumn<Transaction, String> typeColumn = new TableColumn<>("Tipo");

        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Fecha");

        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty().asString());

        transactionsTable.getColumns().addAll(amountColumn, descriptionColumn, typeColumn, dateColumn);

        transactionsTable.setItems(transactions);

        // Crea el formulario para añadir transacciones

        HBox addHBox = new HBox();

        addHBox.setSpacing(10);

        addHBox.setPadding(new Insets(10));

        amountTextField.setPromptText("Monto");

        descriptionTextField.setPromptText("Descripción");

        typeComboBox.getItems().addAll("Ingreso", "Gasto");

        typeComboBox.setValue("Ingreso");

        dateDatePicker.setValue(LocalDate.now());

        Button addButton = new Button("Añadir");

        addButton.setOnAction(event -> addTransaction());

        addHBox.getChildren().addAll(amountTextField, descriptionTextField, typeComboBox, dateDatePicker, addButton);

        // Crea los botones para filtrar transacciones.

        HBox filterButtonsHBox = new HBox();

        filterButtonsHBox.setSpacing(10);

        filterButtonsHBox.setPadding(new Insets(10));

        Button expensesButton = new Button("Gastos");

        expensesButton.setOnAction(event -> filterTransactions("Gasto"));

        Button incomeButton = new Button("Ingreso");

        incomeButton.setOnAction(event -> filterTransactions("Ingreso"));

        Button allButton = new Button("Todo");

        allButton.setOnAction(event -> transactionsTable.setItems(transactions));

        filterButtonsHBox.getChildren().addAll(expensesButton, incomeButton, allButton);

        // Añade elementos al layout principal

        VBox vboxPrincipal = new VBox();

        vboxPrincipal.getChildren().addAll(transactionsTable, addHBox, filterButtonsHBox, balanceLabel);

        return vboxPrincipal;

    }

    private void addTransaction() {

        double amount;

        try {

            amount = Double.parseDouble(amountTextField.getText());

        } catch (NumberFormatException e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            errorAlert.setTitle("Error añadiendo la transacción");

            errorAlert.setHeaderText(null);

            errorAlert.setContentText("El monto debe ser un número valido.");

            errorAlert.showAndWait();

            return;

        }

        String description = descriptionTextField.getText();

        String type = typeComboBox.getValue();

        LocalDate date = dateDatePicker.getValue();

        Transaction newTransaction = financesController.addTransaction(amount, description,type,date);

        transactions.add(0,newTransaction); // Add to the beginning of the list

        updateBalance();

    }

    private void filterTransactions(String type) {

        ObservableList<Transaction> filteredTransactions = FXCollections.observableArrayList();

        for (Transaction transaction : transactions) {

            if (transaction.getType().equals(type)) {

                filteredTransactions.add(transaction);

            }

        }

        transactionsTable.setItems(filteredTransactions);

    }

    private void updateBalance() {

        double balance = financesController.calculateBalance(transactions);

        balanceLabel.setText(String.format("Balance: $%.2f", balance));

    }

}