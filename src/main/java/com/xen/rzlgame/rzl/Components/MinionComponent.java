package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

public class MinionComponent extends Component {

    private Entity player;
    private int onTouchDmg = 10;
    private int currentHealth = 20;

    public void followPlayer() {
        double dx = player.getX() - entity.getX();
        double vx = Math.signum(dx) * 150;
        double vy = entity.getComponent(PhysicsComponent.class).getLinearVelocity().getY();
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(new Point2D(vx, vy));
    }

    public void onDeath() {
        if (currentHealth <= 0) entity.removeFromWorld();
    }

    @Override
    public void onUpdate(double tpf) {
        followPlayer();
        onDeath();
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public int getOnTouchDmg() {
        return onTouchDmg;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
