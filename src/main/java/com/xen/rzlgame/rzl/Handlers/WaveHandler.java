package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Components.MinionComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class WaveHandler {
    private int wave = 0;
    private final int[] numEnemies = {10, 15, 20};
    private boolean waveHasSpawned = false;
    private boolean waveIsSpawning = false;
    private boolean waveBreakActive = false;
    private boolean isCutsceneActive = true;
    private final Entity player;

    public WaveHandler(Entity player) {
        this.player = player;
        FXGL.run(this::updateState, Duration.seconds(1));
    }

    public void updateState() {
        if (isCutsceneActive) return;

        if (!waveHasSpawned && !waveIsSpawning && !waveBreakActive && wave < numEnemies.length) {
            waveHasSpawned = true;
            waveIsSpawning = true;
            //spawnWave();
            wave++;
        }
        if (areEnemiesDead() && waveHasSpawned && !waveIsSpawning && !waveBreakActive) {
            waveHasSpawned = false;
            startWaveBreak();
        }
    }

    private void startWaveBreak() {
        if (wave < numEnemies.length) {
            waveBreakActive = true;
            FXGL.runOnce(() -> waveBreakActive = false, Duration.seconds(2));
        }
    }

    private void spawnWave() {
        int count = numEnemies[wave];

        for (int i = 0; i < count; i++) {
            FXGL.runOnce(() -> {
                Entity minion = FXGL.spawn("minion", spawnNearPlayer());
                minion.getComponent(MinionComponent.class).setPlayer(player);
            }, Duration.seconds(i + 1D));
        }
        FXGL.runOnce(() -> waveIsSpawning = false, Duration.seconds(count));
    }

    private Point2D spawnNearPlayer() {
        int ring = 1;
        int posInRing = 1 % 8;

        double angle = (2 * Math.PI * posInRing) / 8;
        double radius = (double) 20 + (ring * 20);

        double playerX = player.getX() + player.getWidth() / 2;

        double spawnX = playerX + Math.cos(angle) * radius;
        return new Point2D(spawnX, 500);
    }

    public boolean areEnemiesDead() {
        return FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY).isEmpty();
    }

    public void setCutsceneActive(boolean cutsceneActive) {
        isCutsceneActive = cutsceneActive;
    }

    public int getWave() {
        return wave;
    }

}
