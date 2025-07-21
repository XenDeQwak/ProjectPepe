package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class AttackEnemyCollisionHandler extends CollisionHandler {

    public AttackEnemyCollisionHandler() {
        super(EntityType.ATTACK, EntityType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity attack, Entity enemy) {
        System.out.println("I HIT THIS");
    }
}
