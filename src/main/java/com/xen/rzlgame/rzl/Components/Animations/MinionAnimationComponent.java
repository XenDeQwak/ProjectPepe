package com.xen.rzlgame.rzl.Components.Animations;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.xen.rzlgame.rzl.Components.BossComponent;
import com.xen.rzlgame.rzl.Components.MinionComponent;
import javafx.util.Duration;

public class MinionAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel minionWalk;

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

        texture = new AnimatedTexture(minionWalk);
    }

    @Override
    public void onAdded() {
        texture.setScaleX(2);
        texture.setScaleY(2);
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        double dx = entity.getComponent(MinionComponent.class).getPlayer().getX() - entity.getX();
        if (dx > 0) texture.setScaleX(2);
        else if (dx < 0) texture.setScaleX(-2);
    }

    public void loopWalk() {
        if (texture.getAnimationChannel() != minionWalk)
            texture.loopAnimationChannel(minionWalk);
    }
}
