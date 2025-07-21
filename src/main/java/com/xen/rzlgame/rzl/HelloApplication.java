package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Factories.NPCFactory;
import com.xen.rzlgame.rzl.Factories.PlayerFactory;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    Entity player;
    Entity npcJames;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initGame() {
        initFactory();
        player = spawn("player");
        npcJames = spawn("James");
        new InputHandler(player).initInput(FXGL.getInput());
        new PhysicsHandler(player, npcJames).initPhysics(FXGL.getPhysicsWorld());
    }

    private void initFactory() {
        getGameWorld().addEntityFactory(new PlayerFactory());
        getGameWorld().addEntityFactory(new NPCFactory());
    }

    public static void main(String[] args) {
        launch(args);
    }
}