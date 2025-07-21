package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.entity.component.Component;
import com.xen.rzlgame.rzl.UI.EnemyUIComponents;

public class EnemyComponent extends Component {

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private EnemyUIComponents healthBar;

    @Override
    public void onAdded() {
        healthBar = new EnemyUIComponents(entity);
        healthBar.bossBar();
        healthBar.bossBarBorder();
    }

    public void setCurrentHealth(int value) {
        currentHealth = Math.max(0, value);
        healthBar.updateHealthBar();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}

