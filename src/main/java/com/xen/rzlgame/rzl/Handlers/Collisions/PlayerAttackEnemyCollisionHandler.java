package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Components.Animations.MinionAnimationComponent;
import com.xen.rzlgame.rzl.Components.AttackComponent;
import com.xen.rzlgame.rzl.Components.MinionComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class PlayerAttackEnemyCollisionHandler extends CollisionHandler {

    public PlayerAttackEnemyCollisionHandler() {
        super(EntityType.PLAYER_ATTACK, EntityType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity attack, Entity enemy) {
        enemy.getComponent(MinionComponent.class).setCurrentHealth(
                enemy
                        .getComponent(MinionComponent.class)
                        .getCurrentHealth() -
                        attack
                                .getComponent(AttackComponent.class)
                                .getPlayerDamage()
        );
        enemy.getComponent(MinionAnimationComponent.class).blinkRed();
    }
}
