package com.xen.rzlgame.rzl.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerUIComponents {

    private final PlayerComponent pc;
    private Rectangle healthBar;
    private Rectangle healthBarBorder;

    public PlayerUIComponents(PlayerComponent pc) {
        this.pc = pc;
        playerBar();
        playerBarBorder();
    }

    private void playerBarBorder() {
        healthBarBorder = new Rectangle(200, 20);
        healthBarBorder.setTranslateX(20);
        healthBarBorder.setTranslateY(20);
        healthBarBorder.setArcHeight(10);
        healthBarBorder.setArcWidth(10);
        healthBarBorder.setStroke(Color.BLACK);
        healthBarBorder.setStrokeWidth(2);
        healthBarBorder.setFill(Color.TRANSPARENT);
        FXGL.getGameScene().addUINode(healthBarBorder);
    }

    private void playerBar() {
        healthBar = new Rectangle(200, 20, Color.GREEN);
        healthBar.setTranslateX(20);
        healthBar.setTranslateY(20);
        healthBar.setArcHeight(10);
        healthBar.setArcWidth(10);
        FXGL.getGameScene().addUINode(healthBar);
    }

    public void updateHealthBar() {
        int health = pc.getCurrentHealth();
        int maxHealth = pc.getMaxHealth();
        double percent = health / (double) maxHealth;
        healthBar.setWidth(200 * percent);
    }

    public void healthBarGone() {
        FXGL.getGameScene().removeUINode(healthBar);
        FXGL.getGameScene().removeUINode(healthBarBorder);
    }
}
