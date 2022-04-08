import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;


public class EnemyComponent extends Component {
    private PhysicsComponent physics;


    public void enemyMove(){
        physics.setVelocityX(-250);
    }
}
