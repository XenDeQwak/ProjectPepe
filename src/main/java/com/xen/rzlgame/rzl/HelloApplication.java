package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Handlers.*;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.WaveHandler;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    private final PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initGame() {
        Entity boss;
        Entity player;
        WaveHandler wave;

        ComponentHandler.initFactories(getGameWorld());
        SpawningHandler spawn = new SpawningHandler();
        spawn.spawnAll();
        player = spawn.getPlayer();
        boss = spawn.getBoss();
        wave = new WaveHandler(player);

        ComponentHandler.initPhysicsWorld(getPhysicsWorld());
        getPhysicsWorld().addCollisionHandler(ph);

        if (boss != null) //testing
            ComponentHandler.linkBossComponents(player, boss);
        ComponentHandler.linkPlayerComponents(player);

        getGameScene().getViewport().setBounds(0, 0, 2400, 600);
        getGameScene().getViewport().bindToEntity(player, 400, 300);

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(ph, wave).initInput(FXGL.getInput());
    }

    public static void main(String[] args) {
        launch(args);
    }


}