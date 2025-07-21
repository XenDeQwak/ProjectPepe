package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

public class PlayerComponent extends Component {

    private boolean canAttack = true;

    public void attack() {
        if (!canAttack) return;
        canAttack = false;

        Entity atk = FXGL.spawn("attack");
        atk.addComponent(new PlayerFollowComponent(getEntity()));

        FXGL.runOnce(atk::removeFromWorld, Duration.seconds(0.3));
        FXGL.runOnce(() -> canAttack = true, Duration.seconds(0.5));
    }


}
