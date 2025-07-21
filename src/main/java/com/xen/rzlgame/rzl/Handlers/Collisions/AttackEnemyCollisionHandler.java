package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Components.AttackComponent;
import com.xen.rzlgame.rzl.Components.EnemyComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class AttackEnemyCollisionHandler extends CollisionHandler {

    public AttackEnemyCollisionHandler() {
        super(EntityType.ATTACK, EntityType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity attack, Entity enemy) {
        enemy.getComponent(EnemyComponent.class).setCurrentHealth(
                enemy
                        .getComponent(EnemyComponent.class)
                        .getCurrentHealth() -
                attack
                        .getComponent(AttackComponent.class)
                        .getDamage()
        );
        if (enemy.getComponent(EnemyComponent.class).getCurrentHealth() <= 0) enemy.removeFromWorld();
    }
}
