package com.xen.rzlgame.rzl.Components.FollowComponent;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.xen.rzlgame.rzl.Components.BossComponent;

public class BossFollowComponent extends Component {
    private Entity boss;
    private BossComponent bc;

    public BossFollowComponent(Entity boss) {
        this.boss = boss;
        bc = boss.getComponent(BossComponent.class);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.setPosition(boss.getCenter().add(bc.getBossAtkX(), bc.getBossAtkY()));
    }
}
