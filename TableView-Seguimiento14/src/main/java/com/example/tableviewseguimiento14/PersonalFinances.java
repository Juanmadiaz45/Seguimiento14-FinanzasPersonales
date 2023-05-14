package com.example.tableviewseguimiento14;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class PersonalFinances extends Application {

    private final FinancesController financesController = new FinancesController();

    private final FinancesView financesView = new FinancesView(financesController);

    public static void main(String[] args) { launch(args); }

    @Override

    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        root.setCenter(financesView.createView());

        Scene scene = new Scene(root);

        primaryStage.setTitle("Finanzas personales");

        primaryStage.setScene(scene);

        primaryStage.show();

    }

}