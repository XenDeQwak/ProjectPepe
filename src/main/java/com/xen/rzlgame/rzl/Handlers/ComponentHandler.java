package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.entity.GameWorld;
import com.xen.rzlgame.rzl.Factories.*;
import com.xen.rzlgame.rzl.Components.*;
import com.xen.rzlgame.rzl.Handlers.Collisions.BossAttackPlayerCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.BossPlayerCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerAttackBossCollisionHandler;
import com.xen.rzlgame.rzl.UI.*;

public class ComponentHandler {

    public static void initFactories(GameWorld world) {
        world.addEntityFactory(new PlayerFactory());
        world.addEntityFactory(new NPCFactory());
        world.addEntityFactory(new HostilesFactory());
        world.addEntityFactory(new ObjectFactory());
    }

    public static void initPhysicsWorld(PhysicsWorld physics) {
        physics.addCollisionHandler(new PlayerAttackBossCollisionHandler());
        physics.addCollisionHandler(new BossAttackPlayerCollisionHandler());
        physics.addCollisionHandler(new BossPlayerCollisionHandler());
        physics.setGravity(0, 1200);
    }

    public static void linkComponents(Entity player, Entity boss) {
        BossComponent bc = boss.getComponent(BossComponent.class);
        BossUIComponents bossUi = new BossUIComponents(bc);
        bc.setBossUi(bossUi);
        bc.setPlayer(player);

        PlayerComponent pc = player.getComponent(PlayerComponent.class);
        PlayerUIComponents playerUi = new PlayerUIComponents(pc);
        pc.setPlayerUi(playerUi);
    }
}
