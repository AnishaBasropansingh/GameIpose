import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class GameFactory implements EntityFactory {

    HealthIntComponent health;

    @Spawns("player")
    public Entity spawnPlayer(SpawnData data){
        health = new HealthIntComponent(3);
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(48, 48), BoundingShape.box(6, 8)));
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));


        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox("cart.gif")
                .with(physics)
                .with(health)
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .build();
    }

    public void damage(int dmg){
        this.health.damage(dmg);
        System.out.println(health);
    }

    @Spawns("enemy")
    public Entity spawnEnemy(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef enemy = new BodyDef();
        enemy.setFixedRotation(true);
        enemy.setType(BodyType.DYNAMIC);
        physics.setBodyDef(enemy);
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));

        return FXGL.entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(new Rectangle(20, 20, Color.BLUE))
                .with(physics)
                .with(new EnemyComponent())
                .collidable()
                .build();
    }



    @Spawns("platform")
    public Entity spawnWall(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityType.PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("gold")
    public Entity coins(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.GOLD)
                .viewWithBBox(new Circle(10, 10, 10, Color.GOLD))
                .with(new CollidableComponent(true))
                .build();
    }

}
