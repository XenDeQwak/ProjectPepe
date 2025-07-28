package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class PlayerAnimationComponent extends Component {

    private final AnimatedTexture texture;
    private final AnimationChannel playerWalk;
    private final AnimationChannel playerIdle;
    private final AnimationChannel playerJump;
    private final AnimationChannel playerAttack;

    public PlayerAnimationComponent() {
       playerWalk = new AnimationChannel(
               FXGL.image("playerWalk.png"),
               8,
               258/8,
               25,
               Duration.seconds(1),
               0,
               7
       );
       playerIdle = new AnimationChannel(
               FXGL.image("playerWalk.png"),
               8,
               258/8,
               25,
               Duration.seconds(1),
               7,
               7
       );
       playerJump = new AnimationChannel(
               FXGL.image("playerJump.png"),
               6,
               184/6,
               26,
               Duration.seconds(1),
               1,
               5
       );
       playerAttack = new AnimationChannel(
               FXGL.image("playerAttack.png"),
               8,
               (int)32.25,
               25,
               Duration.seconds(0.5),
               0,
               7
       );

       texture = new AnimatedTexture(playerIdle);
    }

    @Override
    public void onAdded() {
        texture.setScaleX(2);
        texture.setScaleY(2);
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);
        double dx = physics.getVelocityX();

        if (dx > 0) texture.setScaleX(2);
        else if (dx < 0) texture.setScaleX(-2);

        if (!entity.getComponent(PlayerComponent.class).isCanAttack()) {
            if (texture.getAnimationChannel() != playerAttack)
                texture.playAnimationChannel(playerAttack);
        } else if (!physics.isOnGround()) {
            if (texture.getAnimationChannel() != playerJump)
                texture.playAnimationChannel(playerJump);
        } else if (physics.isMoving()) {
            if (texture.getAnimationChannel() != playerWalk)
                texture.loopAnimationChannel(playerWalk);
        } else {
            if (texture.getAnimationChannel() != playerIdle)
                texture.loopAnimationChannel(playerIdle);
        }


    }
}
