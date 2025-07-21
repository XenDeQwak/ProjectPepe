package com.xen.rzlgame.rzl.UI;

import com.almasb.fxgl.entity.Entity;
import com.xen.rzlgame.rzl.Components.EnemyComponent;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyUIComponents {

    private Entity entity;
    private Rectangle healthBar;

    public EnemyUIComponents(Entity entity) {
        this.entity = entity;
    }

    public void bossBarBorder() {
        Rectangle healthBarBorder = new Rectangle(600, 20);
        healthBarBorder.setTranslateY(200);
        healthBarBorder.setTranslateX(-300);
        healthBarBorder.setArcHeight(20);
        healthBarBorder.setArcWidth(20);
        healthBarBorder.setStroke(Color.BLACK);
        healthBarBorder.setStrokeWidth(2);
        healthBarBorder.setFill(Color.TRANSPARENT);
        entity.getViewComponent().addChild(healthBarBorder);
    }
    public void bossBar() {
        healthBar = new Rectangle(600, 20, Color.GREEN);
        healthBar.setTranslateY(200);
        healthBar.setTranslateX(-300);
        healthBar.setArcHeight(20);
        healthBar.setArcWidth(20);
        entity.getViewComponent().addChild(healthBar);
    }

    public void updateHealthBar() {
        int health = entity.getComponent(EnemyComponent.class).getCurrentHealth();
        int maxHealth = entity.getComponent(EnemyComponent.class).getMaxHealth();
        double percent = health / (double) maxHealth;
        healthBar.setWidth(600 * percent);
    }
}
