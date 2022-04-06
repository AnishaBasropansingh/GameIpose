import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.event.ActionEvent;

public class Game extends GameApplication {


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("naamloos.txm");
        settings.setVersion("1.0");

        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            public FXGLMenu newMainMenu() {
                return new GameMainMenu();
            }
        });
    }

    @Override
    protected void initGame(){

        FXGL.getGameWorld().addEntityFactory(new Factory());
        Entity next = FXGL.spawn("next", 500, 300);
        Entity scoreboard = FXGL.spawn("scoreboard", 500, 400);
        Entity exit = FXGL.spawn("exit", 500, 500);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
