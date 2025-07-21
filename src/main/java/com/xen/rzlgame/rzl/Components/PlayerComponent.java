package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

public class PlayerComponent extends Component {


    public void attack() {
        Entity atk = FXGL.spawn("attack", new SpawnData(
                getEntity().getCenter().getX() + 20,
                getEntity().getCenter().getY() + 5));
        System.out.println(getEntity().getCenter());

        FXGL.runOnce(atk::removeFromWorld, Duration.seconds(0.1));
    }


}
