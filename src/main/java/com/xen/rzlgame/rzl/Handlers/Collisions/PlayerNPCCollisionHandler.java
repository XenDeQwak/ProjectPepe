package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.xen.rzlgame.rzl.Components.NpcIdComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class PlayerNPCCollisionHandler extends CollisionHandler {

    private Entity currentNpc = null;

    public PlayerNPCCollisionHandler() {
        super(EntityType.PLAYER, EntityType.NPC);
    }
        @Override
        protected void onCollisionBegin(Entity player, Entity npc) {
            currentNpc = npc;
            System.out.println(currentNpc);
        }

        @Override
        protected void onCollisionEnd(Entity player, Entity npc) {
            if (currentNpc == npc)
                currentNpc = null;
        }

    public String getCurrentNpc() {
        if (currentNpc == null) return "";
        return currentNpc.getComponent(NpcIdComponent.class).getId();
    }
}
