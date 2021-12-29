package GUI.main;

import GUI.loading.LoadingScreen;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import logic.Process;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox box = new VBox();

        AnchorPane labelPane = new AnchorPane();
        labelPane.setPrefSize(300, 50);
        Label label = new Label("Dimensions");
        labelPane.getChildren().add(label);

        AnchorPane.setLeftAnchor(label, 117.5);
        AnchorPane.setRightAnchor(label, 117.5);
        AnchorPane.setTopAnchor(label, 20.0);

        AnchorPane row = new AnchorPane();
        row.setPrefSize(200, 40);

        Label rowLabel = new Label("rows");
        TextField rowField = new TextField();
        rowField.setAlignment(Pos.CENTER);

        rowField.setTooltip(new Tooltip("the height of the game board\nis a number between 5 and 30"));

        rowField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                rowField.setText(newValue.replaceAll("[^\\d]", ""));
            }

        });


        rowField.setPrefSize(200, 20);


        row.getChildren().addAll(rowLabel, rowField);
        AnchorPane.setRightAnchor(rowField, 20.0);
        AnchorPane.setLeftAnchor(rowLabel, 20.0);

        AnchorPane columns = new AnchorPane();
        columns.setPrefSize(200, 50);
        Label columnLabel = new Label("columns");
        TextField columnField = new TextField();
        columnField.setAlignment(Pos.CENTER);

        columnField.setTooltip(new Tooltip("the width of the game board\nis a number between 5 and 19"));

        columnField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                columnField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        columnField.setPrefSize(200, 20);


        columns.getChildren().addAll(columnLabel, columnField);

        AnchorPane.setRightAnchor(columnField, 20.0);
        AnchorPane.setLeftAnchor(columnLabel, 20.0);

        AnchorPane buttons = new AnchorPane();
        buttons.setPrefSize(300, 50);

        Button nextButton = new Button("Next");
        nextButton.setMinSize(100, 30);
        nextButton.setId("nextButton");
        nextButton.setTooltip(new Tooltip("lets make a maze!"));
        nextButton.setOnAction(event -> {
            goNext(rowField.getText(), columnField.getText(), primaryStage);
        });

        rowField.addEventHandler(ActionEvent.ACTION, event -> columnField.requestFocus());
        columnField.addEventHandler(ActionEvent.ACTION, event -> {
            goNext(rowField.getText(), columnField.getText(), primaryStage);
        });
        buttons.getChildren().addAll(nextButton);
        AnchorPane.setRightAnchor(nextButton, 25.0);


        AnchorPane nameAnchor = new AnchorPane();

        Label mahsaKarimi = new Label("mahsa karimi");
        mahsaKarimi.setStyle("-fx-font-size: 15px");

        Label amirHosseinRezvani = new Label("amirHossein rezvani");
        amirHosseinRezvani.setStyle("-fx-font-size: 15px");

        nameAnchor.getChildren().addAll(mahsaKarimi, amirHosseinRezvani);

        AnchorPane.setLeftAnchor(mahsaKarimi, 25.0);
        AnchorPane.setTopAnchor(mahsaKarimi, 10.0);

        AnchorPane.setRightAnchor(amirHosseinRezvani, 25.0);
        AnchorPane.setTopAnchor(amirHosseinRezvani, 10.0);


        AnchorPane numberAnchor = new AnchorPane();

        Label mahsaKarimiNumber = new Label("9823723");
        mahsaKarimiNumber.setStyle("-fx-font-size: 15px");

        Label amirHosseinRezvaniNumber = new Label("9819023");
        amirHosseinRezvaniNumber.setStyle("-fx-font-size: 15px");

        numberAnchor.getChildren().addAll(mahsaKarimiNumber, amirHosseinRezvaniNumber);

        AnchorPane.setLeftAnchor(mahsaKarimiNumber, 25.0);

        AnchorPane.setRightAnchor(amirHosseinRezvaniNumber, 25.0);

        box.getChildren().addAll(labelPane, row, columns, buttons, nameAnchor, numberAnchor);


        Scene scene = new Scene(box, 300, 250);
        scene.getStylesheets().add(getClass().getResource("mainCSS.css").toExternalForm());
        primaryStage.setTitle("diameters");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void goNext(String height, String width, Stage primaryStage) {
        if (height.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please fill the row field").showAndWait();
        } else if (width.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please fill the column field").showAndWait();
        } else {
            Process.process(primaryStage, Integer.valueOf(height), Integer.valueOf(width), new LoadingScreen());
        }
    }
}