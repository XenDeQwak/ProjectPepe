package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Components.BossComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import com.xen.rzlgame.rzl.Factories.HostilesFactory;
import com.xen.rzlgame.rzl.Factories.NPCFactory;
import com.xen.rzlgame.rzl.Factories.PlayerFactory;
import com.xen.rzlgame.rzl.Handlers.Collisions.BossAttackPlayerCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.BossPlayerCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerAttackBossCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.InputHandler;
import com.xen.rzlgame.rzl.Handlers.InteractionHandler;
import com.xen.rzlgame.rzl.Managers.SpawningManager;
import com.xen.rzlgame.rzl.UI.BossUIComponents;
import com.xen.rzlgame.rzl.UI.PlayerUIComponents;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    private final PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler();
    private Entity boss;
    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initGame() {
        initFactory();

        SpawningManager spawn = new SpawningManager();
        spawn.spawnAll();
        player = spawn.getPlayer();
        boss = spawn.getBoss();
        initPhysicsWorld();
        uiSetup();

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(ph).initInput(FXGL.getInput());
    }

    private void initFactory() {
        getGameWorld().addEntityFactory(new PlayerFactory());
        getGameWorld().addEntityFactory(new NPCFactory());
        getGameWorld().addEntityFactory(new HostilesFactory());
    }

    private void initPhysicsWorld() {
        FXGL.getPhysicsWorld().addCollisionHandler(ph);
        FXGL.getPhysicsWorld().setGravity(0, 0);
        FXGL.getPhysicsWorld().addCollisionHandler(new PlayerAttackBossCollisionHandler());
        FXGL.getPhysicsWorld().addCollisionHandler(new BossAttackPlayerCollisionHandler());
        FXGL.getPhysicsWorld().addCollisionHandler(new BossPlayerCollisionHandler());
    }

    private void uiSetup() {
        BossComponent bc = boss.getComponent(BossComponent.class);
        BossUIComponents bossUi = new BossUIComponents(bc);
        bc.setBossUi(bossUi);

        PlayerComponent pc = player.getComponent(PlayerComponent.class);
        PlayerUIComponents playerUi = new PlayerUIComponents(pc);
        pc.setPlayerUi(playerUi);

    }

    public static void main(String[] args) {
        launch(args);
    }


}