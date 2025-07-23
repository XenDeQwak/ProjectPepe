package com.xen.rzlgame.rzl.Handlers.Collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.xen.rzlgame.rzl.Components.MinionComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class PlayerEnemyCollisionHandler extends CollisionHandler {

    public PlayerEnemyCollisionHandler() {
        super(EntityType.PLAYER, EntityType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity player, Entity enemy) {
        player.getComponent(PlayerComponent.class).setCurrentHealth(
                player
                        .getComponent(PlayerComponent.class)
                        .getCurrentHealth() -
                enemy
                        .getComponent(MinionComponent.class)
                        .getOnTouchDmg()
        );
    }
}
