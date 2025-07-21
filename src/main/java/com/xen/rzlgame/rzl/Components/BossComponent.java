package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.xen.rzlgame.rzl.Components.FollowComponent.BossFollowComponent;
import com.xen.rzlgame.rzl.UI.BossUIComponents;
import javafx.util.Duration;

public class BossComponent extends Component {

    private int bossAtkX = -60;
    private int bossAtkY = -20;
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private boolean canAttack = true;
    private BossUIComponents bossUI;

    public void bossAttack() {
        if(!canAttack) return;
        canAttack = false;
        Entity bossAtk = FXGL.spawn("bossAttack");
        bossAtk.setPosition(entity.getCenter().add(bossAtkX, bossAtkY));
        bossAtk.addComponent(new BossFollowComponent(entity));

        FXGL.runOnce(bossAtk::removeFromWorld, Duration.seconds(0.3));
        FXGL.runOnce(() -> canAttack = true, Duration.seconds(2.5));
    }

    @Override
    public void onUpdate(double tpf) {
        bossAttack();
    }

    public void setBossUi(BossUIComponents ui) {
        this.bossUI = ui;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.max(0, currentHealth);
        bossUI.updateHealthBar();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getBossAtkX() {
        return bossAtkX;
    }

    public int getBossAtkY() {
        return bossAtkY;
    }

    public BossUIComponents getBossUI() {
        return bossUI;
    }
}

