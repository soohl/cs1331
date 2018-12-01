package edu.gatech.cs1331.hw7;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * @author slee3245
 * @version 1.2
 */
public class OfficeHourQueue extends Application {

    private LinkedQueue<String> obList;
    private final String password = "CS1321R0X";

    /**
     * Starting method of javafx application
     * @param stage that are going to be set.
     */
    @Override
    public void start(Stage stage) {
        obList = new LinkedQueue<>();

        ListView<String> listView = new ListView<>(obList);

        Label label1 = new Label();
        Label label2 = new Label();
        StringProperty property1 =
                new SimpleStringProperty("Current Number Of Students In Queue: "
                        + obList.size());
        StringProperty property2 =
                new SimpleStringProperty("Max Number Of Students In Queue: "
                        + obList.getMaxSize());
        label1.textProperty()
                .bind(property1);
        label2.textProperty()
                .bind(property2);

        VBox labelBox = new VBox();
        labelBox.getChildren().addAll(label1, label2);

        Button addButton = new Button();
        addButton.setText("Add Student");
        Button dequeueButton = new Button();
        dequeueButton.setText("Dequeue Student");

        dequeueButton.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog("enter password");
                dialog.setTitle("Privilege Check");
                dialog.setHeaderText("Confirm the privilege");
                dialog.setContentText("Please enter the password:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(input_password -> {
                        if (input_password.equals(password)) {
                            obList.remove(0);
                        }
                    });
                property1.set("Current Number Of Students In Queue: "
                        + obList.size());
                property2.set("Max Number Of Students In Queue: "
                        + obList.getMaxSize());
            });

        dequeueButton.disableProperty().
                bind(Bindings.isEmpty(obList));

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
                obList.add(inputField.getText());
                inputField.setText("");
                inputField.requestFocus();
                property1.set("Current Number Of Students In Queue: "
                        + obList.size());
                property2.set("Max Number Of Students In Queue: "
                        + obList.getMaxSize());
            });

        addButton.disableProperty()
                .bind(Bindings.createBooleanBinding(() ->
                                inputField.getText().trim().isEmpty(),
                        inputField.textProperty()));

        HBox entryBox = new HBox();
        entryBox.getChildren().addAll(inputField, addButton, dequeueButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(labelBox, listView, entryBox);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("CS 1331 Office Hours Queue");
        stage.show();
    }
}
