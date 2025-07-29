package com.xen.rzlgame.rzl.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.xen.rzlgame.rzl.Components.BossComponent;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class BossUIComponents {

    private Rectangle healthBar;
    private Rectangle healthBarBorder;
    private final BossComponent boss;
    private Text bossName;

    public BossUIComponents(BossComponent bossComponent) {
        this.boss = bossComponent;
        bossBar();
        bossName();
        bossBarBorder();
    }

    private void bossBarBorder() {
        healthBarBorder = new Rectangle(600, 20);
        healthBarBorder.setTranslateX(100);
        healthBarBorder.setTranslateY(550);
        healthBarBorder.setArcHeight(20);
        healthBarBorder.setArcWidth(20);
        healthBarBorder.setStroke(Color.BLACK);
        healthBarBorder.setStrokeWidth(2);
        healthBarBorder.setFill(Color.TRANSPARENT);
        FXGL.getGameScene().addUINode(healthBarBorder);
    }

    private void bossName() {
        bossName = new Text("Padre Salvi");
        bossName.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        bossName.setFill(Color.WHITE);
        bossName.setTranslateX(100);
        bossName.setTranslateY(535);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setColor(Color.BLACK);
        bossName.setEffect(shadow);
        FXGL.getGameScene().addUINode(bossName);
    }

    private void bossBar() {
        healthBar = new Rectangle(600, 20, Color.DARKRED);
        healthBar.setTranslateX(100);
        healthBar.setTranslateY(550);
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
        FXGL.getGameScene().removeUINode(bossName);
    }
}
