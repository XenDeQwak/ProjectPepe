package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Components.AttackComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class BossAttackPlayerCollisionHandler extends CollisionHandler {

    public BossAttackPlayerCollisionHandler() {
        super(EntityType.BOSS_ATTACK, EntityType.PLAYER);
    }

    @Override
    protected void onCollisionBegin(Entity attack, Entity player) {
        player.getComponent(PlayerComponent.class).setCurrentHealth(
                player
                        .getComponent(PlayerComponent.class)
                        .getCurrentHealth() -
                attack
                        .getComponent(AttackComponent.class)
                        .getBossDamage()
        );
    }
}
