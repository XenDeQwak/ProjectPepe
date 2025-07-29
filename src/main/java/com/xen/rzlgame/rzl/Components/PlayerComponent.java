package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.xen.rzlgame.rzl.Components.AttackFollowComponent.PlayerFollowComponent;
import com.xen.rzlgame.rzl.UI.PlayerUIComponents;
import javafx.util.Duration;

public class PlayerComponent extends Component {

    private boolean canAttack = true;
    private boolean canParry = false;
    private boolean isGuarding = false;
    private int maxHealth = 125;
    private int currentHealth = maxHealth;
    private PlayerUIComponents playerUi;
    private boolean isParryOnCooldown = false;


    private int facingX;

    public void attack() {
        if (!canAttack) return;
        canAttack = false;

        Entity atk = FXGL.spawn("attack");
        atk.addComponent(new PlayerFollowComponent(entity, facingX));
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("sword.mp3"));
        FXGL.runOnce(atk::removeFromWorld, Duration.seconds(0.5));
        FXGL.runOnce(() -> canAttack = true, Duration.seconds(0.5));
    }

    public void parryStance() {
        if (isParryOnCooldown) return;

        canParry = true;
        canAttack = false;
        isParryOnCooldown = true;

        FXGL.runOnce(() -> {
            canAttack = true;
            canParry = false;
        }, Duration.seconds(0.3));

        FXGL.runOnce(() -> isParryOnCooldown = false, Duration.seconds(1.0));

        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("parry.mp3"));
    }


    public void onDeath() {
        if (currentHealth <= 0) {
            entity.removeFromWorld();
            playerUi.healthBarGone();
            FXGL.getGameController().pauseEngine();
            FXGL.getDialogService().showMessageBox("Game Over", () -> FXGL.getGameController().exit());
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

    public void jump() {
        if (entity.getComponent(PhysicsComponent.class).isOnGround()) {
            entity.getComponent(PhysicsComponent.class).setVelocityY(-500);
            FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("jump.mp3"));
        }

    }

    public boolean isCanParry() { return canParry;}

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setPlayerUi(PlayerUIComponents playerUi) {
        this.playerUi = playerUi;
    }

    public void setFacingX(int facingX) {
        this.facingX = facingX;
    }

    public boolean isCanAttack() {
        return canAttack;
    }
}
