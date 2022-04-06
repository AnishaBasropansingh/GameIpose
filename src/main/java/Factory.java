import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.control.Button;

public class Factory implements EntityFactory {
 boe
    @Spawns("next")
    public Entity next(SpawnData data){
        return FXGL.entityBuilder(data)
            .view(new Button("Play Game"))
            .build();
    }

    @Spawns("scoreboard")
    public Entity scoreboard(SpawnData data){
        return FXGL.entityBuilder(data)
            .view(new Button("View Scoreboard"))
            .build();
    }

    @Spawns("exit")
    public Entity exit(SpawnData data){
        return FXGL.entityBuilder(data)
            .view(new Button("Exit Game"))
            .build();

    }

}
