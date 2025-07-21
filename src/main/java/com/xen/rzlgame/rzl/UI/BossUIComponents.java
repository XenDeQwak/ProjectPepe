package com.xen.rzlgame.rzl.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.xen.rzlgame.rzl.Components.BossComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BossUIComponents {

    private Rectangle healthBar;
    private Rectangle healthBarBorder;
    private final BossComponent boss;

    public BossUIComponents(BossComponent bossComponent) {
        this.boss = bossComponent;
        bossBar();
        bossBarBorder();
    }

    private void bossBarBorder() {
        healthBarBorder = new Rectangle(600, 20);
        healthBarBorder.setTranslateX(100);
        healthBarBorder.setTranslateY(500);
        healthBarBorder.setArcHeight(20);
        healthBarBorder.setArcWidth(20);
        healthBarBorder.setStroke(Color.BLACK);
        healthBarBorder.setStrokeWidth(2);
        healthBarBorder.setFill(Color.TRANSPARENT);
        FXGL.getGameScene().addUINode(healthBarBorder);
    }

    private void bossBar() {
        healthBar = new Rectangle(600, 20, Color.DARKRED);
        healthBar.setTranslateX(100);
        healthBar.setTranslateY(500);
        healthBar.setArcHeight(20);
        healthBar.setArcWidth(20);

        FXGL.getGameScene().addUINode(healthBar);
    }

    public void updateHealthBar() {
        int health = boss.getCurrentHealth();
        int maxHealth = boss.getMaxHealth();
        double percent = health / (double) maxHealth;
        healthBar.setWidth(600 * percent);
    }

    public void healthBarGone() {
        FXGL.getGameScene().removeUINode(healthBar);
        FXGL.getGameScene().removeUINode(healthBarBorder);
    }
}
