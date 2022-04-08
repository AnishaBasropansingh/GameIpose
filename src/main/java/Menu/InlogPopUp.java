package Menu;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InlogPopUp {
    public static String text;

    public String getText() {
        System.out.println(text);
        return text;
    }

    public void start() {
        Stage stage = new Stage();

        // labels
        Text enterUsernameText = new Text("Vul in je naam");

        // input fields
        TextField usernameField = new TextField();

        Button submitButton = new Button("Submit");


        submitButton.setOnAction(e -> {
            text = usernameField.getText();
            System.out.println(text);
            stage.close();
        });

        FlowPane flowpane = new FlowPane();

        flowpane.setMinSize(400, 200);

        //padding
        flowpane.setPadding(new Insets(10, 10, 10, 10));

        //Spacing tussen de columns
        flowpane.setVgap(5);
        flowpane.setHgap(5);

        //input fields vertical
        flowpane.setOrientation(Orientation.VERTICAL);

        //alles toevoegen aan de flowpane
        flowpane.getChildren().addAll(enterUsernameText, usernameField, submitButton);
        BorderPane root = new BorderPane(flowpane);
        Scene scene = new Scene(root, 400, 200);

        stage.setScene(scene);
        stage.showAndWait();
    }
}

