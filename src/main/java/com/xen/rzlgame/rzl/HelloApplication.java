package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.ComponentHandler;
import com.xen.rzlgame.rzl.Handlers.InputHandler;
import com.xen.rzlgame.rzl.Handlers.InteractionHandler;
import com.xen.rzlgame.rzl.Managers.SpawningManager;
import com.xen.rzlgame.rzl.Managers.WaveManager;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    private final PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler();
    private WaveManager wave;

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

        ComponentHandler.initFactories(getGameWorld());
        SpawningManager spawn = new SpawningManager();
        spawn.spawnAll();
        player = spawn.getPlayer();
        boss = spawn.getBoss();
        wave = new WaveManager(player);


        ComponentHandler.initPhysicsWorld(getPhysicsWorld());

        if (boss != null) //testing
            ComponentHandler.linkBossComponents(player, boss);
        ComponentHandler.linkPlayerComponents(player);

        getGameScene().getViewport().setBounds(0, 0, 2400, 600);
        getGameScene().getViewport().bindToEntity(player, 400, 300);

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(ph).initInput(FXGL.getInput());
    }

    public static void main(String[] args) {
        launch(args);
    }


}