package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class PlayerFollowComponent extends Component {
    private Entity player;

    public PlayerFollowComponent(Entity player) {
        this.player = player;
    }

    @Override
    public void onUpdate(double tpf) {
        entity.setPosition(player.getCenter().add(20, -3));
    }
}
