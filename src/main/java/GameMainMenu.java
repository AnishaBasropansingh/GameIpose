import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Button;

public class GameMainMenu extends FXGLMenu {

    public GameMainMenu() {
        super(MenuType.MAIN_MENU);

        //roept createlayout aan met alles erin
        this.getContentRoot().getChildren().add(this.createLayout());

    }

    public BorderPane createLayout() {
        BorderPane pane = new BorderPane();
        Button btnStart = new Button("PlAY gAME");
        Button btnScoreboard = new Button("ScorebOARD");
        Button btnExit = new Button("EXiT!!");
        pane.setCenter(btnStart);

        //de game start nu op
        btnStart.setOnAction(e ->{
            this.fireNewGame();
        });

        return pane;

        // button die this.fireNewGame() aanroept
    }

}
