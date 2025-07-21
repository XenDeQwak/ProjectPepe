package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.xen.rzlgame.rzl.Components.NpcIdComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class PlayerNPCCollisionHandler {

    private Entity player;
    private Entity currentNpc = null;

    public PlayerNPCCollisionHandler(Entity player) {
        this.player = player;
    }

    public void initPhysics(PhysicsWorld physicsWorld) {
        physicsWorld.setGravity(0, 0);
        physicsWorld.addCollisionHandler(new com.almasb.fxgl.physics.CollisionHandler(player.getType(), EntityType.NPC) {
            @Override
            protected void onCollisionBegin(Entity player, Entity npc) {
                currentNpc = npc;
            }

            @Override
            protected void onCollisionEnd(Entity player, Entity npc) {
                if (currentNpc == npc)
                    currentNpc = null;
            }
        });
    }

    public String getCurrentNpc() {
        if (currentNpc == null) return "";
        return currentNpc.getComponent(NpcIdComponent.class).getId();
    }
}
