import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {
    private PhysicsComponent physics;
    private int jumps = 1;
    private int totalSpeed = 100;

    @Override
    public void onAdded() {
        physics.onGroundProperty().addListener((obs, old, isOnGround) -> {

            if (isOnGround) {
                jumps = 1;
            }
        });

    }

    public void move(int speed){
        totalSpeed += speed;
        physics.setVelocityX(totalSpeed);
    }

    public void fixMove() {
        physics.setVelocityX(totalSpeed);
    }

    public void jump() {
        if (jumps == 0)
            return;

        physics.setVelocityY(-750);

        jumps--;
    }


}
