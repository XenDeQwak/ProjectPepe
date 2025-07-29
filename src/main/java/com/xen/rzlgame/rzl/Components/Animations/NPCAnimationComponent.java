package com.xen.rzlgame.rzl.Components.Animations;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.xen.rzlgame.rzl.Components.NpcComponent;
import javafx.util.Duration;

public class NPCAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel mariaIdle, eliasIdle;

    public NPCAnimationComponent() {
        FXGL.runOnce(() -> {
            mariaIdle = new AnimationChannel(
                    FXGL.image("mariaIdle.png"),
                    5,
                    410 / 5,
                    55,
                    Duration.seconds(1),
                    0,
                    4
            );
            eliasIdle = new AnimationChannel(
                    FXGL.image("eliasIdle.png"),
                    5,
                    410 / 5,
                    75,
                    Duration.seconds(1),
                    0,
                    4
            );

            if ("Maria Clara".equals(entity.getComponent(NpcComponent.class).getId())) texture = new AnimatedTexture(mariaIdle);
            else texture = new AnimatedTexture(eliasIdle);

            texture.setScaleX(1.5);
            texture.setScaleY(1.5);
            texture.setTranslateX(-25);
            entity.getViewComponent().addChild(texture);
            texture.loop();
        }, Duration.seconds(0));
    }

}
