package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Factories.NPCFactory;
import com.xen.rzlgame.rzl.Factories.PlayerFactory;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.InputHandler;
import com.xen.rzlgame.rzl.Handlers.InteractionHandler;
import com.xen.rzlgame.rzl.Managers.SpawningManager;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    Entity player;
    Entity[] npcs;

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

        PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler(player);
        ph.initPhysics(FXGL.getPhysicsWorld());

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(player, ph).initInput(FXGL.getInput());
    }

    private void initFactory() {
        getGameWorld().addEntityFactory(new PlayerFactory());
        getGameWorld().addEntityFactory(new NPCFactory());
    }

    public static void main(String[] args) {
        launch(args);
    }


}