import Menu.InlogPopUp;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Game extends GameApplication {

    InlogPopUp userinput = new InlogPopUp();
    final String outputFilePath = "highscores.txt";
    private String score;

    private static Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }

    private static Entity getEnemy() {
        return FXGL.getGameWorld().getSingleton(EntityType.ENEMY);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(25 * 50 );
        gameSettings.setHeight(25 * 50);
        gameSettings.setTitle("TestApp");
        gameSettings.setVersion("5.0");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setSceneFactory(new Menu.UISceneFactory());
    }


    @Override
    protected void initInput() {
        FXGL.onKeyDown(KeyCode.SPACE, "Move up", () -> getPlayer().getComponent(PlayerComponent.class).jump());

    }

    @Override
    protected void initGameVars(Map<String, Object> vars){
        vars.put("lives", 3);
        vars.put("leveltime", 0.0);
        vars.put("stageColor", Color.GOLD);
        vars.put("healthcolor", Color.RED);
        vars.put("score", 0);
        vars.put("highscore", 0);
    }

    @Override
    protected void initUI(){
        Text liveCounter = FXGL.addText("", 100, 100);
        liveCounter.textProperty().bind(FXGL.getip("lives").asString("Lives: %d"));


        Text uiScore = new Text("score:");
        uiScore.setFont(Font.font(72));
        uiScore.setTranslateX(getAppWidth()  -200);
        uiScore.setTranslateY(50);
        uiScore.fillProperty().bind(getop("stageColor"));
        uiScore.textProperty().bind(getip("score").asString());
        addUINode(uiScore);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 1700);
        HashMap<String, String> map = new HashMap<String, String>();
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMY) {
            @Override
            protected void onCollision(Entity a, Entity b) {
                HealthIntComponent heal = a.getComponent(HealthIntComponent.class);

                if (heal.getValue() > 1){
                    heal.damage(1);
                    FXGL.inc("lives", -1);
                    System.out.println(heal);
                    b.removeFromWorld();
                }
                else{
                    System.out.println(userinput.getText());
                    map.put(userinput.getText(), Integer.toString(heal.getValue()));

                    try{
                        BufferedWriter bf = new BufferedWriter(new FileWriter(outputFilePath));
                        for (Map.Entry<String, String> entry :
                                map.entrySet()) {

                            // put key and value separated by a colon
                            bf.append(entry.getKey()).append(":").append(entry.getValue());
                            // new line
                            bf.newLine();
                        }
                        bf.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    FXGL.getGameController().gotoMainMenu();

                }
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.GOLD) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                FXGL.inc("score", +1);
            }
        });

    }
    @Override
    protected void onPreInit() {
        getSettings().setGlobalMusicVolume(100);
        loopBGM("mc.mp3");
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameFactory());
        FXGL.setLevelFromMap("level1.tmx");
        getGameWorld().spawn("enemy", getPlayer().getPosition().getX() + 500, 500);

        FXGL.getGameTimer().runAtInterval(() -> {
            getPlayer().getComponent(PlayerComponent.class).move(1);

            List<Entity> ents = FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY);

            if (ents.size() > 0) {
                for (int i = 0; i < ents.size(); i++) {
                    ents.get(i).getComponent(EnemyComponent.class).enemyMove();
                }
            }
            }, Duration.millis(75));

        FXGL.getGameTimer().runAtInterval(() ->{
            getGameWorld().spawn("enemy", getPlayer().getPosition().getX() + 800, 500);
                }, Duration.seconds(2));

        getPlayer().getComponent(PlayerComponent.class).move(0);
//        getGameScene().getViewport().setBounds(-1000000000, 0, 1000000000, getAppHeight());
        getGameScene().getViewport().bindToEntity(getPlayer(), getAppWidth() / 2.0, getAppHeight() / 2.0);

    }

    @Override
    protected void onUpdate(double tpf){
        FXGL.inc("leveltime", tpf);
        if(getPlayer().getPosition().getY() > FXGL.getAppHeight() + 250){
            FXGL.getGameController().gotoMainMenu();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
