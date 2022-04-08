package Menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;

import java.lang.management.BufferPoolMXBean;

public class StartMenu extends FXGLMenu {
    public StartMenu(@NotNull MenuType type) {
        super(type);

        //alle FX + styling
        Label title = new Label("Infinity Rails");
        title.setStyle("-fx-text-fill: white;" +
                       "-fx-font-size: 50px;" +
                        "-fx-font-family: Impact");

        Button btnStart = new Button("Start Game");

        btnStart.setStyle("-fx-background-color: white;" +
                        "-fx-label-padding: 10px 30px;"  +
                        "-fx-font-size: 40px;"

        );

        Button btnExit = new Button("Close Game");
        btnExit.setStyle("-fx-background-color: white; " +
                         "-fx-label-padding: 10px 25px;"  +
                         "-fx-font-size: 40px;"

        );

        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);

        vBox.getChildren().addAll(title, btnStart, btnExit);

        pane.setMinHeight(FXGL.getAppHeight());
        pane.setMinWidth(FXGL.getAppWidth());

        pane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);

//        btnStart.setOnAction(e -> {
//            fireNewGame();
//        });

        btnStart.setOnAction(e ->{
            new Menu.InlogPopUp().start();
            fireNewGame();
        });

        btnExit.setOnAction(e -> {
            fireExit();
        });

        BackgroundImage mainBackground = new BackgroundImage(new Image("/assets/background.jpg", FXGL.getAppWidth(), FXGL.getAppHeight(), false, true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
            );

        getContentRoot().setBackground(new Background(mainBackground));
        super.getContentRoot().getChildren().add(pane);
    }
}
