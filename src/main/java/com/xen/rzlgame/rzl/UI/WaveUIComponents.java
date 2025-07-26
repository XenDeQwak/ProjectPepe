package com.xen.rzlgame.rzl.UI;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import com.xen.rzlgame.rzl.Handlers.WaveHandler;

public class WaveUIComponents {

    private final WaveHandler wave;
    private final Text waveCount;
    private int lastWave = -1;

    public WaveUIComponents(WaveHandler wave) {
        this.wave = wave;
        waveCount = new Text();
        waveCount.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        waveCount.setX(FXGL.getAppWidth() / 2.0);
        waveCount.setY(30);
        FXGL.getGameScene().addUINode(waveCount);
        updateWave();
    }

    public void updateWave() {
        int currentWave = wave.getWave();
        if (currentWave != lastWave) {
            waveCount.setText("Wave " + currentWave);
            lastWave = currentWave;
        }
    }
}
