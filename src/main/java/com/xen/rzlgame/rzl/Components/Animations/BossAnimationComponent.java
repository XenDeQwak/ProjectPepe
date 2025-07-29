package com.xen.rzlgame.rzl.Components.Animations;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.xen.rzlgame.rzl.Components.BossComponent;
import javafx.animation.PauseTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class BossAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel bossAttack, bossWalk;
    private boolean isBlinking = false;

    public BossAnimationComponent() {

        bossAttack = new AnimationChannel(
                FXGL.image("bossAttack.png"),
                5,
                1000/5,
                200,
                Duration.seconds(1),
                0,
                4
        );
        bossWalk = new AnimationChannel(
                FXGL.image("bossWalk.png"),
                6,
                1200/6,
                200,
                Duration.seconds(1),
                0,
                5
        );

        texture = new AnimatedTexture(bossWalk);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        double dx = entity.getComponent(BossComponent.class).getPlayer().getX() - entity.getX();

        if (dx > 0) {
            texture.setScaleX(1);
            texture.setTranslateX(-30);
        }
        else if (dx < 0) {
            texture.setScaleX(-1);
            texture.setTranslateX(-70);
        }
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

    public void playAttack() {
        texture.playAnimationChannel(bossAttack);
    }

    public void loopWalk() {
        texture.loopAnimationChannel(bossWalk);
    }
}
