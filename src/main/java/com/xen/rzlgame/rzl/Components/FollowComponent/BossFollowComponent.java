package com.xen.rzlgame.rzl.Components.FollowComponent;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.xen.rzlgame.rzl.Components.BossComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;

public class BossFollowComponent extends Component {
    private final Entity boss;
    private final Entity player;

    public BossFollowComponent(Entity boss, Entity player) {
        this.boss = boss;
        this.player = player;
    }

    @Override
    public void onUpdate(double tpf) {
        if (player.getPosition().getX() > boss.getPosition().getX())
            entity.setPosition(boss.getCenter().add(20, -3));
        else
            entity.setPosition(boss.getCenter().add(-60, -3));
    }
}
