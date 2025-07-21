package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Factories.HostilesFactory;
import com.xen.rzlgame.rzl.Factories.NPCFactory;
import com.xen.rzlgame.rzl.Factories.PlayerFactory;
import com.xen.rzlgame.rzl.Handlers.Collisions.AttackEnemyCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.InputHandler;
import com.xen.rzlgame.rzl.Handlers.InteractionHandler;
import com.xen.rzlgame.rzl.Managers.SpawningManager;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    Entity player;
    Entity[] npcs;
    Entity enemy;

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
        npcs = spawn.getAllNPCs();
        enemy = spawn.getEnemy();

        PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler();

        FXGL.getPhysicsWorld().addCollisionHandler(ph);
        FXGL.getPhysicsWorld().setGravity(0, 0);

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(ph).initInput(FXGL.getInput());
    }

    private void initFactory() {
        getGameWorld().addEntityFactory(new PlayerFactory());
        getGameWorld().addEntityFactory(new NPCFactory());
        getGameWorld().addEntityFactory(new HostilesFactory());
    }

    public static void main(String[] args) {
        launch(args);
    }


}