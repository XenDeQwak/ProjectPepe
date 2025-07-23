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
import com.xen.rzlgame.rzl.Factories.ObjectFactory;
import com.xen.rzlgame.rzl.Factories.PlayerFactory;
import com.xen.rzlgame.rzl.Handlers.Collisions.BossAttackPlayerCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.BossPlayerCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerAttackBossCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.ComponentHandler;
import com.xen.rzlgame.rzl.Handlers.InputHandler;
import com.xen.rzlgame.rzl.Handlers.InteractionHandler;
import com.xen.rzlgame.rzl.Managers.SpawningManager;
import com.xen.rzlgame.rzl.Managers.WaveManager;
import com.xen.rzlgame.rzl.UI.BossUIComponents;
import com.xen.rzlgame.rzl.UI.PlayerUIComponents;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    private final PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler();
    private Entity boss;
    private Entity player;
    private WaveManager wave;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initGame() {
        ComponentHandler.initFactories(getGameWorld());
        SpawningManager spawn = new SpawningManager();
        spawn.spawnAll();
        player = spawn.getPlayer();
        boss = spawn.getBoss();
        wave = new WaveManager(player);

        ComponentHandler.initPhysicsWorld(getPhysicsWorld());
        if (boss != null)
            ComponentHandler.linkComponents(player, boss);

        getGameScene().getViewport().setBounds(0, 0, 3200, 600);
        getGameScene().getViewport().bindToEntity(player, 400, 300);

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(ph).initInput(FXGL.getInput());
    }

    @Override
    public void onUpdate(double tpf) {
        wave.spawnMinion();
    }

    public static void main(String[] args) {
        launch(args);
    }


}