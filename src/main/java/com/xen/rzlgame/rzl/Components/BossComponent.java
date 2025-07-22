package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.core.collection.grid.Grid;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarCell;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.xen.rzlgame.rzl.Components.FollowComponent.BossFollowComponent;
import com.xen.rzlgame.rzl.UI.BossUIComponents;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BossComponent extends Component {

    private int bossAtkX = -60;
    private int bossAtkY = -20;
    private int maxHealth = 100;
    private int onTouchDmg = 30;
    private int currentHealth = maxHealth;
    private boolean canAttack = true;
    private BossUIComponents bossUI;
    private Entity player;

    public void bossAttack() {
        if (!canAttack) return;
        canAttack = false;

        List<String> patterns = List.of(
                "bossAttackA",
                "bossAttackB"
                //"bossAttackC"
        );

        int index = FXGLMath.random(0, patterns.size() - 1);
        String chosen = patterns.get(index);
        Entity bossAtk = FXGL.spawn(chosen);
        bossAtk.setPosition(entity.getCenter().add(bossAtkX, bossAtkY));
        bossAtk.addComponent(new BossFollowComponent(entity));

        FXGL.runOnce(bossAtk::removeFromWorld, Duration.seconds(0.3));
        FXGL.runOnce(() -> canAttack = true, Duration.seconds(3));
    }


    public void onDeath() {
        if (currentHealth <= 0) {
            entity.removeFromWorld();
            bossUI.healthBarGone();
        }
    }

    @Override
    public void onUpdate(double tpf) {
        bossAttack();
        onDeath();
        aStar();
    }

    public void aStar() {
        int playerCellX = (int)(player.getX() / 40);
        int playerCellY = (int)(player.getY() / 40);

        entity.getComponent(AStarMoveComponent.class).moveToCell(playerCellX, playerCellY);
        Point2D dir = player.getPosition().subtract(entity.getPosition()).normalize();
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(dir.multiply(150));

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

    public int getOnTouchDmg() { return onTouchDmg; }

    public void setPlayer(Entity player) {
        this.player = player;
    }
}

