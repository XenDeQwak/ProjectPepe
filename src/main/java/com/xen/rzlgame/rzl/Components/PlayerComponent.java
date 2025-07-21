package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.xen.rzlgame.rzl.Components.FollowComponent.PlayerFollowComponent;
import com.xen.rzlgame.rzl.UI.PlayerUIComponents;
import javafx.util.Duration;

public class PlayerComponent extends Component {

    private boolean canAttack = true;
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private PlayerUIComponents playerUi;

    public void attack() {
        if (!canAttack) return;
        canAttack = false;

        Entity atk = FXGL.spawn("attack");
        atk.addComponent(new PlayerFollowComponent(getEntity()));

        FXGL.runOnce(atk::removeFromWorld, Duration.seconds(0.3));
        FXGL.runOnce(() -> canAttack = true, Duration.seconds(0.5));
    }

    public void onDeath() {
        if (currentHealth <= 0) {
            entity.removeFromWorld();
            playerUi.healthBarGone();
        }
    }

    @Override
    public void onUpdate(double tpf) {
        onDeath();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.max(0, currentHealth);
        playerUi.updateHealthBar();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public PlayerUIComponents getPlayerUi() {
        return playerUi;
    }

    public void setPlayerUi(PlayerUIComponents playerUi) {
        this.playerUi = playerUi;
    }
}
