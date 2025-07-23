package com.xen.rzlgame.rzl.Managers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Components.MinionComponent;
import com.xen.rzlgame.rzl.Factories.EntityType;
import javafx.util.Duration;

public class WaveManager {
    private int wave = 0;
    private final int[] numEnemies = {10, 15, 20};
    private boolean waveHasSpawned = false;
    private boolean waveIsSpawning = false;
    private boolean waveBreakActive = false;
    private final Entity player;

    public WaveManager(Entity player) {
        this.player = player;
        FXGL.run(this::updateState, Duration.seconds(1));
    }

    public void updateState() {
        if (!waveHasSpawned && !waveIsSpawning && !waveBreakActive && wave < numEnemies.length) {
            waveHasSpawned = true;
            waveIsSpawning = true;
            spawnWave();
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
            FXGL.runOnce(() -> waveBreakActive = false, Duration.seconds(20));
        }
    }

    private void spawnWave() {
        int count = numEnemies[wave];
        double playerX = player.getX();
        double playerY = player.getY();

        for (int i = 0; i < count; i++) {
            final int index = i;
            FXGL.runOnce(() -> {
                int ring = index / 8;
                int posInRing = index % 8;

                double angle = (2 * Math.PI * posInRing) / 8;
                double radius = 60 + (ring * 40);

                double spawnX = playerX + Math.cos(angle) * radius;
                double spawnY = playerY + Math.sin(angle) * radius;

                Entity minion = FXGL.spawn("minion", spawnX, spawnY);
                minion.getComponent(MinionComponent.class).setPlayer(player);
            }, Duration.seconds(i + 1D));
        }
    }

    public boolean areEnemiesDead() {
        return FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY).isEmpty();
    }

    public boolean hasWaveSpawned() {
        return waveHasSpawned;
    }

    public int getWave() {
        return wave;
    }
}
