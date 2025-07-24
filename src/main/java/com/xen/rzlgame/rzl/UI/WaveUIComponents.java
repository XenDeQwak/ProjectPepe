package com.xen.rzlgame.rzl.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.xen.rzlgame.rzl.Handlers.WaveHandler;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WaveUIComponents {

    private WaveHandler wave;

    public WaveUIComponents(WaveHandler wave) {
        this.wave = wave;
        initWaveUi();
    }

    public void initWaveUi() {
        Text waveCount = new Text("Wave " + wave.getWave());
        waveCount.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        waveCount.setX((double)FXGL.getAppWidth() / 2);
        waveCount.setY(30);
        FXGL.getGameScene().addUINode(waveCount);
    }
}
