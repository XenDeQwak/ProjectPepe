package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Components.BossComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class BossPlayerCollisionHandler extends CollisionHandler {

    public BossPlayerCollisionHandler() {
        super(EntityType.BOSS, EntityType.PLAYER);
    }

    @Override
    protected void onCollisionBegin(Entity boss, Entity player) {
//        player.getComponent(PlayerComponent.class).setCurrentHealth(
//                player
//                    .getComponent(PlayerComponent.class)
//                    .getCurrentHealth() -
//                boss
//                    .getComponent(BossComponent.class)
//                    .getOnTouchDmg()
//        );
    }

}
