package com.xen.rzlgame.rzl;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Factories.EntityType;
import com.xen.rzlgame.rzl.Handlers.*;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import com.xen.rzlgame.rzl.Handlers.WaveHandler;
import com.xen.rzlgame.rzl.UI.WaveUIComponents;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class HelloApplication extends GameApplication {

    private final PlayerNPCCollisionHandler ph = new PlayerNPCCollisionHandler();
    private WaveHandler wave;
    private WaveUIComponents waveUi;
    private Entity player;
    private boolean bossSpawned = false;


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        ComponentHandler.initFactories(getGameWorld());
    }


    @Override
    protected void initGame() {

        loopBGM("bgm1.mp3");
        setLevelFromMap("main_level.tmx");

        SpawningHandler spawn = new SpawningHandler();
        spawn.spawnAll();
        player = spawn.getPlayer();

        wave = new WaveHandler(player);
        ComponentHandler.linkPlayerComponents(player);

        getGameScene().getViewport().setBounds(0, 0, 2400, 600);
        getGameScene().getViewport().bindToEntity(player, 400, 300);

        new InputHandler(player).initInput(FXGL.getInput());
        new InteractionHandler(ph, wave).initInput(FXGL.getInput());
    }


    @Override
    protected void initPhysics() {
        ComponentHandler.initPhysicsWorld(getPhysicsWorld());
        getPhysicsWorld().addCollisionHandler(ph);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onUpdate(double tpf) {
        if (wave.getWave() > 0) {
            if (waveUi == null)
                waveUi = new WaveUIComponents(wave);
            ComponentHandler.linkWaveComponents(wave, waveUi);
            if (wave.getWave() == 3 && !bossSpawned) {
                Entity boss = FXGL.spawn("Boss");
                ComponentHandler.linkBossComponents(player, boss);
                bossSpawned = true;
                getAudioPlayer().stopMusic(FXGL.getAssetLoader().loadMusic("bgm1.mp3"));
                loopBGM("bgm2.mp3");

                if (getGameWorld().getEntitiesByType(EntityType.BOSS, EntityType.ENEMY).isEmpty() && wave.getWave() == 3)
                    FXGL.getDialogService().showMessageBox("You've beaten Padre Salvi!", () -> FXGL.getGameController().exit());
            }
        }
    }
}