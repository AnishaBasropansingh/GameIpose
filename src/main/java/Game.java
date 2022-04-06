import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class Game extends GameApplication {

    private static Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(30 * 70);
        gameSettings.setHeight(20 * 70);
        gameSettings.setTitle("TestApp");
        gameSettings.setVersion("5.0");
    }


    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.W, "Move up", () -> getPlayer().getComponent(PlayerComponent.class).jump());

    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 800);

    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameFactory());
        FXGL.setLevelFromMap("testmap.tmx");
        getPlayer().getComponent(PlayerComponent.class).move(0);
        getGameScene().getViewport().bindToEntity(getPlayer(), 1050, 700 );
    }


    public static void main(String[] args) {
        launch(args);
    }
}
