package com.xen.rzlgame.rzl.Managers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

public class WaveManager {

    private int wave = 0;
    private int[] numEnemies = {10, 15, 20};
    private boolean hasSpawned = false;
    private Entity player;

    public WaveManager(Entity player) {this.player = player;}


    public void spawnMinion() {
        for (int i = 0; i <= wave; i++) {
            for (int j = 0; j < numEnemies.length; j++) {
                if (!hasSpawned) {
                    int index = i * numEnemies.length + j;
                    FXGL.runOnce(() -> {
                        FXGL.spawn("minion");
                        hasSpawned = true;
                    }, Duration.seconds(index * 5D));
                }
            }
        }

    }
}
