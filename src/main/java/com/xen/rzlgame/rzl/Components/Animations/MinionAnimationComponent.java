package com.xen.rzlgame.rzl.Components.Animations;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.xen.rzlgame.rzl.Components.MinionComponent;
import javafx.animation.PauseTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class MinionAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel minionWalk, minionAttack;
    private boolean isBlinking = false;

    public MinionAnimationComponent() {
        minionWalk = new AnimationChannel(
                FXGL.image("minionWalk.png"),
                6,
                288/6,
                48,
                Duration.seconds(1),
                0,
                5
        );
        minionAttack = new AnimationChannel(
                FXGL.image("minionAttack.png"),
                6,
                288/6,
                48,
                Duration.seconds(0.2),
                0,
                5
        );


        texture = new AnimatedTexture(minionWalk);
    }

    @Override
    public void onAdded() {
        texture.setScaleX(2);
        texture.setScaleY(2);
        entity.getViewComponent().addChild(texture);
        texture.loop();
    }

    public void blinkRed() {
        if (isBlinking)
            return;

        isBlinking = true;

        ColorAdjust redEffect = new ColorAdjust();
        redEffect.setHue(-0.45);
        redEffect.setSaturation(0.5);
        redEffect.setBrightness(0.1);
        texture.setEffect(redEffect);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.15));
        pause.setOnFinished(e -> {
            texture.setEffect(null);
            isBlinking = false;
        });
        pause.play();
    }

    @Override
    public void onUpdate(double tpf) {

        double dx = entity.getComponent(MinionComponent.class).getPlayer().getX() - entity.getX();
        if (dx > 0) texture.setScaleX(2);
        else if (dx < 0) texture.setScaleX(-2);
        if (entity.getComponent(MinionComponent.class).isInRange()) {
            if (texture.getAnimationChannel() != minionAttack)
                texture.playAnimationChannel(minionAttack);
        } else {
            if (texture.getAnimationChannel() != minionWalk)
                texture.loopAnimationChannel(minionWalk);
        }
    }

}
