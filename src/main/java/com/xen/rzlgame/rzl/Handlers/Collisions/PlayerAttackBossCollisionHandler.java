package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Components.AttackComponent;
import com.xen.rzlgame.rzl.Components.BossComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class PlayerAttackBossCollisionHandler extends CollisionHandler {

    public PlayerAttackBossCollisionHandler() {
        super(EntityType.PLAYER_ATTACK, EntityType.BOSS);
    }

    @Override
    protected void onCollisionBegin(Entity attack, Entity boss) {
        boss.getComponent(BossComponent.class).setCurrentHealth(
                boss
                        .getComponent(BossComponent.class)
                        .getCurrentHealth() -
                attack
                        .getComponent(AttackComponent.class)
                        .getPlayerDamage()
        );
        if (boss.getComponent(BossComponent.class).getCurrentHealth() <= 0){
            boss.removeFromWorld();
            boss.getComponent(BossComponent.class).getBossUI().healthBarGone();
        }
    }
}
