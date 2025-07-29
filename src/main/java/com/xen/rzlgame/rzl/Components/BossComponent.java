package com.xen.rzlgame.rzl.Components;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.xen.rzlgame.rzl.Components.Animations.BossAnimationComponent;
import com.xen.rzlgame.rzl.Components.AttackFollowComponent.BossFollowComponent;
import com.xen.rzlgame.rzl.UI.BossUIComponents;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class BossComponent extends Component {

    private int maxHealth = 100;
    private int onTouchDmg = 30;
    private int currentHealth = maxHealth;
    private boolean canAttack = true;
    private BossUIComponents bossUI;
    private Entity player;
    private Entity bossAtk;

    public void bossAttack() {
        if (!canAttack) return;
        canAttack = false;

        FXGL.runOnce(()-> {
            bossAtk = FXGL.spawn("bossAttackA");
            bossAtk.addComponent(new BossFollowComponent(entity, player));
            FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("smash.mp3"));
            FXGL.runOnce(bossAtk::removeFromWorld, Duration.seconds(1));
        }, Duration.seconds(0.6));

        entity.getComponent(BossAnimationComponent.class).playAttack();
        FXGL.runOnce(() -> {
            if (entity != null && entity.isActive()) entity.getComponent(BossAnimationComponent.class).loopWalk();
        }, Duration.seconds(2));
        FXGL.runOnce(() -> canAttack = true, Duration.seconds(3));
    }

    public void onDeath() {
        if (currentHealth <= 0) {
            entity.removeFromWorld();
            bossUI.healthBarGone();
            FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("padre_death.mp3"));
        }
    }

    @Override
    public void onUpdate(double tpf) {
        if (canAttack && isInRange()) bossAttack();
        onDeath();
        if (canAttack) followPlayer();
    }

    private boolean isInRange() {
        double distance = entity.getCenter().distance(player.getCenter());
        return distance <= 125;
    }


    public void followPlayer() {
        double dx = player.getX() - entity.getX();
        double vx = Math.signum(dx) * 150;
        double vy = entity.getComponent(PhysicsComponent.class).getLinearVelocity().getY();
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(new Point2D(vx, vy));
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

    public BossUIComponents getBossUI() {
        return bossUI;
    }

    public int getOnTouchDmg() { return onTouchDmg; }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public Entity getPlayer() {
        return player;
    }
}

