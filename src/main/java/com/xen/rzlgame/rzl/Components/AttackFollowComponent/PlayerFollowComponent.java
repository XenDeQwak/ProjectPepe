package com.xen.rzlgame.rzl.Components.AttackFollowComponent;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class PlayerFollowComponent extends Component {
    private Entity player;
    private int facingX;

    public PlayerFollowComponent(Entity player, int facingX) {
        this.player = player;
        this.facingX = facingX;
    }

    @Override
    public void onUpdate(double tpf) {
        if (facingX > 0)
            entity.setPosition(player.getCenter().add(5, -3));
        else entity.setPosition(player.getCenter().add(-35, -3));
    }
}
