package com.xen.rzlgame.rzl;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;

public class PhysicsHandler {

    Entity player;
    Entity npc;

    public PhysicsHandler(Entity player, Entity npc) {
        this.player = player;
        this.npc = npc;
    }

    void initPhysics(PhysicsWorld physicsWorld) {
        physicsWorld.setGravity(0, 0);
        physicsWorld.addCollisionHandler(new CollisionHandler(player.getType(), npc.getType()) {
            @Override
            protected void onCollisionBegin(Entity player, Entity npc) {
                System.out.println("COLLISION");
            }
        });
    }
}
